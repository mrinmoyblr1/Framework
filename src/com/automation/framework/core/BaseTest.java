package com.automation.framework.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.XLUtil;

/**
 * @author manoj
 *
 */
public class BaseTest {

	public final static Logger logger = Logger.getLogger(BaseTest.class);
	private DriverScript driverScript;

	/**
	 * to intialize the driver script
	 * 
	 * @param almID
	 * @param testDriver
	 * @param browser
	 * 
	 * @throws DriverScriptException
	 */
	public BaseTest() throws DriverScriptException {

	}

	public BaseTest(String almID, TestDriver testDriver, String browser) throws DriverScriptException {

		initializeDriver(almID, testDriver, browser);

	}

	/**
	 * @throws DriverScriptException @param browser @throws IOException @param
	 * almID @throws DriverScriptException Initialize driver.
	 * 
	 * @throws
	 */
	private void initializeDriver(String almID, TestDriver testDriver, String browser) throws DriverScriptException {
		driverScript = new DriverScript(almID);
		Map<String, String> globalParamMap ;
		testDriver.setAlmID(almID);
		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver, "info", "initializeDriver method: Start");
		}
		
		// Review
		if(testDriver.isCucumber()){
			globalParamMap = driverScript.getGlobalConfigPropertiesMap(testDriver, "config//GlobalParameter.properties",
					browser);
			
		}else{
			
		
			globalParamMap = driverScript.getGlobalConfigMap(testDriver, "config//GlobalParameter.xlsx",
					browser);
		}
			testDriver.setGlobalParamMap(globalParamMap);
			String platform = globalParamMap.get("platform");
			if(platform.contains(",")){
				String[] platformSplit = platform.split(",");
				
				for (int i = 0; i < platformSplit.length; i++) {
					
					if(platformSplit[i].toLowerCase().equals("mobile") && DriverScript.mobileMedium!=null){
							browser =globalParamMap.get("mobilemedium");  //DriverScript.mobileMedium;
					}else if (platformSplit[i].toLowerCase().equals("web")){
						browser = globalParamMap.get("webmedium");
					}
					else if (platformSplit[i].toLowerCase().equals("webservices")){
						browser = globalParamMap.get("webservicesmedium");
					}
					globalLaunchDrivers(testDriver, browser, globalParamMap, platformSplit[i]);
				}
			}else{
				if(platform.toLowerCase().equals("mobile")){
					globalLaunchDrivers(testDriver, globalParamMap.get("mobilemedium"), globalParamMap, platform);
				}
				else if(platform.toLowerCase().equals("webservices")){
					globalLaunchDrivers(testDriver, globalParamMap.get("webservicemedium"), globalParamMap, platform);
				}
				else if(platform.toLowerCase().equals("web")){
				globalLaunchDrivers(testDriver, globalParamMap.get("webmedium"), globalParamMap, platform);
				}
			}
			driverScript.setSoapLoggerManager();
			testDriver.setMapValues(new HashMap<String, String>());
			testDriver.setMapWebObject(new HashMap<String, Map<String, JSONObject>>());
			testDriver.setMapMobileObject(new HashMap<String, Map<String, JSONObject>>());
			
					
	}

	public static void loadTestData(String rawFeatureName, String scenarioName, TestDriver testDriver) throws DriverScriptException{
		if(testDriver.isCucumber()){
			File testDataExcel = new File(testDriver.getGlobalParamMap().get("testDataExcelPath"));
			XLUtil xlUtil = new XLUtil();
			xlUtil.openWorkBook(testDriver, testDataExcel);
			Map<String, String> testData = new HashMap<String, String>();
			System.out.println(scenarioName);
			for (int i = 0; i < xlUtil.getTotalRowCount(testDriver, rawFeatureName)+1; i++) {	
				System.out.println(xlUtil.getCellValue(testDriver, rawFeatureName, i, 0));
				if (scenarioName.equalsIgnoreCase(xlUtil.getCellValue(testDriver, rawFeatureName, i, 0))) {
					for (int j = 1; j < xlUtil.getTotalColumnCount(testDriver, rawFeatureName, i)-1; j+=2) {
						String key = xlUtil.getCellValue(testDriver, rawFeatureName, i, j);
						System.out.println(key);
		
						String value = xlUtil.getCellValue(testDriver, rawFeatureName, i, j+1);		
						System.out.println(value);
						testData.put(key, value);

					}
					 System.out.println("testData "+testData);
					 testDriver.setScenarioTestData(testData);
					 
				}
			
			}
			
		}
	}
	private void globalLaunchDrivers(TestDriver testDriver, String browser,
			Map<String, String> globalParamMap, String platform)
			throws DriverScriptException {
		switch (platform) {
		case "Web":
			initWeb(testDriver, globalParamMap,browser);
			break;
		case "Mobile":
			initMobile(testDriver, globalParamMap,browser);
			break;
		case "WebServices":
			initWebService(testDriver, globalParamMap,browser);
			break;
		default:
			DriverScript.logMessage(testDriver,"testStepDriverFail",
					"Invalid platform");
			throw new DriverScriptException(
					"Invalid platform");
		}
	}

	private void initMobile(TestDriver testDriver,
			Map<String, String> globalParamMap, String browser) throws DriverScriptException {
		String mobileMedium = globalParamMap.get("mobilemedium");
		testDriver.setMobileMedium(mobileMedium);
		if(browser!=null && browser.length()>0){
			testDriver.setMobileMedium(browser);
		}
		driverScript.initializeMobileTestBaseSetup(testDriver);
		driverScript.launchApplication(testDriver);
		
	}

	private void initWeb(TestDriver testDriver,
			Map<String, String> globalParamMap, String browser) throws DriverScriptException {
		String webMedium = globalParamMap.get("webmedium");
		testDriver.setWebMedium(webMedium);
		if(browser!=null && browser.length()>0){
			testDriver.setWebMedium(browser);
		}
		driverScript.initializeWebTestBaseSetup(testDriver);
		driverScript.launchApplication(testDriver);
	}
	
	private void initWebService(TestDriver testDriver,
			Map<String, String> globalParamMap, String browser) throws DriverScriptException {
		String webservicemedium = globalParamMap.get("webservicemedium");
		testDriver.setWebServiceMedium(webservicemedium);
		if(browser!=null && browser.length()>0){
			testDriver.setWebServiceMedium(browser);
		}
		
	}
	

}
