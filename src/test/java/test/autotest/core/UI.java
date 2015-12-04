package test.autotest.core;

import java.awt.Color;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import test.autotest.base.AndroidApp;
import test.autotest.core.Initial;
import test.autotest.utils.CommonTools;
import test.autotest.utils.ImageUtils;

public class UI extends Initial {
	public static WebDriver driver;
	protected AndroidDriver<AndroidElement> androidDriver;
	protected IOSDriver<IOSElement> iosDriver;
	protected WebDriverWait wait;

	public void clickElement(String page, String name) {
		log("Click the " + name + " element on the " + page + " page.");
		try {
			findElement(page, name).click();
		} catch (Exception e) {
			Assert.fail("Fail to click the " + name + " element on the " + page + " page.\n");
		}
	}

	public void sendKeys(String page, String name, String value) {
		log("Send the \"" + value + "\" value to the " + name + " element on the " + page + " page.");
		try {
			findElement(page, name).sendKeys(value);
		} catch (Exception e) {
			Assert.fail(
					"Fail to send the \"" + value + "\" value to the " + name + " element on the " + page + " page.\n");
		}
	}

	public void clear(String page, String name) {
		log("Clear the " + name + " element on the " + page + " page.");
		try {
			findElement(page, name).clear();
		} catch (Exception e) {
			Assert.fail("Fail to clear the " + name + " element on the " + page + " page.\n");
		}
	}

	public void clear(String page, String name, String defaultStr) {
		log("Clear the " + name + " element on the " + page + " page.");
		while (true) {
			WebElement ele = findElement(page, name);
			if (!getElementText(page, name).contains(defaultStr)) {
				try {
					ele.clear();
				} catch (Exception e) {
					Assert.fail("Fail to clear the " + name + " element on the " + page + " page.\n");
				}
			} else {
				break;
			}
		}
	}

	public String getElementText(String page, String name) {
		log("To get the text of the " + name + " element on the " + page + " page.");
		String value = findElement(page, name).getText();
		if (value != null) {
			log("The text is \"" + value + "\".");
			return value;
		} else {
			log("The text is null.");
		}
		return null;
	}

	protected void executeJavaScript(String js, WebElement element) {
		((JavascriptExecutor) driver).executeScript(js, element);
	}

	public void assertFail(String message) {
		Assert.fail(message);
	}

	public void assertContains(String actual, String expected) {
		if (!expected.contains(actual)) {
			Assert.fail("Expected [" + expected + "] but found [" + actual + "]");
		} else {
			log("Expected [" + expected + "] found [" + actual + "]");
		}
	}

	public void assertEquals(String actual, String expected) {
		log("assertEquals expected[" + expected + "] actual[" + actual + "]");
		Assert.assertEquals(actual, expected);
	}

	public boolean isSlected(String page, String name) {
		return findElement(page, name).isSelected();
	}

	public boolean isDisplayed(String page, String name) {
		return findElement(page, name).isDisplayed();
	}

	public boolean isEnabled(String page, String name) {
		return findElement(page, name).isEnabled();
	}

	public void sleep(int time) {
		log("Sleep " + time + " ms.");
		CommonTools.sleep(time);
	}

	private String getElementExpectedValue(String page, String name) {
		log("To get the expected text of the " + name + " element on the " + page + " page.");
		String value = elementData.get(page).get(name).get("Expected Value");
		if (value != null) {
			log("The expected text is \"" + value + "\".");
			return value;
		} else {
			log("The expected text is null.");
		}
		return value;
	}

	public void assertElementContains(String page, String name) {
		assertContains(getElementText(page, name), getElementExpectedValue(page, name));
	}

	public void assertElementEquals(String page, String name) {
		assertEquals(getElementText(page, name), getElementExpectedValue(page, name));
	}
	
	public List<WebElement> findElementsByName(String name) {
		return driver.findElements(By.name(name));
	}

	public void clickElmentByName(String name) {
		clickElmentByName(name, 0);
	}
	
	public void clickElmentByName(String name,int index) {
		List<WebElement> elements = findElementsByName(name);
		System.out.println(elements.size());
		
		elements.get(index).click();
	}

	public void printAllElesByClassName(String className) {
		List<WebElement> eles = driver.findElements(By.className(className));
		if (eles.size() != 0) {
			log("The count of " + className + " is " + eles.size());

			for (int i = 0; i < eles.size(); i++) {
				log("The " + i + " th " + className + " is " + eles.get(i).getText());
			}
		} else {
			log("Can not find any elements.");
		}
	}

	public WebElement findElementByClassNameIndex(String className, int index) {
		List<WebElement> eles = driver.findElements(By.className(className));
		return eles.get(index);
	}

	public WebElement findElement(String page, String name) {
		return findElement(page, name, this);
	}

	protected WebElement findElement(String page, String name, UI ui) {
		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		try {
			if (selecttype.equals("css")) {
				return driver.findElement(By.cssSelector(location));
			} else if (selecttype.equals("id")) {
				return driver.findElement(By.id(location));
			} else if (selecttype.equals("xpath")) {
				return driver.findElement(By.xpath(location));
			} else if (selecttype.equals("name")) {
				return driver.findElement(By.name(location));

			} else if (selecttype.equals("linktext")) {
				return driver.findElement(By.linkText(location));

			} else if (selecttype.equals("partiallinktext")) {
				return driver.findElement(By.partialLinkText(location));
			} else if (selecttype.equals("tagname")) {
				return driver.findElement(By.tagName(location));
			} else if (selecttype.equals("index")) {
				String[] sourceStrArray = location.split(",");
				String classname = sourceStrArray[0];
				String index = sourceStrArray[1];
				int in = Integer.parseInt(index);
				return findElementByClassNameIndex(classname, in);
			} else if (ui instanceof AndroidApp) {
				if (selecttype.equals("scrollname")) {
					return androidDriver.scrollTo(location);
				}
			} else {
				log("Can not find the " + name + " element on the " + page + " page." + "The " + selecttype + " is "
						+ location);
			}
		} catch (Exception e) {
			Assert.fail("Can not find the " + name + " element on the " + page + " page." + "The " + selecttype + " is "
					+ location);
		}
		return null;
	}

	public List<WebElement> findElements(String page, String name) {
		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		try {
			if (selecttype.equals("css")) {
				return driver.findElements(By.cssSelector(location));
			} else if (selecttype.equals("id")) {
				return driver.findElements(By.id(location));
			} else if (selecttype.equals("xpath")) {
				return driver.findElements(By.xpath(location));
			} else if (selecttype.equals("name")) {
				return driver.findElements(By.name(location));

			} else if (selecttype.equals("linktext")) {
				return driver.findElements(By.linkText(location));

			} else if (selecttype.equals("partiallinktext")) {
				return driver.findElements(By.partialLinkText(location));
			} else if (selecttype.equals("tagname")) {
				return driver.findElements(By.tagName(location));
			} else if (selecttype.equals("classname")) {
				return driver.findElements(By.className(location));
			} else {
				log("Can not find the " + name + " element on the " + page + " page." + "The " + selecttype + " is "
						+ location);
			}
		} catch (Exception e) {
			Assert.fail("Can not find the " + name + " element on the " + page + " page." + "The " + selecttype + " is "
					+ location);
		}
		return null;
	}

	public void waitDisplay(String page, String name) {
		waitDisplay(page, name, 10);
	}
	protected void waitDisplayAfterLoading(String page,String name,String loadingPage,String loadingName){
		waitDisplayAfterLoading(page, name, loadingPage, loadingName, 10);
	}

	protected void waitDisplayAfterLoading(String page,String name,String loadingPage,String loadingName,int count){
		boolean status = false;
		for (int i = 0; i < count; i++) {
			if(!verifyDisplay(loadingPage, loadingName)){
				status = true;
				waitDisplay(page, name);
				break;
			}
		}
		if(status == false){
			Assert.fail("The UI is still loading.");
		}

		
	}
	public void waitDisplay(String page, String name, int count) {
		boolean status = false;
		for (int i = 0; i < count; i++) {
			status = verifyDisplay(page, name);
			if (status == false) {
				if (i < count - 1) {
					log("Start to find the " + name + " element on the " + page + " page again.");
				}
				continue;
			} else {
				break;
			}
		}
		if (status == false) {
			Assert.fail("Can not find the " + name + " element on the " + page + " page.");

		}

	}

	public boolean verifyDisplay(String page, String name) {

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		log("To find the " + name + " element on the " + page + " page.");
		if (selecttype.equals("css")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(location)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else if (selecttype.equals("id")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(location)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else if (selecttype.equals("xpath")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(location)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else if (selecttype.equals("linktext")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(location)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else if (selecttype.equals("name")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(location)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else if (selecttype.equals("partiallinktext")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(location)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else if (selecttype.equals("tagname")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(location)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else if (selecttype.equals("index")) {
			String[] sourceStrArray = location.split(",");
			String classname = sourceStrArray[0];
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(classname)));
			} catch (Exception e) {
				log("The " + name + " element on the " + page + " page is not displayed.");
				return false;
			}

		} else {
			log("Don't support the type.");
			return false;
		}
		log("The " + name + " element on the " + page + " page is displayed.");
		sleep(100);
		return true;
	}

	protected void swipeOfType(String type,UI ui) {
		try {
			int windowlenX = 0;
			int windowlenY = 0;
			if (ui instanceof AndroidApp){
				windowlenX = androidDriver.manage().window().getSize().getWidth();
				windowlenY = androidDriver.manage().window().getSize().getHeight();
			}
			else{
				windowlenX = iosDriver.manage().window().getSize().getWidth();
				windowlenY = iosDriver.manage().window().getSize().getHeight();
			}
				
			String swipeLeft = "left";
			String swipeRight = "right";
			String swipeUp = "up";
			String swipeDown = "down";

			// Sliding screen to the left
			if (type.equalsIgnoreCase(swipeLeft)) {
				log("Swipe left.");
				if (ui instanceof AndroidApp){
					androidDriver.swipe((int) (windowlenX * 0.9), (int) (windowlenY * 0.5), (int) (windowlenX * 0.1),
							(int) (windowlenY * 0.5), 3000);
				}
				else{
					iosDriver.swipe((int) (windowlenX * 0.9), (int) (windowlenY * 0.5), (int) (windowlenX * 0.1),
							(int) (windowlenY * 0.5), 3000);
				}

			}

			// Sliding screen to the right
			if (type.equalsIgnoreCase(swipeRight)) {
				log("Swipe right.");
				if (ui instanceof AndroidApp){
					androidDriver.swipe((int) (windowlenX * 0.1), (int) (windowlenY * 0.5), (int) (windowlenX * 0.9),
							(int) (windowlenY * 0.5), 3000);
				}
				else{
					iosDriver.swipe((int) (windowlenX * 0.1), (int) (windowlenY * 0.5), (int) (windowlenX * 0.9),
							(int) (windowlenY * 0.5), 3000);
				}
				
			}

			// Screen upward sliding
			if (type.equalsIgnoreCase(swipeUp)) {
				log("Swipe up.");
				if (ui instanceof AndroidApp){
					androidDriver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.8), (int) (windowlenX * 0.5),
							(int) (windowlenY * 0.4), 3000);
				}
				else{
					iosDriver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.8), (int) (windowlenX * 0.5),
							(int) (windowlenY * 0.4), 3000);
				}

			}

			// Slide down the screen
			if (type.equalsIgnoreCase(swipeDown)) {
				log("Swipe down.");
				if (ui instanceof AndroidApp){
					androidDriver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.4), (int) (windowlenX * 0.5),
							(int) (windowlenY * 0.8), 3000);
				}
				else{
					iosDriver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.4), (int) (windowlenX * 0.5),
							(int) (windowlenY * 0.8), 3000);
				}

			}
		

		} catch (Exception e) {
			Assert.fail("Fail to swip to " + type + ".");
		}

	}

	public void getScreen(String filename) {
		File scrFile = null;

		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		if (!(new File(screenDir).isDirectory())) { // 判断是否存在该目录
			new File(screenDir).mkdir(); // 如果不存在则新建一个目录
		}
		try {
			log("Get screen.");
			String path = screenDir + CommonTools.getCurrentTime() + "_" + filename + ".png";
			FileUtils.copyFile(scrFile, new File(path));
		} catch (Exception e) {
			Assert.fail("Get screen failed.\n");
		}
	}

	private String getScreenReturnPath(String filename) {
		File scrFile = null;

		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		if (!(new File(screenDir).isDirectory())) { // 判断是否存在该目录
			new File(screenDir).mkdir(); // 如果不存在则新建一个目录
		}
		try {
			log("Get screen.");
			String path = screenDir + CommonTools.getCurrentTime() + "_" + filename + ".png";
			FileUtils.copyFile(scrFile, new File(path));
			return path;
		} catch (Exception e) {
			Assert.fail("Get screen failed.\n");

		}
		return null;

	}

	public void getScreen() {
		getScreen("");
	}

	private String getScreenReturnPath() {
		return getScreenReturnPath("");
	}

	public void getElementScreen(String page, String name) {
		String srcImg = getScreenReturnPath();
		File srcImg1 = new File(srcImg);
		String destImg = screenDir;
		String imgName = srcImg1.getName();
		int x = getElementLocateX(page, name);
		int y = getElementLocateY(page, name);
		int elementX = getElementX(page, name);
		int elementY = getElementY(page, name);
		try {
			ImageUtils.cutImage(srcImg, destImg + "cut by element_ " + name + " " + imgName, x, y, elementX, elementY);
		} catch (Exception e) {
			Assert.fail("Get element screen failed.\n");

		}
	}

	public void getScreenMarkedByText(String content) {

		String srcImg = getScreenReturnPath();
		File srcImg1 = new File(srcImg);
		String destImg = screenDir;
		String name = srcImg1.getName();
		try {
			ImageUtils.markImageByText(srcImg, destImg + "marked by text " + content + " " + name, content, Color.red,
					"黑体", 13);
		} catch (Exception e) {
			Assert.fail("Get element screen failed.\n");
		}
	}

	public int getElementX(String page, String name) {
		return findElement(page, name).getSize().getWidth();
	}

	public int getElementY(String page, String name) {
		return findElement(page, name).getSize().getHeight();
	}

	public int getElementLocateX(String page, String name) {
		return findElement(page, name).getLocation().getX();
	}

	public int getElementLocateY(String page, String name) {
		return findElement(page, name).getLocation().getY();
	}

}
