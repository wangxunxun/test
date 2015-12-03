package projects.ticket.testCases.ticketWeb;

import projects.ticket.frame.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class qiChe {
	
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
	@DataProvider(name = "addqichezhan")
	public Object[][] dataProvider1() {
		String addQiCheZhan = piaoWuWebApp.getProperties("addQiCheZhan");
		return piaoWuWebApp.getTestDataForTestNG(addQiCheZhan);
	}

	@DataProvider(name = "addCity")
	public Object[][] dataProvider2() {
		String addCity = piaoWuWebApp.getProperties("addCity");
		return piaoWuWebApp.getTestDataForTestNG(addCity);
	}

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
	public void test001enterAddCityPage() {
		piaoWuWebApp.logTestDescription("进入主页");
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.waitDisplay("侧边栏", "长途售票管理");
		piaoWuWebApp.clickElement("侧边栏", "长途售票管理");
		piaoWuWebApp.logSuccessMessage("成功进入主页");
		

	}

	@Test(dataProvider="addCity")
	public void test010addCity(String name, String pinYin, String hot) {
		piaoWuWebApp.logTestDescription("添加城市-"+name);
		piaoWuWebApp.waitDisplay("侧边栏", "城市管理");
		piaoWuWebApp.clickElement("侧边栏", "城市管理");
		piaoWuWebApp.switchToFrame("添加城市", "iframe");
		piaoWuWebApp.waitDisplay("城市管理", "添加城市");
		piaoWuWebApp.clickElement("城市管理", "添加城市");
		piaoWuWebApp.switchToFrame("xubox_iframe1");
		piaoWuWebApp.waitDisplay("添加城市", "城市名称");
		piaoWuWebApp.sendKeys("添加城市", "城市名称", name);
		piaoWuWebApp.waitDisplay("添加城市", "城市拼音简写");
		piaoWuWebApp.sendKeys("添加城市", "城市拼音简写", pinYin);
		if (hot.matches("是")) {
			piaoWuWebApp.clickElement("添加城市", "热门");
		}

		piaoWuWebApp.clickElement("添加城市", "提交");
		if(piaoWuWebApp.verifyDisplay("添加城市", "提交")){
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.switchToFrame("添加城市", "iframe");
			piaoWuWebApp.clickElement("添加城市", "删除");			
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage(name+"-已经存在");
		}
		else{			
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage("成功添加 -"+name);
		}
		piaoWuWebApp.assertFail("111");
		
	}

//	@Test(dataProvider="addqichezhan")
	public void test021addQiCheZhan(String address, String name, String pinyin,
			String longitude, String latitude, String bashi, String phone,
			String from, String to, String city) throws InterruptedException,
			BiffException, IOException {
		piaoWuWebApp.logTestDescription("添加汽车站-"+name);
		piaoWuWebApp.waitDisplay("侧边栏", "汽车车站维护");
		piaoWuWebApp.clickElement("侧边栏", "汽车车站维护");

		piaoWuWebApp.switchToFrame("汽车-添加车站", "iframe");
		piaoWuWebApp.waitDisplay("汽车车站维护", "添加车站");
		piaoWuWebApp.clickElement("汽车车站维护", "添加车站");

		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("汽车-添加车站", "车站地址", address);
		piaoWuWebApp.sendKeys("汽车-添加车站", "车站名称", name);
		piaoWuWebApp.sendKeys("汽车-添加车站", "拼音", pinyin);
		piaoWuWebApp.sendKeys("汽车-添加车站", "经度", longitude);
		piaoWuWebApp.sendKeys("汽车-添加车站", "纬度", latitude);
		piaoWuWebApp.clear("汽车-添加车站", "途径巴士");
		piaoWuWebApp.sendKeys("汽车-添加车站", "途径巴士", bashi);
		piaoWuWebApp.sendKeys("汽车-添加车站", "车站电话", phone);

		piaoWuWebApp.sendKeys("汽车-添加车站", "营业时间从", from);
		piaoWuWebApp.clickElement("汽车-添加车站", "车站地址");
		piaoWuWebApp.sendKeys("汽车-添加车站", "营业时间到", to);
		piaoWuWebApp.clickElement("汽车-添加车站", "车站地址");

		piaoWuWebApp.clear("汽车-添加车站", "车站所在城市");
		piaoWuWebApp.sendKeys("汽车-添加车站", "车站所在城市", city);
		piaoWuWebApp.clickElement("汽车-添加车站", "车站地址");
		piaoWuWebApp.clickElement("汽车-添加车站", "提交");
		if (piaoWuWebApp.verifyDisplay("汽车-添加车站", "提交")) {
			piaoWuWebApp.log(city+"已经存在");		
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.switchToFrame("汽车-添加车站", "iframe");
			piaoWuWebApp.waitDisplay("汽车-添加车站", "关闭");
			piaoWuWebApp.clickElement("汽车-添加车站", "关闭");	
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage(name+"-已经存在");
		}
		else{
			piaoWuWebApp.switchToDefaultContent();
			piaoWuWebApp.logSuccessMessage("成功添加-"+name);
		}
 		
	}

//	@Test
	public void test022editQiChePiaoJia() throws InterruptedException,
			BiffException, IOException {

		piaoWuWebApp.logTestDescription("ahahddsferewrewrewrewrerew");
		String piaojia = piaoWuWebApp.getProperties("qiChePiaoJia");

		String newPiaoJia = piaoWuWebApp.getTableRowLocationByCss(piaojia, 6);

		// piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "长途售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "汽车线路管理");
		piaoWuWebApp.clickElement("侧边栏", "汽车线路管理");
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
