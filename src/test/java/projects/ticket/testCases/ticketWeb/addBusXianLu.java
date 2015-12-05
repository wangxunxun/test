package projects.ticket.testCases.ticketWeb;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import projects.ticket.frame.piaoWuWebTest;

public class addBusXianLu {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();

	@DataProvider(name = "addBaShiXianLu")
	public Object[][] dataProvider3() {
		String addBaShiXianLu = piaoWuWebApp.getProperties("addBaShiXianLu");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiXianLu);
	}

	@BeforeClass
	public void setUp() {
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();
		// piaoWuWebApp.runFirefoxApp();
		piaoWuWebApp.logClassInfo("添加巴士线路");

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

	@Test(dataProvider = "addBaShiXianLu")
	public void test011addBaShiXianLu(String name, String city, String qidian, String zhongdian, String km, String time,
			String sellNum, String sellDay, String effectiveData)
					throws InterruptedException, BiffException, IOException {
		piaoWuWebApp.logTestDescription("添加巴士线路" + name);
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");
		piaoWuWebApp.waitDisplay("巴士线路维护", "iframe");
		piaoWuWebApp.switchToFrame("巴士线路维护", "iframe");
		piaoWuWebApp.waitDisplay("巴士线路维护", "添加线路");
		piaoWuWebApp.clickElement("巴士线路维护", "添加线路");
		piaoWuWebApp.switchToFrame("xubox_iframe1");
		piaoWuWebApp.waitDisplay("巴士-添加线路", "线路名称");
		piaoWuWebApp.sendKeys("巴士-添加线路", "线路名称", name);
		piaoWuWebApp.sendKeys("巴士-添加线路", "所属城市", city);
		piaoWuWebApp.sendKeys("巴士-添加线路", "起点站", qidian);
		piaoWuWebApp.sendKeys("巴士-添加线路", "终点站", zhongdian);
		piaoWuWebApp.sendKeys("巴士-添加线路", "里程", km);
		piaoWuWebApp.sendKeys("巴士-添加线路", "耗时", time);
		piaoWuWebApp.sendKeys("巴士-添加线路", "默认售票张数", sellNum);
		piaoWuWebApp.sendKeys("巴士-添加线路", "提前预售天数", sellDay);
		piaoWuWebApp.sendKeys("巴士-添加线路", "生效日期", effectiveData);
		piaoWuWebApp.clickElement("巴士-添加线路", "提前预售天数");
		piaoWuWebApp.clickElement("巴士-添加线路", "提交");
		if (piaoWuWebApp.verifyDisplay("巴士-添加线路", "提交")) {
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.switchToFrame("巴士线路维护", "iframe");
			piaoWuWebApp.clickElement("巴士-添加线路", "关闭");
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage(name + "已经存在");
		} else {
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage("成功添加" + name);
		}

	}
}
