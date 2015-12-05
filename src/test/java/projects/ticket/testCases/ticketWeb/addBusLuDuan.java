package projects.ticket.testCases.ticketWeb;

import projects.ticket.frame.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class addBusLuDuan {
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
		piaoWuWebApp.logClassInfo("添加巴士路段");

	}

	@AfterClass
	public void tearDown() {
		piaoWuWebApp.quit();
	}

	@Test
	public void test000login() {
		piaoWuWebApp.logTestDescription("进入主页");
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.logSuccessMessage("成功进入主页");
	}

	@Test(dataProvider = "addBaShiLuDuan")
	public void test021addBaShiLuDuan(String station, String km, String time)
			throws InterruptedException, BiffException, IOException {
		piaoWuWebApp.logTestDescription("添加巴士路段" + station);
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");
		piaoWuWebApp.waitDisplay("巴士线路维护", "iframe");
		piaoWuWebApp.switchToFrame("巴士线路维护", "iframe");

		String luDuan = piaoWuWebApp.getProperties("baShiLuDuan");

		String newLuDuan = piaoWuWebApp.getTableRowLocationByCss(luDuan, 1);

		piaoWuWebApp.waitDisplayByCss(newLuDuan);
		piaoWuWebApp.clickElementByCss(newLuDuan);

		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.waitDisplay("巴士-编辑路段", "选择站点");
		piaoWuWebApp.clear("巴士-编辑路段", "选择站点");
		piaoWuWebApp.sendKeys("巴士-编辑路段", "选择站点", station);
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站距离", km);
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站时间", time);
		piaoWuWebApp.clickElement("巴士-编辑路段", "添加");
		if (piaoWuWebApp.verifyDisplay("巴士-编辑路段", "站名")) {
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage(station + "已经存在");
		} else {
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage("成功添加" + station);
		}

	}

}
