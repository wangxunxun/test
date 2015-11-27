package projects.ticket.testCases.android;







import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import projects.ticket.frame.ticketAndroid;
import test.autotest.utils.CommonTools;





public class testa{
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeMethod
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();
	}

    @AfterMethod
    public void tearDown(){

    	ticketApp.quit();
    }
    
    


    @Test
    public void searchQiChePiao(){
    	ticketApp.enterMainPage();
    	ticketApp.sleep(5000);
    	ticketApp.clickElement("汽车票", "起始站");
    	ticketApp.sendKeys("选择出发城市", "搜索输入框","XN");
    	ticketApp.clickElmentByName("咸宁");
    	ticketApp.clickElement("汽车票", "终点站");
    	ticketApp.sendKeys("选择出发城市", "搜索输入框","SZ");
    	ticketApp.clickElmentByName("深圳");
    	ticketApp.clickElement("汽车票", "乘车日期");
    	String currentDay = CommonTools.getCurrentDay();
    	int day = Integer.parseInt(currentDay);
    	ticketApp.clickElmentByName(String.valueOf(day+1));
 	
    	ticketApp.clickElement("汽车票", "查询");
    	ticketApp.clickElmentByName("南山汽车站");
    	ticketApp.clickElmentByName("18:00");
    	ticketApp.login();
    	ticketApp.sleep(5000);

    



    }


    
}