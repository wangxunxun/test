package projects.ticket.testCases.android;







import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import projects.ticket.frame.ticketAndroid;
import test.autotest.utils.CommonTools;


public class buyQiChePiao{
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeClass
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();
		ticketApp.logClassInfo("测试购买汽车票");
	}

    @AfterClass
    public void tearDown(){

    	ticketApp.quit();
    }
    
    


    @Test
    public void test001enterMainPage(){
    	ticketApp.logTestDescription("进入主页");
    	ticketApp.enterMainPage();
    	ticketApp.checkUpdate();
    	ticketApp.logSuccessMessage("成功进入");

    	

    }
    @Test
    public void test002search(){
    	ticketApp.logTestDescription("输入起始站和终点站进行搜索");
    	ticketApp.clickElement("汽车票", "起始站");
    	ticketApp.sendKeys("选择出发城市", "搜索输入框","XN");
    	ticketApp.clickElmentByName("咸宁");
    	ticketApp.clickElement("汽车票", "终点站");
    	ticketApp.sendKeys("选择出发城市", "搜索输入框","SZ");
    	ticketApp.clickElmentByName("深圳");
    	ticketApp.clickElement("汽车票", "乘车日期");
    	ticketApp.sleep(3000);
    	int nextDay = CommonTools.getNextDay();
    	if(nextDay ==1){
    		ticketApp.swipeLeft();
    		ticketApp.clickElmentByName(String.valueOf(nextDay));
    	}
    	else{
    		ticketApp.clickElmentByName(String.valueOf(nextDay));
    	}
    	
    	ticketApp.waitDisplay("汽车票", "查询");
    	ticketApp.clickElement("汽车票", "查询");
    	ticketApp.waitDisplay("汽车线路列表", "终点");
    	ticketApp.clickElmentByName("南山汽车站");
    	ticketApp.waitDisplay("汽车班次列表", "终点");
    	ticketApp.clickElmentByName("18:00");
    	ticketApp.logSuccessMessage("成功搜索");
    }
    @Test
    public void test003loginAndChoosePerson(){
    	ticketApp.logTestDescription("登录并选择乘车人");
    	ticketApp.login();
    	ticketApp.waitDisplay("汽车班次列表", "终点");
    	ticketApp.clickElmentByName("18:00");
    	ticketApp.waitDisplay("汽车填写订单","添加乘车人");
    	ticketApp.clickElement("汽车填写订单","添加乘车人");
    	if(ticketApp.verifyDisplay("常用联系人", "姓名")){
    		ticketApp.clickElement("常用联系人", "姓名");
    		ticketApp.clickElement("常用联系人", "确定");
    	}
    	else{
    		ticketApp.clickElement("常用联系人", "添加");
    		ticketApp.waitDisplay("新增联系人","姓名");
    		ticketApp.sendKeys("新增联系人","姓名", "王勋");
    		ticketApp.sendKeys("新增联系人","手机号", "18627802681");
    		ticketApp.clickElement("标题栏", "保存");
    		ticketApp.clickElement("常用联系人", "姓名");
    		ticketApp.clickElement("常用联系人", "确定");
    	}
    	ticketApp.logSuccessMessage("成功登录并选中一个乘车人");
    }
    @Test
    public void test004buyTicket(){
    	ticketApp.logTestDescription("分别选择微信和支付宝支付");
    	ticketApp.waitDisplay("汽车填写订单","添加乘车人");
    	ticketApp.clickElement("汽车填写订单","提交订单");
    	ticketApp.waitDisplay("汽车支付","立即支付");
    	ticketApp.clickElement("汽车支付","立即支付");
    	ticketApp.waitDisplay("微信支付","返回");
    	ticketApp.clickElement("微信支付","返回");
    	ticketApp.waitDisplay("汽车支付","立即支付");
    	ticketApp.clickElement("汽车支付","支付宝支付");
    	ticketApp.clickElement("汽车支付","立即支付");
    	ticketApp.waitDisplay("支付宝支付","返回");
    	ticketApp.sleep(10000);
    	ticketApp.clickBack();

    	ticketApp.clickBack();
    	ticketApp.sleep(3000);
    	ticketApp.logSuccessMessage("成功支付");
    	
    }
    
    
}