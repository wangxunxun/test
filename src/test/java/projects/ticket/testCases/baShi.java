package projects.ticket.testCases;

import projects.ticket.frame.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//@Listeners(utils.TestngListener.class)
public class baShi {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
	piaoWuWebTest testaaa;
	
	

	
	


	
	@DataProvider(name="addBaShiStation")
	public Object[][] dataProvider2(){		
		String addBaShiStation = piaoWuWebApp.getProperties("addBaShiStation");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiStation);
	}
	
	@DataProvider(name="addBaShiXianLu")
	public Object[][] dataProvider3(){		
		String addBaShiXianLu = piaoWuWebApp.getProperties("addBaShiXianLu");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiXianLu);
	}
	
	@DataProvider(name="addBaShiLuDuan")
	public Object[][] dataProvider4(){		
		String addBaShiLuDuan = piaoWuWebApp.getProperties("addBaShiLuDuan");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiLuDuan);
	}
	
	
	@BeforeClass
	public void setUp(){
		
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();	
//		piaoWuWebApp.logClassInfo("342423resrewrewrewr");

	}


		
	@AfterClass
	public void tearDown() {
		piaoWuWebApp.quit();
	}

	@Test
	public void test000login(){
		piaoWuWebApp.logTestDescription("login the ststem");
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.getScreen();
		piaoWuWebApp.assertEquals("333", "333334");
	}
	
	@Test
	public void test002login() {
		piaoWuWebApp.logTestDescription("test");
		piaoWuWebApp.logSuccessMessage("34342342dfdfd3423");
		piaoWuWebApp.getScreen();
	}
	
	@Test
	public void test003login() {
		piaoWuWebApp.logTestDescription("test");
		piaoWuWebApp.logSuccessMessage("3434234234edfdf23");
	}
	
//	@Test
	public void test004login() {
		piaoWuWebApp.logTestDescription("test");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.logSuccessMessage("3434234234eeer23");
	}
	
//	@Test
	public void test001EnterBusStationPage(){
		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士车站维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士车站维护");


	}
	
//	@Test(dataProvider="addBaShiStation")
    public void test001addBaShiStation(String name,String longitude,String latitude) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.waitDisplay("巴士车站维护", "添加车站");
		piaoWuWebApp.clickElement("巴士车站维护", "添加车站");
		piaoWuWebApp.switchToFrame("xubox_iframe1");
		piaoWuWebApp.waitDisplay("巴士-添加车站", "车站名称");
		piaoWuWebApp.sendKeys("巴士-添加车站", "车站名称",name); 
		piaoWuWebApp.sendKeys("巴士-添加车站", "经度",longitude); 
		piaoWuWebApp.sendKeys("巴士-添加车站", "纬度",latitude); 
		piaoWuWebApp.clickElement("巴士-添加车站", "提交");



    }
//	@Test
	public void test010EnterBaShiXianLuPage(){
		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");



	}
//	@Test(dataProvider="addBaShiXianLu")
    public void test011addBaShiXianLu(String name,String city,String qidian,String zhongdian,String km,String time,String sellNum,String sellDay,String effectiveData) throws InterruptedException, BiffException, IOException{



		piaoWuWebApp.waitDisplay("巴士线路维护", "添加线路");
		piaoWuWebApp.clickElement("巴士线路维护", "添加线路");
		piaoWuWebApp.switchToFrame("xubox_iframe1");
		piaoWuWebApp.waitDisplay("巴士-添加线路", "线路名称");
		piaoWuWebApp.sendKeys("巴士-添加线路", "线路名称",name); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "所属城市",city); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "起点站",qidian); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "终点站",zhongdian); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "里程",km); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "耗时",time); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "默认售票张数",sellNum); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "提前预售天数",sellDay); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "生效日期",effectiveData); 
		piaoWuWebApp.clickElement("巴士-添加线路", "提前预售天数");
		piaoWuWebApp.clickElement("巴士-添加线路", "提交");



    }
//	@Test
	public void test020EnterBaShiLuDuanPage(){
		String luDuan = piaoWuWebApp.getProperties("baShiLuDuan");

		String newLuDuan = piaoWuWebApp.getTableRowLocationByCss(luDuan, 1);
		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");
		
		
		piaoWuWebApp.waitDisplayByCss(newLuDuan);
		piaoWuWebApp.clickByCss(newLuDuan);

		piaoWuWebApp.switchToFrame("xubox_iframe1");



	}
//	@Test(dataProvider="addBaShiLuDuan")
    public void test021addBaShiLuDuan(String station,String km,String time) throws InterruptedException, BiffException, IOException{




		piaoWuWebApp.waitDisplay("巴士-编辑路段", "选择站点");
		piaoWuWebApp.sendKeys("巴士-编辑路段", "选择站点",station); 
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站距离",km); 
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站时间",time); 

		piaoWuWebApp.clickElement("巴士-编辑路段", "添加");

    }

//	@Test
    public void test031editBaShiPiaoJia() throws InterruptedException, BiffException, IOException{

		String piaoJia = piaoWuWebApp.getProperties("baShiPiaoJia");

		String newPiaoJia = piaoWuWebApp.getTableRowLocationByCss(piaoJia, 2);

		piaoWuWebApp.waitDisplay("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");

		piaoWuWebApp.waitDisplayByCss(newPiaoJia);
		piaoWuWebApp.clickByCss(newPiaoJia);

		piaoWuWebApp.switchToFrame("xubox_iframe1");
		
		for (int i=1;i<=78;i++){
			String loc1 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > input";
			String loc2 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > input";
			String loc3 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > input";
			String loc4 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(6) > input";
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
		piaoWuWebApp.sleep(5000);


    }
}
