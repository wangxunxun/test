package projects.ticket.testCases.ticketWeb;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import projects.ticket.frame.piaoWuWebTest;

public class addBusStation {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();

	@BeforeClass
	public void setUp() {
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();
		// piaoWuWebApp.runFirefoxApp();
		piaoWuWebApp.logClassInfo("添加巴士车站");

	}

	@AfterClass
	public void tearDown() {
		piaoWuWebApp.quit();
	}

	@DataProvider(name = "addBaShiStation")
	public Object[][] dataProvider2() {
		String addBaShiStation = piaoWuWebApp.getProperties("addBaShiStation");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiStation);
	}

	@Test
	public void test001login() {
		piaoWuWebApp.logTestDescription("进入主页");
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.logSuccessMessage("成功进入主页");
	}

	@Test(dataProvider = "addBaShiStation")
	public void test002addBaShiStation(String name, String longitude, String latitude)
			throws InterruptedException, BiffException, IOException {
		piaoWuWebApp.logTestDescription("添加巴士车站" + name);
		piaoWuWebApp.waitDisplay("侧边栏", "巴士车站维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士车站维护");
		piaoWuWebApp.waitDisplay("巴士车站维护", "iframe");
		piaoWuWebApp.switchToFrame("巴士车站维护", "iframe");
		piaoWuWebApp.waitDisplay("巴士车站维护", "添加车站");
		piaoWuWebApp.clickElement("巴士车站维护", "添加车站");
		piaoWuWebApp.switchToFrame("xubox_iframe1");
		piaoWuWebApp.waitDisplay("巴士-添加车站", "车站名称");
		piaoWuWebApp.sendKeys("巴士-添加车站", "车站名称", name);
		piaoWuWebApp.sendKeys("巴士-添加车站", "经度", longitude);
		piaoWuWebApp.sendKeys("巴士-添加车站", "纬度", latitude);
		piaoWuWebApp.clickElement("巴士-添加车站", "提交");
		if (piaoWuWebApp.verifyDisplay("巴士-添加车站", "提交")) {
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.switchToFrame("巴士车站维护", "iframe");
			piaoWuWebApp.clickElement("巴士-添加车站", "关闭");
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage(name + "已经存在");
		} else {
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage("成功添加" + name);
		}
	}

}
