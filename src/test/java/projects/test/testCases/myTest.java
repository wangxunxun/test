package projects.test.testCases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projects.test.frame.myTestAndroid;


@Listeners(test.autotest.utils.TestngListener.class)
public class myTest {
	myTestAndroid myTest = new myTestAndroid();
	@BeforeClass
	public void setUp(){
		myTest.initialTestData();
		
		myTest.runAndroidApp();

	}

    @AfterClass
    public void tearDown(){

    	myTest.quit();
    }
    
    public void start(){
    	while(true){
    		String qq = myTest.getElementText("主页", "登陆");
    		myTest.sleep(2000);
    		if(qq.matches("注销")){
    			myTest.log("start");
    			open();
    		}
    	}
    }
    
    public void open(){
    	myTest.sleep(1000);
    	myTest.startActivity("hk.com.nextmedia.magazine.nextmediaplus.qa", "com.nextmedia.nextplus.userguide.UserGuide");
    	myTest.sleep(1000);
    	myTest.backToMainApp();
    	myTest.clickElement("主页", "按钮");
    }

    
    
    @Test
    public void login(){
    	myTest.sleep(5000);
    	myTest.clickElement("主页", "按钮");
//    	myTest.clickElement("主页", "登陆");
    	start();
/*    	myTest.log(myTest.getCurrentActivity());
    	myTest.sleep(5000);
    	myTest.startActivity("hk.com.nextmedia.magazine.nextmediaplus.qa", "com.nextmedia.nextplus.userguide.UserGuide");
    	myTest.log(myTest.getCurrentActivity());
    	myTest.sleep(5000);
    	myTest.backToMainApp();
    	myTest.log(myTest.getCurrentActivity());
    	myTest.sleep(5000);*/
//    	NPAndroid.runTestCase("进入首页");



    }
}
