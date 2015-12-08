package projects.ticket.testCases.ticketWeb;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import projects.ticket.frame.piaoWuWebTest;

public class addBusPiaoJia {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();

	@DataProvider(name = "addBaShiLuDuan")
	public Object[][] dataProvider4() {
		String addBaShiLuDuan = piaoWuWebApp.getProperties("addBaShiLuDuan");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiLuDuan);
	}

	@BeforeClass
	public void setUp() {
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();
		// piaoWuWebApp.runFirefoxApp();
		piaoWuWebApp.logClassInfo("添加巴士票价");

	}

	@AfterClass
	public void tearDown() {
		piaoWuWebApp.quit();
	}

	@Test
	public void test000login() {
//		piaoWuWebApp.runTestCase("登录");
		piaoWuWebApp.logTestDescription("进入主页");
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.logSuccessMessage("成功进入主页");
//		piaoWuWebApp.assertFail("33");
	}

//	@Test
	public void test031editBaShiPiaoJia() {
		piaoWuWebApp.logTestDescription("添加bus票价");
		String piaoJia = piaoWuWebApp.getProperties("baShiPiaoJia");
		String newPiaoJia = piaoWuWebApp.getTableRowLocationByCss(piaoJia, 1);
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");
		piaoWuWebApp.waitDisplay("巴士车站维护", "iframe");
		piaoWuWebApp.switchToFrame("巴士车站维护", "iframe");
		piaoWuWebApp.waitDisplayByCss(newPiaoJia);
		piaoWuWebApp.clickElementByCss(newPiaoJia);

		piaoWuWebApp.switchToFrame("xubox_iframe1");

		for (int i = 1; i <= 78; i++) {
			String loc1 = "#form-user-add > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3) > input";
			String loc2 = "#form-user-add > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4) > input";
			String loc3 = "#form-user-add > table > tbody > tr:nth-child(" + i + ") > td:nth-child(5) > input";
			String loc4 = "#form-user-add > table > tbody > tr:nth-child(" + i + ") > td:nth-child(6) > input";
			piaoWuWebApp.clearByCss(loc1);
			piaoWuWebApp.sendKeysByCss(loc1, "2");
			piaoWuWebApp.clearByCss(loc2);
			piaoWuWebApp.sendKeysByCss(loc2, "1");
			piaoWuWebApp.clearByCss(loc3);
			piaoWuWebApp.sendKeysByCss(loc3, "0.02");
			piaoWuWebApp.clearByCss(loc4);
			piaoWuWebApp.sendKeysByCss(loc4, "0.01");

		}

		piaoWuWebApp.waitDisplay("巴士-票价", "提交");
		piaoWuWebApp.clickElement("巴士-票价", "提交");
		piaoWuWebApp.logSuccessMessage("成功添加票价");

	}
}
