package test.autotest.base;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.HideKeyboardStrategy;
import test.autotest.utils.CommonTools;
import test.autotest.utils.OperateExcel;
import test.autotest.utils.TestngListener;
import test.autotest.core.UI;

public class IOSApp extends UI {

	public void runIOSApp() {

		try {
			initialIOSData();

		} catch (Exception e) {
			System.err.println(e.toString());
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("app", iosApp);
		capabilities.setCapability("deviceName", iosDeviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platform", platform);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("browserName", browserName);

		try {
			iosDriver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		wait = new WebDriverWait(iosDriver, waitTime);
		driver = iosDriver;

	}

	public void quit() {

		try {
			testAppType = null;
			String excelPath = CommonTools.setPath(testCaseExcelPath);
			if (writeResult == true) {
				CommonTools.writeResultToExcel(excelPath, testCaseSheet, testResultData);
				testResultData.clear();
			}
			if (writeScript == true) {
				CommonTools.writeScriptToExcel(excelPath, testCaseSheet, testScriptData);
				testScriptData.clear();
			}
			OperateExcel testClassSheet = new OperateExcel(testReportDir + testReportName + ".xls", testClassName);
			testClassSheet.setColumnView(1, 100);
			testClassSheet.setColumnView(0, 40);
			testClassSheet.setFormat(10, true);
			testClassSheet.setHyperLinkForSheet(0, 0, "Back", testSummarySheetName, 0, 0);
			testClassSheet.setVerticalFreeze(1);
			testClassSheet.writeLogToExcel(logData);
			testClassSheet.close();
			logData.clear();

			OperateExcel testSummaySheet = new OperateExcel(testReportDir + testReportName + ".xls",
					testSummarySheetName);
			testSummaySheet.setFormat(10, true);
			testSummaySheet.writeTestSummaryToExcel(TestngListener.classData);
			testSummaySheet.close();
			TestngListener.classData.clear();

		} catch (Exception e) {
			System.err.println(e);
		}
		iosDriver.quit();
	}

	public void quitWithoutTestData() {
		testAppType = null;
		iosDriver.quit();
	}

	public WebElement findElement(String page, String name) {

		return findElement(page, name, this);
	}
	
	public void sendKeysAndHideKeyBoard(String page, String name, String value) {
		super.sendKeys(page, name, value);
		hideKeyboard("换行");
	}
	
	public void sendKeysAndClikeEnter(String page, String name, String value) {
		super.sendKeys(page, name, value);
		iosDriver.getKeyboard().sendKeys("确认");
//		hideKeyboard("确认");
	}

	protected void hideKeyboard(String keyName){
		iosDriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, keyName);
	}

	public void swipeLeft() {
		swipeOfType("left",this);
	}

	public void swipeRight() {
		swipeOfType("right",this);
	}

	public void swipeUp() {
		swipeOfType("up",this);
	}

	public void swipeDown() {
		swipeOfType("down",this);
	}

	@SuppressWarnings({ "unchecked" })
	public void runTestCase(String testCase) {

		List<Map<String, String>> cases = (List<Map<String, String>>) testCaseData.get(testCase);

		for (int i = 0; i < cases.size(); i++) {

			String action = cases.get(i).get("Action");
			String page = cases.get(i).get("Page");
			String name = cases.get(i).get("Element");
			String value = cases.get(i).get("Value");
			String actual = cases.get(i).get("Actual");
			String expected = cases.get(i).get("Expected");
			String row = cases.get(i).get("row");
			int rowin = Integer.parseInt(row);

			if (action.equals("click")) {
				clickElement(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clickElement(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("sleep")) {
				int v = Integer.parseInt(value);
				CommonTools.sleep(v);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = "CommonTools.sleep(" + v + ");";
				putScriptData(rowin, script);

			} else if (action.equals("waitDisplay")) {
				waitDisplay(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "waitDisplay(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			}

			else if (action.equals("clear")) {
				clear(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clear(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);
			}

			else if (action.equals("swipeOfType")) {
				swipeOfType(value,this);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "swipeOfType(\"" + value + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("sendKey")) {
				sendKeys(page, name, value);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "sendKeys(\"" + page + "\",\"" + name + "\",\"" + value + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("assert")) {
				actual = getElementText(page, name);
				assertEquals(actual, expected);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "assertEquals(" + appClass + "." + "getElementText(\"" + page + "\",\""
						+ name + "\")" + "," + "\"" + expected + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("runTestCase")) {
				runTestCase(value);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "runTestCase(\"" + value + "\");";
				putScriptData(rowin, script);

			}

			else {
				CommonTools.log("Can not run the action");

			}

		}
	}
}
