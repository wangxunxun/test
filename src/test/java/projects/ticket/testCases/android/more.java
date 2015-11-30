package projects.ticket.testCases.android;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import projects.ticket.frame.ticketAndroid;

public class more {
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeClass
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();
		ticketApp.logClassInfo("测试更多页面");
	}

    @AfterClass
    public void tearDown(){

    	ticketApp.quit();
    }
    
    public void check(String page,String element){
    	if(!ticketApp.verifyDisplay(page, element)){
    		ticketApp.clickBack();
    		ticketApp.verifyDisplay(page, element);
    	}
    }
    
    @Test
    public void test001enterMorePage(){
    	ticketApp.logTestDescription("进入更多页面");
    	ticketApp.enterMainPage();
    	ticketApp.clickElement("首页", "我的");
    	ticketApp.waitDisplay("我的", "更多");
    	ticketApp.clickElement("我的", "更多");    
    	ticketApp.logSuccessMessage("成功进入更多页面");
    }
    
    @Test
    public void test002caoZuoZhiNan(){
    	ticketApp.logTestDescription("检查操作指南");
    	ticketApp.waitDisplay("更多", "操作指南");
    	ticketApp.clickElement("更多", "操作指南");    
    	ticketApp.waitDisplay("标题栏", "返回");
    	ticketApp.waitDisplay("操作指南", "正文");
    	ticketApp.clickElement("标题栏", "返回");
    	ticketApp.logSuccessMessage("操作指南验证通过");
    }
    @Test
    public void test003changJianWenTi(){
    	ticketApp.logTestDescription("检查常见问题");
    	check("更多", "常见问题");
    	ticketApp.clickElement("更多", "常见问题"); 
    	ticketApp.waitDisplay("标题栏", "返回");
    	ticketApp.waitDisplay("常见问题", "正文");
    	ticketApp.clickElement("标题栏", "返回");
    	ticketApp.logSuccessMessage("常见问题验证通过");
    }
    
    @Test
    public void test004mianZeShenMing(){
    	ticketApp.logTestDescription("检查免责声明");
    	check("更多", "免责声明");
    	ticketApp.clickElement("更多", "免责声明"); 
    	ticketApp.waitDisplay("标题栏", "返回");
    	ticketApp.assertElementContains("免责声明", "标题");
    	ticketApp.clickElement("标题栏", "返回");
    	ticketApp.logSuccessMessage("免责声明验证通过");
    }
    @Test
    public void test005guanYuWoMen(){
    	ticketApp.logTestDescription("检查关于我们");
    	check("更多", "关于我们");
    	ticketApp.clickElement("更多", "关于我们"); 
    	ticketApp.waitDisplay("标题栏", "返回");
    	ticketApp.waitDisplay("关于我们", "应用名");
    	ticketApp.assertElementContains("关于我们", "应用名");
    	ticketApp.clickElement("标题栏", "返回");
    	ticketApp.logSuccessMessage("关于我们验证通过");
    }
    @Test
    public void test006banBenGengXin(){
    	ticketApp.logTestDescription("检查版本更新");
    	check("更多", "版本更新");
    	ticketApp.assertElementContains("更多", "当前版本");
    	ticketApp.logSuccessMessage("版本更新验证通过");
    }
}
