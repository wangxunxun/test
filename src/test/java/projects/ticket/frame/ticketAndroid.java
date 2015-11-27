package projects.ticket.frame;

import test.autotest.base.AndroidApp;





public class ticketAndroid extends AndroidApp{

	

	public void initialTestData(){
		configFilePath = "/config/ticketSystem/configAndroid.properties";
	}
    public void enterMainPage(){

    	sleep(10000);
    	waitDisplay("启动页", "首页");
    	swipeOfType("left");
    	sleep(500);
    	swipeOfType("left");
    	getElementExpectedValue("启动页", "开启直达车");
    	assertEqual("启动页", "开启直达车");
    	clickElement("启动页", "开启直达车");
    	
    }
    public void login(){
    	sendKeys("登录", "账户","18627802681");
    	sendKeys("登录", "密码","12345678");
    	clickElement("登录", "普通登录按钮");
    }
}
