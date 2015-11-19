package projects.ticket.testCases;

import projects.ticket.frame.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class cheDuiAndCheLiang {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();

	
	
	
	


	
	@DataProvider(name="addCheLiang")
	public Object[][] dataProvider5(){		
		String addCheLiang = piaoWuWebApp.getProperties("addCheLiang");
		return piaoWuWebApp.getTestDataForTestNG(addCheLiang);
	}
	
	@DataProvider(name="addSiJi")
	public Object[][] dataProvider6(){		
		String addSiJi = piaoWuWebApp.getProperties("addSiJi");
		return piaoWuWebApp.getTestDataForTestNG(addSiJi);
	}
	
	@BeforeClass
	public void setUp(){
		
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();	


	}


		
	@AfterClass
	public void tearDown() {
		piaoWuWebApp.quit();
	}
	
	@Test
	public void test000login(){
		piaoWuWebApp.enterHomePage();
	}
	
//	@Test
	public void test010EnterAddCheLiangPage(){
		piaoWuWebApp.waitDisplay("侧边栏", "车队与车辆管理");
		piaoWuWebApp.clickElement("侧边栏", "车队与车辆管理");
		piaoWuWebApp.waitDisplay("侧边栏", "车辆管理");
		piaoWuWebApp.clickElement("侧边栏", "车辆管理");
	}

//	@Test(dataProvider="addCheLiang")
    public void test011AddCheLiang(String siJi,String cheDui,String carType,String car_owner,String phone_number,String seat_num,String max_seat_num,String add_seat_num,String plate_number,String buy_year,String engine_number,String brand  ) throws InterruptedException, BiffException, IOException{




		piaoWuWebApp.waitDisplay("车辆管理", "添加车辆");
		piaoWuWebApp.clickElement("车辆管理", "添加车辆");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("添加车辆", "选择司机",siJi); 
		piaoWuWebApp.sendKeys("添加车辆", "所属车队",cheDui); 
		piaoWuWebApp.sendKeys("添加车辆", "选择车类型",carType); 

		piaoWuWebApp.sendKeys("添加车辆", "车主姓名",car_owner); 
		piaoWuWebApp.sendKeys("添加车辆", "联系方式",phone_number); 
		piaoWuWebApp.sendKeys("添加车辆", "乘客数量",seat_num); 
		piaoWuWebApp.sendKeys("添加车辆", "最大座位数",max_seat_num); 
		piaoWuWebApp.sendKeys("添加车辆", "加座数",add_seat_num); 
		piaoWuWebApp.sendKeys("添加车辆", "车牌号码",plate_number); 
		piaoWuWebApp.sendKeys("添加车辆", "购买时间",buy_year); 
		piaoWuWebApp.clickElement("添加车辆", "车主姓名"); 
		piaoWuWebApp.sendKeys("添加车辆", "发动机号",engine_number); 
		piaoWuWebApp.clear("添加车辆", "品牌");
		piaoWuWebApp.sendKeys("添加车辆", "品牌",brand); 
		piaoWuWebApp.clickElement("添加车辆", "品牌");
		piaoWuWebApp.clickElement("添加车辆", "提交");

//		piaoWuWebApp.runTestCase("登录");
		

		
    }
	
	@Test
	public void test020EnterAddSiJiPage(){
		piaoWuWebApp.waitDisplay("侧边栏", "车队与车辆管理");
		piaoWuWebApp.clickElement("侧边栏", "车队与车辆管理");
		piaoWuWebApp.waitDisplay("侧边栏", "司机管理");
		piaoWuWebApp.clickElement("侧边栏", "司机管理");
	}
	
	@Test(dataProvider="addSiJi")
    public void test021addSiJi(String number,String name,String cheDui,String phone,String xingBie,String idCard,String workNo,String licenseNo,String driverYear,String driverType) throws InterruptedException, BiffException, IOException{




		piaoWuWebApp.waitDisplay("司机管理", "添加司机");
		piaoWuWebApp.clickElement("司机管理", "添加司机");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("添加司机", "编号",number); 
		piaoWuWebApp.sendKeys("添加司机", "姓名",name); 
		piaoWuWebApp.sendKeys("添加司机", "所属车队",cheDui); 
		piaoWuWebApp.sendKeys("添加司机", "手机号",phone); 
		if (xingBie.matches("男")){
			piaoWuWebApp.clickElement("添加司机", "性别-男");
		}
		else if (xingBie.matches("女")){
			piaoWuWebApp.clickElement("添加司机", "性别-女");
		}
		else{
			piaoWuWebApp.clickElement("添加司机", "性别-保密");
		}
		
		piaoWuWebApp.sendKeys("添加司机", "身份证号",idCard); 
		piaoWuWebApp.sendKeys("添加司机", "从业资格证",workNo); 
		piaoWuWebApp.sendKeys("添加司机", "驾驶证号",licenseNo); 
		piaoWuWebApp.sendKeys("添加司机", "驾龄",driverYear); 
		piaoWuWebApp.sendKeys("添加司机", "驾照等级",driverType); 


		piaoWuWebApp.clickElement("添加司机", "提交");


    }
    
//    @Test
    public void addCheDui(){
    	piaoWuWebApp.runTestCase("登录");
    	piaoWuWebApp.waitDisplay("侧边栏","车队与车辆管理");
    	piaoWuWebApp.clickElement("侧边栏","车队与车辆管理");
    	piaoWuWebApp.waitDisplay("侧边栏","车队管理");
    	piaoWuWebApp.clickElement("侧边栏","车队管理");
    	piaoWuWebApp.waitDisplay("车队管理","添加车队");
    	piaoWuWebApp.clickElement("车队管理","添加车队");
    	piaoWuWebApp.switchToFrame("xubox_iframe1");
    	piaoWuWebApp.sendKeys("添加车队","名称","11");
    	piaoWuWebApp.sendKeys("添加车队","负责人","11");
    	piaoWuWebApp.sendKeys("添加车队","电话","11");
    	piaoWuWebApp.sendKeys("添加车队","座机","11");
    	piaoWuWebApp.sendKeys("添加车队","地址","11");

    	piaoWuWebApp.clickElement("添加车队","提交");

    }
    
    

}
