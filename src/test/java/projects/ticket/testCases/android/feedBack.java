package projects.ticket.testCases.android;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import projects.ticket.frame.piaoWuWebTest;
import projects.ticket.frame.ticketAndroid;
import test.autotest.utils.CommonTools;

public class feedBack {
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeClass
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();
		ticketApp.logClassInfo("测试意见反馈页面");
	}

    @AfterClass
    public void tearDown(){

    	ticketApp.quit();
    }
    public void assertEqual(String page,String element){
    	if(ticketApp.verifyDisplay("意见反馈", "标题")){
    		ticketApp.assertElementContains("意见反馈", "标题");
    	}
    	else{
    		ticketApp.clickElement("我的", "意见反馈"); 
    	}
    	
    }
    @Test
    public void test001enterFeedbackPage(){
    	ticketApp.logTestDescription("进入意见反馈页面");
    	ticketApp.enterMainPage();
    	ticketApp.clickElement("首页", "我的");
    	ticketApp.waitDisplay("我的", "意见反馈");
    	ticketApp.clickElement("我的", "意见反馈");  
    	ticketApp.waitDisplay("意见反馈", "手机");
    	ticketApp.logSuccessMessage("成功进入意见反馈页面");
    }
    
    @Test
    public void test002checkPhone(){
    	ticketApp.logTestDescription("验证手机号合法性");
    	ticketApp.sendKeys("意见反馈", "手机", "1");
    	ticketApp.sendKeys("意见反馈", "电子邮箱", "59853844@qq.com");
    	ticketApp.sendKeys("意见反馈", "反馈意见", "autotest");
    	ticketApp.clickElement("意见反馈", "提交");
    	assertEqual("意见反馈", "标题");
    	ticketApp.clear("意见反馈", "手机");
    	ticketApp.sendKeys("意见反馈", "手机", "186278026811");
    	ticketApp.clickElement("意见反馈", "提交");
    	assertEqual("意见反馈", "标题");
    	ticketApp.clear("意见反馈", "手机");
    	ticketApp.clickElement("意见反馈", "提交");
    	assertEqual("意见反馈", "标题");
    	ticketApp.logSuccessMessage("手机号合法性验证通过");
    }
    
    @Test
    public void test003checkEmail(){
    	ticketApp.logTestDescription("验证邮箱合法性");
    	ticketApp.sendKeys("意见反馈", "手机", "18627802681");
    	ticketApp.clear("意见反馈", "电子邮箱");
    	ticketApp.sendKeys("意见反馈", "电子邮箱", "59853844@");
    	ticketApp.clickElement("意见反馈", "提交");
    	assertEqual("意见反馈", "标题");
    	ticketApp.clear("意见反馈", "电子邮箱");
    	ticketApp.clickElement("意见反馈", "提交");
    	assertEqual("意见反馈", "标题");
    	ticketApp.logSuccessMessage("邮箱合法性验证通过");
    }
    @Test
    public void test004checkContent(){
    	ticketApp.logTestDescription("验证内容合法性");
    	ticketApp.sendKeys("意见反馈", "电子邮箱", "59853844@qq.com");
    	ticketApp.clear("意见反馈", "反馈意见");
    	ticketApp.clickElement("意见反馈", "提交");
    	assertEqual("意见反馈", "标题");
    	ticketApp.clear("意见反馈", "反馈意见");
    	String content501 = CommonTools.getStr(100, "autot")+"1";
    	ticketApp.sendKeys("意见反馈", "反馈意见", content501);
    	String content = ticketApp.getElementText("意见反馈", "反馈意见");
    	if(content.length()==500){
    		ticketApp.log("The max boundary is 500");

    	}
    	else if(content.length()==501){
        	ticketApp.clickElement("意见反馈", "提交");
        	assertEqual("意见反馈", "标题");
    	}
    	else{
    		ticketApp.assertFail("The max boundary is not 500");
    	}
    	ticketApp.logSuccessMessage("内容合法性验证通过");


    }
    @Test
    public void test005checkSubmit(){
    	ticketApp.logTestDescription("检查提交意见功能");
    	ticketApp.clickElement("标题栏", "返回");
    	ticketApp.waitDisplay("我的", "意见反馈");
    	ticketApp.clickElement("我的", "意见反馈");  
    	ticketApp.sendKeys("意见反馈", "手机", "18627802681");
    	ticketApp.sendKeys("意见反馈", "电子邮箱", "59853844@qq.com");
    	String content500 = CommonTools.getStr(100, "autot");
    	ticketApp.sendKeys("意见反馈", "反馈意见", content500);
    	String content = ticketApp.getElementText("意见反馈", "反馈意见");
    	if(content.length()==500){
        	ticketApp.clickElement("意见反馈", "提交");
        	ticketApp.waitDisplay("我的", "意见反馈");
        	ticketApp.assertElementContains("我的", "意见反馈");
    	}
    	else{
    		ticketApp.assertFail("The max boundary is not 500");
    	}
    	ticketApp.logSuccessMessage("提交意见功能验证通过");
    }
    @Test
    public void test006verifyOnWeb(){
    	ticketApp.logTestDescription("在后台验证是否提交到后台");
    	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runFirefoxApp();	
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.waitDisplay("侧边栏", "系统管理");
		piaoWuWebApp.clickElement("侧边栏", "系统管理");
		piaoWuWebApp.waitDisplay("侧边栏", "用户反馈");
		piaoWuWebApp.clickElement("侧边栏", "用户反馈");
		
		piaoWuWebApp.switchToFrame(piaoWuWebApp.findElement("用户反馈", "frame"));
		piaoWuWebApp.waitDisplay("用户反馈", "电话");
		
		String content500 = CommonTools.getStr(100, "autot");
		String phone = piaoWuWebApp.getElementText("用户反馈", "电话");
		piaoWuWebApp.assertEquals(phone, "18627802681");
		String actual = piaoWuWebApp.getElementText("用户反馈", "内容");
		piaoWuWebApp.assertEquals(actual, content500);
		piaoWuWebApp.quitWithoutTestData();
		ticketApp.logSuccessMessage("后台验证通过");

    }
}
