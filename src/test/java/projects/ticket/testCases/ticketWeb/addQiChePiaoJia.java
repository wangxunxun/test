package projects.ticket.testCases.ticketWeb;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import projects.ticket.frame.piaoWuWebTest;

public class addQiChePiaoJia {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();

	@BeforeClass
	public void setUp() {
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();
		piaoWuWebApp.logClassInfo("testclasdfdsreinfof");

	}

	@AfterClass
	public void tearDown() {
		piaoWuWebApp.quit();
	}

	
	@Test
	public void test001enterAddMainPage() {
		piaoWuWebApp.logTestDescription("进入主页");
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.waitDisplay("侧边栏", "长途售票管理");
		piaoWuWebApp.clickElement("侧边栏", "长途售票管理");
		piaoWuWebApp.logSuccessMessage("成功进入主页");


	}
	@Test
	public void test022editQiChePiaoJia() throws InterruptedException,
			BiffException, IOException {

		piaoWuWebApp.logTestDescription("添加汽车票价");
		String piaojia = piaoWuWebApp.getProperties("qiChePiaoJia");

		String newPiaoJia = piaoWuWebApp.getTableRowLocationByCss(piaojia, 1);
		piaoWuWebApp.waitDisplay("侧边栏", "汽车线路管理");
		piaoWuWebApp.clickElement("侧边栏", "汽车线路管理");
		piaoWuWebApp.waitDisplay("汽车-添加车站", "iframe");
		piaoWuWebApp.switchToFrame("汽车线路管理", "iframe");
		piaoWuWebApp.waitDisplayByCss(newPiaoJia);
		piaoWuWebApp.clickElementByCss(newPiaoJia);

		piaoWuWebApp.switchToFrame("xubox_iframe1");
		for (int i = 1; i <= 91; i++) {
			String loc1 = "#form-user-add > table > tbody > tr:nth-child(" + i
					+ ") > td:nth-child(3) > input";
			String loc2 = "#form-user-add > table > tbody > tr:nth-child(" + i
					+ ") > td:nth-child(4) > input";
			String loc3 = "#form-user-add > table > tbody > tr:nth-child(" + i
					+ ") > td:nth-child(5) > input";
			String loc4 = "#form-user-add > table > tbody > tr:nth-child(" + i
					+ ") > td:nth-child(6) > input";
			piaoWuWebApp.clearByCss(loc1);
			piaoWuWebApp.sendKeysByCss(loc1, "2");
			piaoWuWebApp.clearByCss(loc2);
			piaoWuWebApp.sendKeysByCss(loc2, "1");
			piaoWuWebApp.clearByCss(loc3);
			piaoWuWebApp.sendKeysByCss(loc3, "0.01");
			piaoWuWebApp.clearByCss(loc4);
			piaoWuWebApp.sendKeysByCss(loc4, "0.01");

		}

		piaoWuWebApp.clickElement("汽车-票价", "提交");

//		 piaoWuWebApp.runTestCase("添加城市");

	}
}
