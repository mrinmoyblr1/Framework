package com.automation.framework.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.PropertyUtil;
import com.automation.framework.utilities.XLUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author manoj
 *
 */
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverScript {

	public final static Logger logger = Logger.getLogger(DriverScript.class);
	public static Map<String, String> logMap;
	private static String alMID;
	public static Map<String, String> mapClassName;
	public static String testDataFile;
	public static ITestResult result;
	public static String rowNumber;
	public static Map<Integer, String> testsFailMap;
	public static String mobileMedium;
	
	
	public String browser1=null;
	private HashMap<String, String> globalPropMap;
	public static boolean parallel = false;
	public static ExtentReports extentReports; 
	public static Map<String,Integer> testsExcelMap;
	public static String platform;
	public static boolean batchRun;

	/**
	 * @param propertyUtil
	 * @throws IOException
	 */

	public DriverScript(String almID) { 
		DriverScript.alMID = almID;
		mapClassName = new HashMap<String, String>();
	}

	/**
	 * Sets the driver.
	 * @param browser 
	 *
	 * @param browserType
	 *            the new driver
	 * @throws DriverScriptException
	 */
	public void initializeMobileTestBaseSetup(TestDriver testDriver) throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info", "set driver method: Start");
		}
		String browserType =testDriver.getMobileMedium();
		switch (browserType ) {
		case "androidChrome":
			try {
				initAppiumServer(testDriver);
				initAppiumAndriodChromeDriver(testDriver);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			break;
		case "androidNative":
			try {
				initAppiumServer(testDriver);
				initAppiumAndroidNativeDriver(testDriver);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			break;
			
		case "androidHybrid":
			try {
				initAppiumServer(testDriver);
				initAppiumAndroidHybridDriver(testDriver);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			break;
			
		case "iosSafari":
			try {
				initAppiumServer(testDriver);
				initAppiumIOSSafariDriver(testDriver);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			break;
		case "iosNative":
			try {
				initAppiumServer(testDriver);
				initAppiumIOSNativeDriver(testDriver);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			break;
			
		case "iosHybrid":
			try {
				initAppiumServer(testDriver);
				initAppiumIOSHybridDriver(testDriver);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			break;
		
		default:
			DriverScript
            .logMessage(testDriver,
                        "error",
                        "Invalid mobile medium type "+browserType);
				throw new DriverScriptException(
		"Invalid mobile medium type "+browserType);
			
		}
		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info", "set driver method: End");
		}
	}

	
	private void initAppiumIOSNativeDriver(TestDriver testDriver) throws InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, testDriver.getGlobalParamMap().get("deviceOsVersion"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testDriver.getGlobalParamMap().get("deviceName"));
		capabilities.setCapability(MobileCapabilityType.APP,testDriver.getGlobalParamMap().get("appPath"));
		capabilities.setCapability("launchTimeout","290000");
		capabilities.setCapability("nativeInstrumentsLib",true);
		capabilities.setCapability("udid",testDriver.getGlobalParamMap().get("udid"));
		try {
            AppiumDriver mobileDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) {
                  
                  
                  @Override
                  public MobileElement scrollToExact(String arg0) {
                        // TODO Auto-generated method stub
                        return null;
                  }
                  
                  @Override
                  public MobileElement scrollTo(String arg0) {
                        // TODO Auto-generated method stub
                        return null;
                  }
            };
            
            Thread.sleep(10000);
            testDriver.setMobileDriver(mobileDriver);;
      } catch (MalformedURLException e) {
      }		
	}
	
	
	private void initAppiumIOSHybridDriver(TestDriver testDriver) throws InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, testDriver.getGlobalParamMap().get("deviceOsVersion"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testDriver.getGlobalParamMap().get("deviceName"));
		capabilities.setCapability(MobileCapabilityType.APP,testDriver.getGlobalParamMap().get("appPath"));
		capabilities.setCapability("launchTimeout","290000");
		capabilities.setCapability("nativeInstrumentsLib",true);
		capabilities.setCapability("udid",testDriver.getGlobalParamMap().get("udid"));
		try {
            AppiumDriver mobileDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) {
                  
                  
                  @Override
                  public MobileElement scrollToExact(String arg0) {
                        // TODO Auto-generated method stub
                        return null;
                  }
                  
                  @Override
                  public MobileElement scrollTo(String arg0) {
                        // TODO Auto-generated method stub
                        return null;
                  }
            };
            
            Thread.sleep(10000);
            testDriver.setMobileDriver(mobileDriver);
            
            Set<String> contextNames = testDriver.getMobileDriver().getContextHandles();
            for (String contextName : contextNames) {
                System.out.println(contextName);
                if (contextName.contains("WEBVIEW")){
                      testDriver.getMobileDriver().context(contextName);
                      break;
                }
             }

            
      } catch (MalformedURLException e) {
      }		
	}

	private void initAppiumIOSSafariDriver(TestDriver testDriver) throws InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, testDriver.getGlobalParamMap().get("deviceOsVersion"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testDriver.getGlobalParamMap().get("deviceName"));
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.SAFARI);
		capabilities.setCapability("launchTimeout","290000");
		capabilities.setCapability("nativeInstrumentsLib",true);
		capabilities.setCapability("udid",testDriver.getGlobalParamMap().get("udid"));
		try {
            AppiumDriver mobileDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) {
                  
                  
                  @Override
                  public MobileElement scrollToExact(String arg0) {
                        // TODO Auto-generated method stub
                        return null;
                  }
                  
                  @Override
                  public MobileElement scrollTo(String arg0) {
                        // TODO Auto-generated method stub
                        return null;
                  }
            };
            testDriver.setMobileDriver(mobileDriver);;
		} catch (MalformedURLException e) {
	      }
		
	}

	private void initAppiumAndroidNativeDriver(TestDriver testDriver) throws InterruptedException {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("newCommandTimeout","200");
        capabilities.setCapability(MobileCapabilityType.APP,testDriver.getGlobalParamMap().get("appPath"));
        capabilities.setCapability("appPackage",testDriver.getGlobalParamMap().get("appPackage"));
        capabilities.setCapability("appActivity",testDriver.getGlobalParamMap().get("appActivity"));
        capabilities.setCapability("launchTimeout","290000");
        capabilities.setCapability("nativeInstrumentsLib",true);
          capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
          capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"SampleMobile");
            capabilities.setCapability(MobileCapabilityType.VERSION,"5.1");
        try {
        AppiumDriver mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) {
             
              
              @Override
              public MobileElement scrollToExact(String arg0) {
                    // TODO Auto-generated method stub
                    return null;
              }
              
              @Override
              public MobileElement scrollTo(String arg0) {
                    // TODO Auto-generated method stub
                    return null;
              }
        };
        
        Thread.sleep(10000);
        testDriver.setMobileDriver(mobileDriver);;
  } catch (MalformedURLException e) {
  }           
        
  }
	
	
	private void initAppiumAndroidHybridDriver(TestDriver testDriver) throws InterruptedException {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("newCommandTimeout","200");
        capabilities.setCapability(MobileCapabilityType.APP,testDriver.getGlobalParamMap().get("appPath"));
        capabilities.setCapability("appPackage",testDriver.getGlobalParamMap().get("appPackage"));
        capabilities.setCapability("appActivity",testDriver.getGlobalParamMap().get("appActivity"));
        capabilities.setCapability("launchTimeout","290000");
        capabilities.setCapability("nativeInstrumentsLib",true);
          capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
          capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"SampleMobile");
            capabilities.setCapability(MobileCapabilityType.VERSION,"5.1");
        try {
        AppiumDriver mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) {
             
              
              @Override
              public MobileElement scrollToExact(String arg0) {
                    // TODO Auto-generated method stub
                    return null;
              }
              
              @Override
              public MobileElement scrollTo(String arg0) {
                    // TODO Auto-generated method stub
                    return null;
              }
        };
        
        Thread.sleep(10000);
        testDriver.setMobileDriver(mobileDriver);
        Set<String> contextNames = testDriver.getMobileDriver().getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName);
            if (contextName.contains("WEBVIEW")){
                  testDriver.getMobileDriver().context(contextName);
                  break;
            }
         }
      
      
  } catch (MalformedURLException e) {
  }           
        
  }

	public void initAppiumServer(TestDriver testDriver){
		String appiumInstallationDir = testDriver.getGlobalParamMap().get("appiumInstallationDir");// e.g. in Windows
		 String osName = System.getProperty("os.name");
		 AppiumDriverLocalService appiumProcess = null;
		 if(osName.contains("Windows")){
		 appiumProcess = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
	     .usingDriverExecutable(new File(appiumInstallationDir + File.separator + "Appium" + File.separator + "node.exe"))
	     .withAppiumJS(new File(appiumInstallationDir + File.separator + "Appium" + File.separator
	       + "node_modules" + File.separator + "appium" + File.separator + "bin" + File.separator + "appium.js"))
	     );
		 }else if(osName.contains("Mac")){
			 appiumProcess = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		     .usingDriverExecutable(new File(appiumInstallationDir + "/Appium.app/Contents/Resources/node/bin/node"))
		     .withAppiumJS(new File(
		       appiumInstallationDir + "/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js")));
		 }
		 appiumProcess.start();
		 testDriver.setAppiumProcess(appiumProcess);
	}
		  

	/**
	 * Initializes the IE Local driver initialization.
	 */
	private void initIELocal(TestDriver testDriver) {
		String driverPath = testDriver.getGlobalParamMap().get("iedriverPath");
		System.setProperty("webdriver.ie.driver", driverPath);
		DesiredCapabilities capIElocal = DesiredCapabilities.internetExplorer();
		capIElocal.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capIElocal
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		capIElocal.setCapability("requireWindowFocus", true);
		WebDriver driver = new InternetExplorerDriver(capIElocal);
		driver.manage().window().maximize();
		testDriver.setWebDriver(driver);
	}
	
	
	/**
	 * Initializes the chrome driver.
	 * 
	 * @throws DriverScriptException
	 * @throws InterruptedException 
	 * @throws MalformedURLException 
	 */
	private void initAppiumAndriodChromeDriver(TestDriver testDriver) throws DriverScriptException, InterruptedException {
        DriverScript.logMessage(testDriver,"info", "initAppiumDriver method: Start");
  if(testDriver.getGlobalParamMap().get("platform").equals("Mobile")){
  DesiredCapabilities capabilities=DesiredCapabilities.android();
  capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
  capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
  capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"SampleMobile");
    capabilities.setCapability(MobileCapabilityType.VERSION,"5.1");

  
        try {
              AppiumDriver mobiledriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) {
                    @Override
                    public MobileElement scrollToExact(String arg0) {
                          // TODO Auto-generated method stub
                          return null;
                    }
                    
                    @Override
                    public MobileElement scrollTo(String arg0) {
                          // TODO Auto-generated method stub
                          return null;
                    }
              };
              testDriver.setMobileDriver(mobiledriver);
        } catch (MalformedURLException e) {
        }
              
              
        } 
  else {
        DriverScript
                    .logMessage(testDriver,
                                "error",
                                "chrome driver path is not configured in Property file to launch the chrome Browser");
        throw new DriverScriptException(
                    "chrome driver path is not configured in Property file to launch the chrome Browser");
  }
  if (logger.isDebugEnabled()) {
        DriverScript.logMessage(testDriver,"info", "initChromeDriver method: End");
  }
}


public void launchApplication(TestDriver testDriver) throws DriverScriptException {

  if (logger.isDebugEnabled()) {
        DriverScript.logMessage(testDriver, "info", "launchApplication method: Start");
  }
  String url = testDriver.getGlobalParamMap().get("url");
  if (url != null) {
        DriverScript.logMessage(testDriver, "info", "Loading URL " + url);
        if (testDriver.getWebMedium()!=null) {
              testDriver.getWebDriver().navigate().to(url);
        } if(testDriver.getMobileMedium() != null && (!(testDriver.getMobileMedium().equals("iosNative") || testDriver.getMobileMedium().equals("androidNative")))) {
              testDriver.getMobileDriver().get(url);
              DriverScript.logMessage(testDriver, "info", "Launching "+url);
        }
  } else {
        throw new DriverScriptException("URL is not configured in config/Globalparameter workbook");
  }
  if (logger.isDebugEnabled()) {
        DriverScript.logMessage(testDriver, "info", "launchApplication method: Start");
  }
}


	/**
	 * Initializes the IE remote driver initialization.
	 *
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	private void initIERemote(TestDriver testDriver) throws DriverScriptException {
		DesiredCapabilities capIERemote = DesiredCapabilities.chrome();
		capIERemote.setBrowserName("internet explorer");
		capIERemote.setPlatform(Platform.WINDOWS);
		capIERemote
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		// cap.setJavascriptEnabled(true);
		capIERemote.setCapability("requireWindowFocus", true);
		String Node = "http://localhost:5555/wd/hub";
		try {
			WebDriver driver = new RemoteWebDriver(new URL(Node), capIERemote);
			driver.manage().window().maximize();
			testDriver.setWebDriver(driver);
		} catch (MalformedURLException e) {
			throw new DriverScriptException(
					"Error in launching IE as remote webdriver. Please verify whether connection is established between node and hub");
		}
	
	}

	/**
	 * Initializes the chrome driver.
	 * 
	 * @throws DriverScriptException
	 */
	private void initChromeDriver(TestDriver testDriver) throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info", "initChromeDriver method: Start");
		}
		String driverPath = testDriver.getGlobalParamMap().get("chromedriverPath");
		if (driverPath != null) {
			DriverScript.logMessage(testDriver,"info",
					"Launching google chrome with new profile..");
			System.setProperty("webdriver.chrome.driver", driverPath);
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
//			ChromeOptions options = new ChromeOptions();
			WebDriver driver = new ChromeDriver(capabilities);
//			options.addArguments("start-maximized");
			driver.manage().window().maximize();
			testDriver.setWebDriver(driver);
						
		} else {
			DriverScript
					.logMessage(testDriver,
							"error",
							"chrome driver path is not configured in Property file to launch the chrome Browser");
			throw new DriverScriptException(
					"chrome driver path is not configured in Property file to launch the chrome Browser");
		}
		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info", "initChromeDriver method: End");
		}
	}

	/**
	 * Initializes the Firefox driver.
	 */
	private void initFirefoxDriver(TestDriver testDriver) {
		if (logger.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,"info", "initFirefoxDriver method: Start");
		}

		DriverScript.logMessage(testDriver,"info", "Launching Firefox browser..");
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile ffprofile = profile.getProfile("SELENIUM");
		WebDriver driver = new FirefoxDriver(ffprofile);
		
		driver.manage().window().maximize();
		testDriver.setWebDriver(driver);

		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info", "initChromeDriver method: Start");
		}
	}


	/**
	 * Initialize test base setup.
	 * @param testDriver 
	 * @param browser 
	 *
	 * @param propertyUtil
	 *            the property util
	 * @throws DriverScriptException
	 */
	public void initializeWebTestBaseSetup(TestDriver testDriver) throws DriverScriptException {
			DriverScript.logMessage(testDriver,"info",
					"initializeTestBaseSetup method: Start");
			String browserType =testDriver.getWebMedium();
			
			switch (browserType ) {
			case "chrome":
				initChromeDriver(testDriver);
				break;
			case "firefox":
				initFirefoxDriver(testDriver);
				break;
			case "IERemote":
				initIERemote(testDriver);
				break;

			case "IELocal":
				initIELocal(testDriver);
				break;
				
			default:
				DriverScript
	            .logMessage(testDriver,
	                        "error",
	                        "Invalid browser type "+browserType);
					throw new DriverScriptException(
			"Invalid browser type "+browserType);
				
			}
			
			if (logger.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info", "set driver method: End");
			}
		
		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info",
					"initializeTestBaseSetup method: Start");
		}
	}

	/**
	 * Gets the global config excel values.
	 *
	 * @param file
	 *            the file
	 * @return the global config map
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public Map<String, String> getGlobalConfigMap(TestDriver testDriver,String file,String browser) throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info",
					"getGlobalConfigMap method: Start");
		}
		XLUtil xlUtil = new XLUtil();
		Map<String, String> globalParamMap = new HashMap<String, String>();
		File fi = new File(file);
		if (fi.exists()) {
			
			xlUtil.openWorkBook(testDriver,fi);
			DriverScript.logMessage(testDriver,"info",
					"Opening GlobalParameter workbook...");
			if (xlUtil.isSheetExists(testDriver,"GlobalParameter")) {
				DriverScript.logMessage(testDriver,"info",
						"GlobalParameter sheet exists");
				String globalParameterSheetName = "GlobalParameter";
				int totalRowCntGlobalParameter = xlUtil
						.getTotalRowCount(testDriver,globalParameterSheetName);
				for (int row = 1; row < totalRowCntGlobalParameter; row++) {
					String globalParamName = xlUtil.getCellValue(testDriver,
							globalParameterSheetName, row, 0);
					String globalParamValue = xlUtil.getCellValue(testDriver,
							globalParameterSheetName, row, 1);
					boolean readGlob = false;
					
					if(globalParamName.equals("webmedium")|| globalParamName.equals("mobilemedium")){
						if(!DriverScript.batchRun){
						readGlobalParamsForBrowser(testDriver, xlUtil,
								globalParamMap, globalParamValue);
						globalParamMap.put(globalParamName, globalParamValue);
						DriverScript.logMessage(testDriver,"info", globalParamName + "  "
								+ globalParamValue);
						}else{
							globalParamMap.put(globalParamName, globalParamValue);
							DriverScript.logMessage(testDriver,"info", globalParamName + "  "
									+ globalParamValue);	
						}
						}else if(globalParamName.equals("Global_Parameter_Ends_Here")){
						break;
						}else{
							globalParamMap.put(globalParamName, globalParamValue);
							DriverScript.logMessage(testDriver,"info", globalParamName + "  "
									+ globalParamValue);
						}
						
					
						
				}
				if(DriverScript.platform != null){
					globalParamMap.put("platform", DriverScript.platform);
				}
				if(DriverScript.batchRun){
					readGlobalParamsForBrowser(testDriver, xlUtil,
							globalParamMap, browser);
					globalParamMap.put("webmedium", browser);
					DriverScript.logMessage(testDriver,"info", "webmedium" + "  "
							+ browser);
				}
				if(DriverScript.mobileMedium!=null){
					readGlobalParamsForBrowser(testDriver, xlUtil,
							globalParamMap, DriverScript.mobileMedium);
					globalParamMap.put("mobilemedium", DriverScript.mobileMedium);
					DriverScript.logMessage(testDriver,"info", "mobilemedium" + "  "
							+ DriverScript.mobileMedium);
				}
				
			} else {
				DriverScript.logMessage(testDriver,"testStepDriverFail",
						"GlobalParameter sheet does not exists");
				throw new DriverScriptException(
						"GlobalParameter sheet does not exists");
			}
		} else {
			DriverScript.logMessage(testDriver,"testStepDriverFail",
					"GlobalParameter workbook does not exists");
			throw new DriverScriptException(
					"GlobalParameter workbook does not exists");
		}
			DriverScript.logMessage(testDriver,"info", "getGlobalConfigMap method: end");
			return globalParamMap;
	}

/**
 * Gets the global config properties values.
 *
 * @param file
 *            the file
 * @return the global config map
 * @throws DriverScriptException
 *             the driver script exception
 */
public Map<String, String> getGlobalConfigPropertiesMap(TestDriver testDriver,String file,String browser) throws DriverScriptException {
	if (logger.isDebugEnabled()) {
		DriverScript.logMessage(testDriver,"info",
				"getGlobalConfigPropertiesMap method: Start");
	}
	
	//PropertyUtil propertyUtil = new PropertyUtil();
	//XLUtil xlUtil = new XLUtil();
	Map<String, String> globalParamMap = new HashMap<String, String>();
	File propFile = new File(file);
	if (propFile.exists()) {
		
		PropertyUtil propertyUtil = new PropertyUtil(testDriver, propFile);
		propertyUtil.getProperty("browser");
		DriverScript.logMessage(testDriver,"info",
				"Opening GlobalParameter properties file...");
		
		globalParamMap =  propertyUtil.readPropertyFile(testDriver, propFile);
		globalParamMap.put("webmedium",globalParamMap.get("browser"));
	} else {
		DriverScript.logMessage(testDriver,"testStepDriverFail",
				"GlobalParameter workbook does not exists");
		throw new DriverScriptException(
				"GlobalParameter workbook does not exists");
	}
		DriverScript.logMessage(testDriver,"info", "getGlobalConfigMap method: end");
		return globalParamMap;	 
			 
}

	
	private void readGlobalParamsForBrowser(TestDriver testDriver,
			XLUtil xlUtil, Map<String, String> globalParamMap,
			String browser) throws DriverScriptException {
		if(xlUtil.isSheetExists(testDriver, browser)){
			int browserRowCntGlobalParameter = xlUtil
					.getTotalRowCount(testDriver,browser.trim());
			for (int browserRow = 1; browserRow < browserRowCntGlobalParameter; browserRow++) {
				String globalParamBrowserName = xlUtil.getCellValue(testDriver,
						browser.trim(), browserRow, 0);
				String globalParamBrowserValue = xlUtil.getCellValue(testDriver,
						browser.trim(), browserRow, 1);
				globalParamMap.put(globalParamBrowserName, globalParamBrowserValue);
				DriverScript.logMessage(testDriver,"info", globalParamBrowserName + "  "
						+ globalParamBrowserValue);
				if(globalParamBrowserName.equals("Global_Parameter_Ends_Here")){
					break;
					}
			}
		}else{
			throw new DriverScriptException(
					browser+" browser config sheet does not exists in config/GlobalParameter workbook ");	
		}
	}

	/**
	 * Gets the field values by business action.
	 *
	 * @param columnCount
	 *            the column count
	 * @param businessComponentValue
	 *            the business component value
	 * @return the field values by business action
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public static Hashtable<String, String> getFieldValuesByBusinessAction(TestDriver testDriver,
			String businessComponentValue, String functionalityName)
			throws DriverScriptException {

		Map<String, Hashtable<String, String>> businessActionsMap = testDriver.getBusinessActionsMap();
		if (businessActionsMap != null
				&& businessActionsMap.get(businessComponentValue
						+ functionalityName) != null) {
			Hashtable<String, String> fieldValues = businessActionsMap
					.get(businessComponentValue + functionalityName);
			if (fieldValues != null) {
				DriverScript.logMessage(testDriver,"info",
						"Combination of businessComponentValue "
								+ businessComponentValue
								+ " and functionality name "
								+ functionalityName + " for almID " + alMID
								+ " exists");
				return fieldValues;
			}

			throw new DriverScriptException(new Throwable(
					"Combination of businessComponentValue "
							+ businessComponentValue
							+ " and functionality name " + functionalityName
							+ " for almID " + alMID + " doesnot exists"));
		} else {
			throw new DriverScriptException(new Throwable(
					"Combination of businessComponentValue "
							+ businessComponentValue
							+ " and functionality name " + functionalityName
							+ " for almID " + alMID + " doesnot exists"));

		}
	}

	/**
	 * Log messages.
	 *
	 * @param logType
	 *            info/error
	 * @param msg
	 *            the log message
	 */
	public static void logMessage(TestDriver testDriver,String logType, String msg) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String packageName = element.getClassName();
		String className;
		if (mapClassName.get(packageName) == null) {
			className = getClassname(element.getClassName());
			mapClassName.put(packageName, className);
		} else {
			className = mapClassName.get(packageName);
		}
		if (logType.toLowerCase().contains("fail")) {
			if (msg.contains(".png")) {
				Reporter.log(dateFormat.format(date) + " " + logType + msg
						+ "######<br/>");
				msg = msg.replace("$$$$$", "").replace("#####", "");
				logger.error(dateFormat.format(date) + " " + logType + msg);
			} else if (logType.equals("testStepDriverFail")) {
				
				Reporter.log(dateFormat.format(date) + " " + logType + msg
						+ "<br/>");
				msg = msg.replace("$$$$$", "");
				logger.error(dateFormat.format(date) + " " + logType + msg);
				if( testDriver.getLoggers()!=null){
					 testDriver.getLoggers().log(LogStatus.FAIL, msg);
				}
				//closeForBeforeFailure();
			}
		} else if (logType.toLowerCase().contains("done")) {
			if( testDriver.getLoggers()!=null){
				 testDriver.getLoggers().log(LogStatus.INFO, msg);
			}
			Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ "
					+ className + " : " + element.getMethodName() + " $$$$$ "
					+ msg + "<br/>");
			logger.info(dateFormat.format(date) + " " + logType + " : "
					+ className + " : " + element.getMethodName() + " - " + msg);
		}else if(logType.toLowerCase().contains("pass")){
			if( testDriver.getLoggers()!=null){
				 testDriver.getLoggers().log(LogStatus.PASS, msg);
			}
			Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ "
					+ className + " : " + element.getMethodName() + " $$$$$ "
					+ msg + "<br/>");
			logger.info(dateFormat.format(date) + " " + logType + " : "
					+ className + " : " + element.getMethodName() + " - " + msg);
			
		}
		
		else if (logType.toLowerCase().contains("test")) {
			logger.info(dateFormat.format(date) + " " + logType + " : "
					+ className + " : " + element.getMethodName() + " - " + msg);
		} else if ("info".equalsIgnoreCase(logType)) {
			logger.info(dateFormat.format(date) + " " + logType + " : "
					+ className + " : " + element.getMethodName() + " - " + msg);
		} else if ("error".equalsIgnoreCase(logType)) {
			logger.error(dateFormat.format(date) + " " + logType + " : "
					+ className + " : " + element.getMethodName() + " - " + msg);
		}
	}

	/**
	 * Gets the classname.
	 *
	 * @param className
	 *            the class name
	 * @return the classname
	 */
	public static String getClassname(String className) {
		int counter = 0;
		for (int i = className.length() - 1; i > 0; i--) {
			if (className.charAt(i) == '.') {
				break;
			}
			counter = counter + 1;
		}
		className = className.substring(className.length() - counter,
				className.length());
		return className;
	}

	public static void close(TestDriver testDriver) {
		String browser=null;
		try {
			

			if(testDriver.isExecuted() || testDriver.isSkipped()){
				
				browser = closeDiver(testDriver, browser);
			}
		} catch (Exception e) {
			if(testDriver.isExecuted()){
			DriverScript.result.setStatus(ITestResult.FAILURE);
			Reporter.setCurrentTestResult(result);
			
			applyFailStyle(DriverScript.rowNumber,browser);
			DriverScript.logMessage(testDriver,"testStepDriverFail", " $$$$$ "
					+ "DriverScript" + " : close"
					+ " $$$$$ Failed to close the "+browser +"driver/Browser");
			}
		}
		DriverScript.logMessage(testDriver,"info", "Driver close");
	}

	private static String closeDiver(TestDriver testDriver, String browser) {
		if(testDriver.getWebMedium() !=null && testDriver.getWebDriver()!=null){
			browser = testDriver.getWebMedium();
//testDriver.getWebDriver().quit();
if (!hasQuit(testDriver)) {
		testDriver.getWebDriver().close();
		
		DriverScript.logMessage(testDriver,"testStepDone",
				browser+" Browser closed successfully");
}
}if(testDriver.getMobileMedium()!=null && testDriver.getMobileDriver()!=null ){
		if(browser!=null){
			browser = testDriver.getMobileMedium();
			}else{
				browser =browser+"_"+ testDriver.getMobileMedium();
			}
//				try{
		testDriver.getMobileDriver().quit();
		/*}catch(SessionNotFoundException ex){
		}*/
		stopAppiumServer(testDriver);
		
		DriverScript.logMessage(testDriver,"testStepDone",
				testDriver.getMobileMedium()+" closed successfully");
}
		return browser;
	}

	public static void stopAppiumServer(TestDriver testDriver) {
		 if (testDriver.getAppiumProcess() != null) {
			 testDriver.getAppiumProcess().stop();
			  }
	}

	public static void closeForBeforeFailure(TestDriver testDriver) {
		try {
			
			if (!hasQuit(testDriver)) {
				testDriver.getWebDriver().close();
			}
			
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM winword.exe");
			Runtime.getRuntime().exec("taskkill /F /IM excel.exe");
			Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
			if (testsFailMap != null
					&& testsFailMap.get(Integer.parseInt(alMID)) != null) {
				applyFailStyle(testsFailMap.get(Integer.parseInt(alMID)),null);
			}
			DriverScript.logMessage(testDriver,"testStepDone",
					"Browser closed successfully");
		} catch (Exception e) {

			DriverScript.logMessage(testDriver,"testStepDriverFail", " $$$$$ "
					+ "D2DriverScript" + " : closeForBeforeFailure"
					+ " $$$$$ Failed to close the driver/Browser");
		}
		DriverScript.logMessage(testDriver,"info", "Driver close ");
	}

	public void setSoapLoggerManager() {
		System.setProperty("soapui.log4j.config", "config/soapui-log4j.xml");

	}

	public static HSSFCellStyle applyFailStyle(String rowNumber,String browser) {
		HSSFWorkbook wb = null;
		HSSFCellStyle styleFail = null;
		try {
			FileInputStream fi = new FileInputStream(new File(
					"Execution_Interface.xls"));
			// Get the workbook instance for XLS file
			wb = new HSSFWorkbook(fi);
			styleFail = failStyle(wb);
			Sheet sh = wb.getSheetAt(0);
			if(!parallel){
				sh.getRow(Integer.parseInt(rowNumber)).createCell(6)
						.setCellStyle(styleFail);
				sh.getRow(Integer.parseInt(rowNumber)).getCell(6)
						.setCellValue("FAIL");
				}else{
					if(testsExcelMap.get(rowNumber)==null){
						sh.getRow(Integer.parseInt(rowNumber)).createCell(6)
					.setCellValue(browser+":FAIL");
						testsExcelMap.put(rowNumber,1);
					}
					else{
						String previousRes = sh.getRow(Integer.parseInt(rowNumber)).getCell(6).getStringCellValue();
						sh.getRow(Integer.parseInt(rowNumber)).getCell(6).setCellValue(previousRes+" , "+browser+":FAIL");
					}
				}
			FileOutputStream out = new FileOutputStream(new File(
					"Execution_Interface.xls"));
			wb.write(out);
			out.close();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return styleFail;
	}

	private static HSSFCellStyle failStyle(HSSFWorkbook wb) {
		HSSFCellStyle styleFail;
		styleFail = wb.createCellStyle();
		styleFail.setFillForegroundColor(HSSFColor.DARK_RED.index);
		styleFail.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return styleFail;
	}

	public static boolean hasQuit(TestDriver testDriver) {
		if (testDriver.getWebDriver() != null) {
			if (((RemoteWebDriver) testDriver.getWebDriver()).getSessionId() == null) {
				return true;
			}
		} else {
			return true;
		}
		return false;

	}
}