package projects.ticket.testCases.IOS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projects.ticket.frame.ticketIOS;

public class login {
	ticketIOS ticketApp = new ticketIOS();
	
	@BeforeClass
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runIOSApp();
		ticketApp.logClassInfo("测试更多页面");
	}

    @AfterClass
    public void tearDown(){

    	ticketApp.quit();
    }
    @Test
    public void test001login(){

    	ticketApp.waitDisplayAfterLoading("汽车票", "起点");
    	ticketApp.clickElement("汽车票", "起点");
    	ticketApp.waitDisplay("汽车票", "搜索栏");
    	ticketApp.sendKeysAndClikeEnter("汽车票", "搜索栏", "咸宁");
//    	ticketApp.clickElement("汽车票", "第一个结果");
    	ticketApp.sleep(15000);

    }
}
