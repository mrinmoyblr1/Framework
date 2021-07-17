package com.automation.framework.core;

import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;


public class SauceLabIntegrationSimulator {

    private AppiumDriver driver;

    private List<Integer> values;

    private static final int MINIMUM = 0;
    private static final int MAXIMUM = 10;

   

    @Before
    public void setUp() throws Exception {
       
    	
    	DesiredCapabilities capabilities = new DesiredCapabilities();
/*        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("platformName", "Linux");
        capabilities.setCapability("appiumVersion", "1.5.3");    */
    	/*capabilities.setCapability("app", "sauce-storage:BuyRent-core-prod-debug.apk");
    	        
    	        
    	        capabilities.setCapability("browserName", "");
    	        capabilities.setCapability("deviceOrientation", "portrait");
    	        capabilities.setCapability("appiumVersion", "1.5.3");  */
    	capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Samsung Galaxy S4");
        //capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        //capabilities.setCapability("app", "sauce-storage:AndroidRealtor.apk");
        //capabilities.setCapability("app", "sauce-storage:selendroid-test-app.apk");
        //capabilities.setCapability("app", "sauce-storage:AndroidRealtordebug.apk");
        capabilities.setCapability("app", "sauce-storage:BuyRent-core-prod-debug.apk");
        capabilities.setCapability("appPackage", "com.move.realtor");
        capabilities.setCapability("appActivity", "com.move.realtor.splash.SplashActivity");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("appiumVersion", "1.5.3");
    	 
      this.driver = new IOSDriver(
              new URL("http://manojrb1990:e00643a5-caf0-46c1-adbf-4400f461e1ea@ondemand.saucelabs.com:80/wd/hub"),
              capabilities);
      //this.driver = new URL("http://" + "qaautomationsvc" + ":" + "69298856-04f4-408a-bd26-c40d6be060e9"
    //  this.driver = new RemoteWebDriver(new URL("http://" + "offshoreautomation" + ":" + "ca6b6f16-2ae3-4a94-88be-9c91b682fa02" + "@ondemand.saucelabs.com:80/wd/hub"),capabilities);
      Thread.sleep(20000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private void populate() {
        //populate text fields with two random number
        /*List<WebElement> elems = driver.findElementsByClassName("UIATextField");
        Random random = new Random();
        for (WebElement elem : elems) {
            int rndNum = random.nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
            elem.sendKeys(String.valueOf(rndNum));
            values.add(rndNum);
        }*/
    }

    @Test
    public void testUIComputation() throws Exception {

       /* // populate text fields with values
        populate();
        // trigger computation by using the button
        WebElement button = driver.findElementByClassName("UIAButton");
        button.click();
        // is sum equal ?
        WebElement texts = driver.findElementByClassName("UIAStaticText");
    */   // assertEquals(String.valueOf(values.get(0) + values.get(1)), texts.getText());
    }

    
}
