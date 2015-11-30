package projects.ticket.testCases.android;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import projects.ticket.frame.ticketAndroid;
import test.autotest.utils.CommonTools;

public class contacts {
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeClass
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();
		ticketApp.logClassInfo("测试常用联系人");
	}

    @AfterClass
    public void tearDown(){

    	ticketApp.quit();
    }
    @Test
    public void test001enterContactsPage(){
    	ticketApp.logTestDescription("进入联系人页面");
    	ticketApp.enterMainPage();
    	ticketApp.clickElement("首页", "我的");
    	ticketApp.waitDisplay("我的", "常用联系人");
    	ticketApp.clickElement("我的", "常用联系人");  
    	ticketApp.login();
    	ticketApp.waitDisplay("我的", "常用联系人");
    	ticketApp.clickElement("我的", "常用联系人");  
    	ticketApp.logSuccessMessage("成功进入联系人页面");
	
    }
    
    @Test
    public void test002checkName(){
    	ticketApp.logTestDescription("检查姓名合法性");
		ticketApp.clickElement("常用联系人", "添加");
		ticketApp.waitDisplay("新增联系人","姓名");

		ticketApp.sendKeys("新增联系人","手机号", "18627802681");
		ticketApp.clickElement("标题栏", "保存");
		ticketApp.assertElementContains("新增联系人","标题");
		String name = CommonTools.getStr(100, "t")+"2";
		ticketApp.sendKeys("新增联系人","姓名", name);
		String actual = ticketApp.getElementText("新增联系人","姓名");
		if(actual.length()==100){
			ticketApp.log("姓名的最大边界为100个字符");
		}
		else if(actual.length()>100){
			ticketApp.clickElement("标题栏", "保存");
			ticketApp.assertElementContains("新增联系人","标题");
		}
		else{
			ticketApp.assertFail("姓名的最大边界不为100个字符");
		}
		ticketApp.logSuccessMessage("姓名合法性验证通过");
    }
    @Test
    public void test003checkPhone(){
    	ticketApp.logTestDescription("检查手机号合法性");
		ticketApp.clear("新增联系人","姓名");
		ticketApp.sendKeys("新增联系人","姓名", "王勋");
		ticketApp.clear("新增联系人","手机号");
		ticketApp.sendKeys("新增联系人","手机号", "1");
		ticketApp.clickElement("标题栏", "保存");
		ticketApp.assertElementContains("新增联系人","标题");
		ticketApp.clear("新增联系人","手机号");
		ticketApp.sendKeys("新增联系人","手机号", "186278026811");
		ticketApp.clickElement("标题栏", "保存");
		ticketApp.assertElementContains("新增联系人","标题");
		ticketApp.logSuccessMessage("手机号合法性验证通过");
    }
    @Test
    public void test004checkID(){
    	ticketApp.logTestDescription("检查身份证合法性");
		ticketApp.clear("新增联系人","手机号");
		ticketApp.sendKeys("新增联系人","手机号", "18627802681");
		ticketApp.sendKeys("新增联系人","身份证号", "11000019810419093Xx");
		ticketApp.clickElement("标题栏", "保存");
		ticketApp.assertElementContains("新增联系人","标题");
		ticketApp.clear("新增联系人","身份证号");
		ticketApp.sendKeys("新增联系人","身份证号", "110");
		ticketApp.clickElement("标题栏", "保存");
		ticketApp.assertElementContains("新增联系人","标题");
		ticketApp.logSuccessMessage("身份证合法性验证通过");
    }
    @Test
    public void test005addContact(){
    	ticketApp.logTestDescription("检查添加联系人");
		ticketApp.clear("新增联系人","身份证号");
		ticketApp.sendKeys("新增联系人","身份证号", "11000019810419093X");
		ticketApp.clickElement("标题栏", "保存");
		ticketApp.waitDisplay("常用联系人", "姓名");
		ticketApp.assertContains(ticketApp.getElementText("常用联系人", "身份证"), "11000019810419093X");
		ticketApp.logSuccessMessage("添加联系人功能验证通过");
    }
    
    @Test
    public void test006deleteContact(){
    	ticketApp.logTestDescription("检查删除联系人");
    	if(ticketApp.verifyDisplay("常用联系人", "姓名")){
    	int size = ticketApp.findElements("常用联系人", "姓名").size();
        	ticketApp.longTapElementByXY("常用联系人", "姓名");
        	ticketApp.waitDisplay("常用联系人", "删除");
        	ticketApp.clickElement("常用联系人", "删除");
        	int newsize = ticketApp.findElements("常用联系人", "姓名").size();
        	if(newsize+1 !=size){
        		ticketApp.assertFail("删除失败");
        	}
    	}
    	else{
    		ticketApp.assertFail("没有联系人");
    	}
    	ticketApp.logSuccessMessage("删除联系人验证通过");
    }
    
}
