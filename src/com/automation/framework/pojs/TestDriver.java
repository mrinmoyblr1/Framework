package com.automation.framework.pojs;

import java.util.Hashtable;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class TestDriver {
	private Map<String, String> globalParamMap;
	private AppiumDriver mobileDriver;
	private WebDriver webDriver;
	private Map<String, Hashtable<String, String>> businessActionsMap;
	private Map<String, String> testData1;
	private Map<String, String> mapValues;
	private Map<String, Map<String, JSONObject>> mapWebObject;
	private Map<String, Map<String, JSONObject>> mapMobileObject;
	private ExtentTest loggers;
	private ExtentReports extentReports;
	private String webMedium;
	private String mobileMedium;
	private String webServiceMedium;
	private AppiumDriverLocalService appiumProcess;
	private boolean executed;
	private String almID;
	private boolean webFailure;
	private boolean mobileFailure;
	private boolean skipped;
	private String testName;
	private String currentPage;
	private boolean scriptInit;
	private Map<String, Response> mapValuesResponse;
	private Response response;
	private boolean cucumber;

	
	public boolean isCucumber() {
		return cucumber;
	}

	public void setCucumber(boolean cucumber) {
		this.cucumber = cucumber;
	}

	public Map<String, String> getGlobalParamMap() {
		return globalParamMap;
	}

	public void setGlobalParamMap(Map<String, String> globalParamMap) {
		this.globalParamMap = globalParamMap;
	}

	public AppiumDriver getMobileDriver() {
		return mobileDriver;
	}

	public void setMobileDriver(AppiumDriver mobileDriver) {
		this.mobileDriver = mobileDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public Map<String, Hashtable<String, String>> getBusinessActionsMap() {
		return businessActionsMap;
	}

	public void setBusinessActionsMap(Map<String, Hashtable<String, String>> businessActionsMap) {
		this.businessActionsMap = businessActionsMap;
	}

	public Map<String, String> getMapValues() {
		return mapValues;
	}

	public void setMapValues(Map<String, String> mapValues) {
		this.mapValues = mapValues;
	}

	/*public Map<String, JSONObject> getMapObject() {
		return mapObject;
	}

	public void setMapObject(Map<String, JSONObject> mapObject) {
		this.mapObject = mapObject;
	}*/

	public ExtentTest getLoggers() {
		return loggers;
	}

	public void setLoggers(ExtentTest loggers) {
		this.loggers = loggers;
	}

	public ExtentReports getExtentReports() {
		return extentReports;
	}

	public void setExtentReports(ExtentReports extentReports) {
		this.extentReports = extentReports;
	}


	public AppiumDriverLocalService getAppiumProcess() {
		return appiumProcess;
	}

	public void setAppiumProcess(AppiumDriverLocalService appiumProcess) {
		this.appiumProcess = appiumProcess;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public String getAlmID() {
		return almID;
	}

	public void setAlmID(String almID) {
		this.almID = almID;
	}

	public String getWebMedium() {
		return webMedium;
	}

	public void setWebMedium(String webMedium) {
		this.webMedium = webMedium;
	}

	public String getMobileMedium() {
		return mobileMedium;
	}

	public void setMobileMedium(String mobileMedium) {
		this.mobileMedium = mobileMedium;
	}


	public boolean isWebFailure() {
		return webFailure;
	}

	public void setWebFailure(boolean webFailure) {
		this.webFailure = webFailure;
	}

	public boolean isMobileFailure() {
		return mobileFailure;
	}

	public void setMobileFailure(boolean mobileFailure) {
		this.mobileFailure = mobileFailure;
	}

	public boolean isSkipped() {
		return skipped;
	}

	public void setSkipped(boolean skipped) {
		this.skipped = skipped;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Map<String, Map<String, JSONObject>> getMapWebObject() {
		return mapWebObject;
	}

	public void setMapWebObject(Map<String, Map<String, JSONObject>> mapWebObject) {
		this.mapWebObject = mapWebObject;
	}

	public Map<String, Map<String, JSONObject>> getMapMobileObject() {
		return mapMobileObject;
	}

	public void setMapMobileObject(Map<String, Map<String, JSONObject>> mapMobileObject) {
		this.mapMobileObject = mapMobileObject;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isScriptInit() {
		return scriptInit;
	}

	public void setScriptInit(boolean objectFailure) {
		this.scriptInit = objectFailure;
	}

	public String getWebServiceMedium() {
		return webServiceMedium;
	}

	public void setWebServiceMedium(String webServiceMedium) {
		this.webServiceMedium = webServiceMedium;
	}

	public Map<String, Response> getMapValuesResponse() {
		return mapValuesResponse;
	}

	public void setMapValuesResponse(Map<String, Response> mapValuesResponse) {
		this.mapValuesResponse = mapValuesResponse;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void setScenarioTestData(Map<String, String> testData) {
		this.testData1 = testData;
		
	}
	
	public Map<String, String> getScenarioTestData() {
		return testData1;
		
	}
	


	

}
