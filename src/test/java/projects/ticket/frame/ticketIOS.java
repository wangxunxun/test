package projects.ticket.frame;

import test.autotest.base.IOSApp;

public class ticketIOS extends IOSApp{
	public void initialTestData(){
		configFilePath = "/config/ticketSystem/configIOS.properties";
	}
	

	public void waitDisplayAfterLoading(String page,String name){
		super.waitDisplayAfterLoading(page, name, "公共", "加载");		
	}
	
	public void login(){
    	waitDisplayAfterLoading("登录", "账号");
    	sendKeysAndHideKeyBoard("登录", "账号","18627802681");
    	sendKeysAndHideKeyBoard("登录", "密码","123456");
    	clickElement("登录", "登录");
	}
	
	public void enterMainPage(){
		swipeLeft();
    	swipeLeft();
    	clickElement("启动页", "开启直达车");
	}

}
