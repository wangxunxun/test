package test.autotest.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import test.autotest.utils.CommonTools;
import test.autotest.utils.ReadElementData;
import test.autotest.utils.ReadTestCasesData;
import test.autotest.utils.ReadTestData;

public class Initial {

	protected int waitTime;
	// 测试项目配置文件（必填）
	protected String configFilePath;
	// 测试数据存储路径（必填）
	private String testDataExcelPath;
	protected String testCaseExcelPath;
	// 元素定位对应sheet名（必填）
	private String elementSheet;
	// 测试用例对应sheet名
	protected String testCaseSheet;
	// app存放文件夹（android项目必填）
	protected String appDir;
	// andorid配置信息
	protected int basicWindowX;
	protected int basicWindowY;
	protected boolean unicodeKeyboard;
	protected boolean resetKeyboard;
	// android项目必填
	protected String androidDeviceName;
	// android项目必填
	protected String apkName;
	// android项目必填
	protected String appPackage;
	// android项目必填
	protected String mainActivity;

	// IOS配置信息（必填）
	protected String iosApp;
	protected String platformVersion;
	protected String platform;
	protected String platformName;
	protected String browserName;
	protected String iosDeviceName;

	// 截屏存放路径
	protected String screenDir;
	// log存放路径
	private String logDir;

	// 测试报告存储文件夹
	public static String testReportDir;
	// 测试报告名称
	protected String testReportName;
	// 当前运行测试class的类名
	protected String testClassName;

	// 元素定位对象
	protected Map<String, Map<String, Map<String, String>>> elementData;
	// 测试用例对象
	protected Map<String, Object> testCaseData;
	// 测试app的对象名
	protected String appClass;
	// 回写用例脚本开关
	protected boolean writeScript;
	// 回写测试结果开关
	protected boolean writeResult;
	// log开关
	private boolean logSwitch;
	// 每次运行时是否删除log.text文件开关
	private boolean deleteLogFileFirst;

	// 测试报告testsummary sheet名称
	protected String testSummarySheetName;

	// 测试项目名称
	private String projectName;
	// 测试项目简介
	private String projectInfo;
	// 测试范围
	private String testSpecification;

	// 每个case执行成功后的message
	public static String successMessage;
	// 每个case的介绍
	public static String caseInfo;
	// 每个class的介绍
	public static String classInfo;
	// 测试报告名称中转变量
	public static List<String> reportName = new ArrayList<String>();
	// 测试脚本数据
	public static List<List<String>> testScriptData = new ArrayList<List<String>>();
	// 测试结果数据
	public static List<List<String>> testResultData = new ArrayList<List<String>>();
	// log数据
	public static List<List<String>> logData = new ArrayList<List<String>>();
	// 测试app类型
	public static String testAppType;
	protected String coreFilePath;
	protected String envName;
	protected String firefoxPath;
	private String reportDirPathNoTime;
	private String currentTime;

	protected String getFirefoxPath() {
		firefoxPath = getProperties("firefoxPath");
		if (firefoxPath != null) {
			return firefoxPath;
		}

		return "";
	}

	protected String getTestReportDir() {
		testReportDir = getProperties("testReportDir");
		if (testReportDir != null) {
			return CommonTools.setPath(testReportDir);
		}

		return CommonTools.setPath("/testReport/"+projectName+"/"+projectName+"TestReport"+currentTime+"/");
	}
	protected String getTestReportDirNoTime() {
		testReportDir = getProperties("testReportDir");
		if (testReportDir != null) {
			return CommonTools.setPath(testReportDir);
		}

		return CommonTools.setPath("/testReport/"+projectName+"/");
	}

	protected String getProjectName() {
		projectName = getProperties("projectName");
		if (projectName != null) {
			return projectName;
		}

		return "defaultProject";
	}

	protected String getProjectInfo() {
		projectInfo = getProperties("projectInfo");
		if (projectInfo != null) {
			return projectInfo;
		}

		return "";
	}

	protected String getTestSpecification() {
		testSpecification = getProperties("testSpecification");
		if (testSpecification != null) {
			return testSpecification;
		}

		return "";
	}

	protected String getTestSummarySheetName() {
		testSummarySheetName = getProperties("testSummarySheetName");
		if (testSummarySheetName != null) {
			return testSummarySheetName;
		}

		return "TestSummary";

	}

	protected String getTestReportName() {
		testReportName = getProperties("testReportName");
		if (testReportName != null) {
			testReportName = testReportName + "(" + currentTime+ ")";
			reportName.add(testReportName);
			return reportName.get(0);
		}
		String name = "TestReport" + "(" + currentTime + ")";
		reportName.add(name);
		return reportName.get(0);
	}

	protected Map<String, Map<String, Map<String, String>>> getElementData() {
		ReadElementData elementdata = new ReadElementData(testDataExcelPath, elementSheet);
		Map<String, Map<String, Map<String, String>>> eledata = null;
		try {
			eledata = elementdata.getdata();
		} catch (Exception e) {
			Assert.fail("Fail to get the element data.\n");
		}
		return eledata;
	}

	public void setThirdAppElementData(String excelPath, String elementSheet) {
		ReadElementData elementdata = new ReadElementData(excelPath, elementSheet);
		Map<String, Map<String, Map<String, String>>> eledata = null;
		try {
			eledata = elementdata.getdata();
		} catch (Exception e) {
			Assert.fail("Fail to get the element data.\n");
		}
		elementData = eledata;

	}

	public void setMainAppElementData() {
		elementData = getElementData();
	}

	public List<Map<String, String>> getTestData(String testDataSheet) {

		ReadTestData readtestdata = new ReadTestData();
		List<Map<String, String>> data = null;
		try {
			data = readtestdata.getTestData(testDataExcelPath, testDataSheet);
		} catch (Exception e) {
			Assert.fail("Fail to get the test data.\n");
		}
		return data;
	}

	public Object[][] getTestDataForTestNG(String testDataSheet) {

		ReadTestData readtestdata = new ReadTestData();
		Object[][] data = null;
		try {
			data = readtestdata.getTestDataForTestNG(testDataExcelPath, testDataSheet);
		} catch (Exception e) {
			Assert.fail("Fail to get the test data for testNG.\n");
		}
		return data;
	}

	protected Map<String, Object> getTestCaseData() {
		if (testCaseSheet != null) {
			ReadTestCasesData testCaseData = new ReadTestCasesData(testCaseExcelPath, testCaseSheet);
			Map<String, Object> data = null;
			try {
				data = testCaseData.getdata();
			} catch (Exception e) {
				Assert.fail("Fail to get the test case data .\n");
			}
			return data;
		}
		return null;

	}

	protected String getAppClass() {
		appClass = getProperties("appClass");
		if (appClass == null) {
			return "defaultApp";
		}
		return appClass;

	}

	protected int getBasicWindowX() {
		String x = getProperties("basicWindowX");
		if (x != null) {
			basicWindowX = Integer.parseInt(x);
			return basicWindowX;
		}
		return 720;
	}

	protected int getBasicWindowY() {
		String y = getProperties("basicWindowY");
		if (y != null) {
			basicWindowY = Integer.parseInt(y);
			return basicWindowY;
		}
		return 1280;
	}

	protected int getWaitTime() {
		String time = getProperties("waitTime");
		if (time != null) {
			waitTime = Integer.parseInt(time);
			return waitTime;
		}
		return 1;
	}

	protected String getScreenDir() {
		screenDir = getProperties("screenDir");
		if (screenDir != null) {
			return CommonTools.setPath(screenDir);
		}

		return CommonTools.setPath("/screenShot/");

	}

	protected String getAppDir() {
		appDir = getProperties("appDir");
		if (appDir != null) {
			return CommonTools.setPath(appDir);
		}
		return CommonTools.setPath("/testResource/apps/");

	}

	protected String getlogDir() {
		logDir = getProperties("logDir");
		if (logDir != null) {
			return CommonTools.setPath(logDir);
		}
		return CommonTools.setPath("/logDefaultFolder/");

	}

	public String getProperties(String name) {
		if (configFilePath != null) {
			return CommonTools.getProperties(configFilePath, name);
		}
		return null;
	}

	protected void logResult(Integer row) {

		if (logSwitch == true) {
			String content = "The " + row + "th step is pass.";
			CommonTools.log(content);
		}
	}

	public void log(Object content) {
		if (logSwitch == true) {
			String time = CommonTools.getCurrentTime();
			System.out.println(time + " INFO - " + content);
			// writeLog(time + " INFO - " + content);
			writeLogToExcel(time + " INFO - " + content);
		}
	}

	protected void deleteFirstTime(String filePath) {
		if (deleteLogFileFirst == true) {
			CommonTools.deleteFile(filePath);
			deleteLogFileFirst = false;
		}
	}

	protected String getLogFilePath() {
		return logDir + getClassName() + ".txt";
	}

	protected void writeLog(Object content) {
		deleteFirstTime(getLogFilePath());
		writeLog(getClassName() + ".txt", content);
	}

	protected void writeLogToExcel(Object content) {

		try {
			putLogData("", (String) content);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	protected String getClassName() {
		try {
			ITestResult it = Reporter.getCurrentTestResult();
			String classNamePath = it.getTestClass().getName();

			return classNamePath;
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return "";
	}

	protected String getTestMethodName() {
		try {
			ITestResult it = Reporter.getCurrentTestResult();
			String testMethodName = it.getName();

			return testMethodName;
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return "";
	}

	protected void putScriptData(Integer row, String script) {
		String rowString = String.valueOf(row);
		List<String> result = new ArrayList<String>();
		result.add(rowString);
		result.add(script);
		testScriptData.add(result);
	}

	protected void putResultData(Integer row, String result) {
		String rowString = String.valueOf(row);
		List<String> data = new ArrayList<String>();
		data.add(rowString);
		data.add(result);
		testResultData.add(data);
	}

	protected void putLogData(String title, String info) {
		List<String> data = new ArrayList<String>();
		data.add(title);
		data.add(info);
		logData.add(data);
	}

	public void logTestDescription(String content) {
		try {
			ITestResult it = Reporter.getCurrentTestResult();
			String testMethod = it.getName();
			putLogData(testMethod, "Test case description: " + content);
			caseInfo = content;
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void logSuccessMessage(String content) {
		successMessage = content;
		try {
			putLogData("", "Success info: " + content);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void logClassInfo(String content) {
		try {
			putLogData("ClassName", testClassName);
			putLogData("Description", content);
			classInfo = content;
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	protected void createWorkBook(String className, int index) {

		File f = new File(testReportDir + testReportName + ".xls");
		if (!f.exists()) {
			try {
				CommonTools.createWorkbook(testReportDir, testReportName + ".xls", className, index, projectName,
						projectInfo, testSpecification);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

	}

	protected void createSheet(int index) throws WriteException, BiffException, IOException {
		if (!CommonTools.verifySheet(testReportDir + testReportName + ".xls", testClassName)) {
			CommonTools.createSheet(testReportDir + testReportName + ".xls", testClassName, index);
		}
	}

	protected void deleteSheet(String sheetName) {
		try {
			CommonTools.deleteSheet(testReportDir + testReportName + ".xls", sheetName);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	protected void writeLog(String fileName, Object content) {
		FileWriter writer;
		try {
			if (!(new File(logDir).isDirectory())) { // 判断是否存在该目录
				new File(logDir).mkdir(); // 如果不存在则新建一个目录
			}
			writer = new FileWriter(logDir + fileName, true);
			writer.write(content + "\r\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}

	}

	protected String getTestClassName() {
		String className = getClassName();
		String[] ddd = className.split("\\.");
		return CommonTools.getValidSheetName(ddd[ddd.length - 2] + "." + ddd[ddd.length - 1]);

	}

	protected String getInitialPropertiesPath(String coreFilePath, String key) {
		if (coreFilePath != null) {
			coreFilePath = CommonTools.setPath(coreFilePath);
			Properties corePro = CommonTools.getConfigFormatData(coreFilePath);
			String newConfigFilePath = CommonTools.getConfigValue(corePro, key);
			return newConfigFilePath;
		} else {
			return configFilePath;
		}
	}

	protected void initialData() throws WriteException, BiffException, IOException {

		configFilePath = getInitialPropertiesPath(coreFilePath, envName);
		testDataExcelPath = getProperties("testDataExcelPath");
		testCaseExcelPath = getProperties("testCaseExcelPath");
		elementSheet = getProperties("elementSheet");
		testCaseSheet = getProperties("testCaseSheet");
		projectName = getProjectName();
		projectInfo = getProjectInfo();
		testSpecification = getTestSpecification();
		firefoxPath = getFirefoxPath();

		elementData = getElementData();
		testCaseData = getTestCaseData();
		waitTime = getWaitTime();

		logDir = getlogDir();
		screenDir = getScreenDir();
		appClass = getAppClass();
		reportDirPathNoTime = getTestReportDirNoTime();
		currentTime = CommonTools.getCurrentTime();
		writeScript = Boolean.parseBoolean(getProperties("writeScript"));
		writeResult = Boolean.parseBoolean(getProperties("writeResult"));
		logSwitch = Boolean.parseBoolean(getProperties("logSwitch"));
		deleteLogFileFirst = Boolean.parseBoolean(getProperties("deleteLogFileFirst"));
		testClassName = getTestClassName();
		appDir = getAppDir();
		testReportDir = getTestReportDir();
		testReportName = getTestReportName();
		testSummarySheetName = getTestSummarySheetName();
		CommonTools.keepDirCount(reportDirPathNoTime, 4);
		createWorkBook(testSummarySheetName, 0);
		createSheet(999);
		testAppType = "web";

	}

	protected void initialAndroidData() throws WriteException, BiffException, IOException {
		initialData();
		androidDeviceName = getProperties("deviceName");
		apkName = getProperties("apkName");
		appPackage = getProperties("appPackage");
		mainActivity = getProperties("mainActivity");
		basicWindowX = getBasicWindowX();
		basicWindowY = getBasicWindowY();
		unicodeKeyboard = Boolean.parseBoolean(getProperties("unicodeKeyboard"));
		resetKeyboard = Boolean.parseBoolean(getProperties("resetKeyboard"));
		testAppType = "android";
	}

	protected void initialIOSData() throws WriteException, BiffException, IOException {
		initialData();
		iosApp = getProperties("iosApp");
		iosDeviceName = getProperties("iosDeviceName");
		platformVersion = getProperties("platformVersion");
		platform = getProperties("platform");
		platformName = getProperties("platformName");
		browserName = getProperties("browserName");
		testAppType = "ios";
	}

}
