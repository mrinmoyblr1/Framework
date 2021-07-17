package com.automation.framework.mobileAdaptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.MobileAdaptorException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;
import com.google.common.base.Function;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

public class ElementKeywords {

	/**
	 * This method is used to tap an element.
	 * 
	 * @param xpath
	 *            of the object
	 * 
	 * 
	 */
	public void click(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException,MobileAdaptorException {

		DriverScript.logMessage(testDriver,"info", "Tap keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);

		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getMobileDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						try {
							element.click();
						} catch (Exception e) {
							testDriver.getMobileDriver().tap(1, element, (int) 1.0);
						}

						DriverScript.logMessage(testDriver,"info", locator + " is clicked");
					} else {
						DriverScript.logMessage(testDriver,"error", locator
								+ " element found, but its disabled ,please try clickUsingJavascript action to perform click operation");
						throw new ElementFailException(new Throwable(locator
								+ " element found, but its disabled ,please try clickUsingJavascript action to perform click operation"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator
							+ " element found, but its disabled ,please use clickUsingJavascript action to perform click operation");
					throw new ElementFailException(new Throwable(locator
							+ " element found, but its disabled ,please use clickUsingJavascript action to perform click operation"));
				}

			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
						+ locator + " locator does not exists in the screen to perform click operation"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				;
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "Tap keyword is executed");
	}
	/**
	 * @name tapUsingJavascript
	 * @description click on element using javascript
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void clickUsingJavascript(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "clickUsingJavascript keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = testDriver.getMobileDriver().findElement(objLocator);
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
						 ((JavascriptExecutor)testDriver.getMobileDriver()).executeScript("arguments[0].click();", element);
						DriverScript.logMessage(testDriver,"info", locator + " is clicked");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				
			} 
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "clickUsingJavascript keyword is executed");
	}
	
	/**
	 * 
	 * @param testDriver
	 * @description It will perform long press on the location 
	 * @param xpath of the location
	 * @throws BrowserException
	 * @throws ElementFailException
	 * @throws ObjectNameNotFoundException
	 * @throws MobileAdaptorException
	 */
	public void longPress(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException,MobileAdaptorException {

		DriverScript.logMessage(testDriver,"info", "longPress keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator =  ObjectRead.getObject(testDriver,locator,"Mobile");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);

		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getMobileDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						TouchAction action = new TouchAction(testDriver.getMobileDriver());
						action.longPress(element).release().perform();
						DriverScript.logMessage(testDriver,"info", locator + " is clicked");
					} else {
						DriverScript.logMessage(testDriver,"error", locator
								+ " element found, but its disabled ,please try clickUsingJavascript action to perform click operation");
						throw new ElementFailException(new Throwable(locator
								+ " element found, but its disabled ,please try clickUsingJavascript action to perform click operation"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator
							+ " element found, but its disabled ,please use clickUsingJavascript action to perform click operation");
					throw new ElementFailException(new Throwable(locator
							+ " element found, but its disabled ,please use clickUsingJavascript action to perform click operation"));
				}

			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
						+ locator + " locator does not exists in the screen to perform click operation"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				;
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "Tap keyword is executed");
	}
	

	/**
	 * @name setText
	 * @description to provide input value to the textbox element
	 * @param arg1
	 * @return void
	 * @usage Actions.setText, XpathOfTargetElement, Value
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Set element Text keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		String inputext = (String) params.get("arg1");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		DriverScript.logMessage(testDriver,"info", "inputext is " + inputext);
		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getMobileDriver().findElement(objLocator);
				element.clear();
				DriverScript.logMessage(testDriver,"info", locator + " is cleared ");
				element.sendKeys(inputext+"\n");				
				DriverScript.logMessage(testDriver,"info", inputext + " is passed in to " + locator + " element");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator textbox does not exists in the screen to send input parameters " + inputext);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
						+ locator + " locator does not exists in the screen to fetch text"));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Set element Text keyword is executed");

	}

	
	/**
	 * @name wait
	 * @description Explicit wait. Halts the execution for the amount of time
	 *              provide as a input such as wait.low/wait.medium/wait.high
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.wait,"wait.high/low/medium"
	 */
	public void wait(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Wait action is invoked");
		try {
			String waitTime = (String) params.get("arg0");
			int time = Integer.parseInt(testDriver.getGlobalParamMap().get(waitTime));
			time = time * 1000;
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				DriverScript.logMessage(testDriver,"error", "Error in NumberFormat" + e);
				throw new ElementFailException("Unable to wait");
			}
			DriverScript.logMessage(testDriver,"info", "Wait Action Successful");
		} catch (NumberFormatException e) {
			DriverScript.logMessage(testDriver,"error", "Error in NumberFormat" + e);
			throw new ElementFailException(new Throwable("Error in parsing time unit"));

		}
		DriverScript.logMessage(testDriver,"info", "Wait action is executed");

	}

	/**
	 * 
	 * This method is used to verify the status of the checkbox
	 * 
	 * @param executor
	 * @param executorResults
	 * 
	 */
	public void verifyChecked(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "To verify the status of the checkbox");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");

		try {
			element = testDriver.getMobileDriver().findElement(objLocator);
			// highLightByXpath(webElement);
			if (element.getAttribute("checked").equals("true")) {
				DriverScript.logMessage(testDriver,"info", "verifyChecked is successfull");

			} else {
				DriverScript.logMessage(testDriver,"info", "Object is not checked");
			}

		} catch (InvalidSelectorException e) {
			DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
			throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
		} catch (NoSuchElementException e) {
			DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator + " ," + locator
					+ " locator textbox does not exists in the screen to send input parameters ");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
					+ locator + " locator does not exists in the screen to fetch text"));
		}
	}
/**
 * Description perform double tap based on the input co-odinate
 * @param testDriver
 * @param params
 */
	public void doubleTapToLocation(TestDriver testDriver,HashMap<String, Object> params) {
		String xCordString = (String) params.get("arg0");
		int xCord = Integer.parseInt(xCordString);
		String yCordString = (String) params.get("arg1");
		int yCord = Integer.parseInt(yCordString);
		try {

			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) testDriver.getMobileDriver();
			javascriptExecutor.executeScript("mobile: tap", new HashMap<String, Double>() {
				{
					put("tapCount", (double) 2);
					put("touchCount", (double) 1);
					put("duration", (double) 0.0);
					put("x", (double) xCord);
					put("y", (double) yCord);
				}
			});
			DriverScript.logMessage(testDriver,"info", "tapToLocationBasedOnXpath is successfull");

		} catch (NoSuchElementException e) {
			// D2DriverScript.logMessage(testDriver,"error", e.getStackTrace());

		}
	}
	/**
	 * @description tap on element based on location
	 * @param testDriver
	 * @param params
	 * @throws BrowserException
	 * @throws ElementFailException
	 * @throws ObjectNameNotFoundException
	 */
	public void tapToLocationBasedOnElement(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		try {
			element = testDriver.getMobileDriver().findElement(objLocator);
			Point point = element.getLocation();
			int xCord = point.getX() + 10;
			int yCord = point.getY() + 5;
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) testDriver.getMobileDriver();
			javascriptExecutor.executeScript("mobile: tap", new HashMap<String, Double>() {
				{
					put("tapCount", (double) 1);
					put("touchCount", (double) 1);
					put("duration", (double) 0.0);
					put("x", (double) xCord);
					put("y", (double) yCord);
				}
			});
			DriverScript.logMessage(testDriver,"info", "tapToLocationBasedOnXpath is successfull");
		} catch (NoSuchElementException e) {
			DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator + " ," + locator
					+ " locator textbox does not exists in the screen to send input parameters ");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
					+ locator + " locator does not exists in the screen to fetch text"));
		}

	}

	/**
	 * This method is used to clear the content in the textbox.
	 * 
	 * @param xpath
	 * 
	 * 
	 */
	public void clearText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		try {
			element = testDriver.getMobileDriver().findElement(objLocator);
			element.clear();
			DriverScript.logMessage(testDriver,"info", "clearText is successfull");

		} catch (NoSuchElementException e) {
			DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator + " ," + locator
					+ " locator textbox does not exists in the screen to send input parameters ");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
					+ locator + " locator does not exists in the screen to fetch text"));
		}

	}

	/**
	 * @name elementCount
	 * @description click on element
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void elementCount(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "elementCount keyword is invoked"); // Ritesh
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				int size  = testDriver.getMobileDriver().findElements(objLocator).size();
				DriverScript.logMessage(testDriver,"info", "Count of "+locator+" locator is "+size);
				 testDriver.getMapValues().put(locator,String.valueOf(size));
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "elementCount keyword is executed");
	}
	
	/**
	 * @name IsElementExist
	 * @description verifies whether element exists in the screen
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementExist, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void IsElementExist(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "IsElementExist keyword is invoked");
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				testDriver.getMobileDriver().findElement(objLocator);
				DriverScript.logMessage(testDriver,"info", locator + " exists ");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen ");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen "));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "IsElementExist keyword is executed");
	}
	
    /**
	 * @name setValueToXpathAndClick
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then click on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndClick, xpath ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
    public void setValueToXpathAndClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndClick keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"Mobile");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = testDriver.getMobileDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						element.click();
					} else {
						DriverScript.logMessage(testDriver,"error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation "));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndClick keyword is executed");

	}

/**
* @name setXpathAndwaitForObjectToLoad
* @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
*              by the actual value passed and waits till the element is
*              loaded. Max wait time is wait.high value provided in
*              Globalprameters sheet
* @param params
* @throws ObjectNameNotFoundException
* @throws ElementFailException
* @description set xpath and wait till object load
*/

public void setXpathAndwaitForObjectToLoad(TestDriver testDriver,HashMap<String, Object> params)
            throws ObjectNameNotFoundException, ElementFailException {
      DriverScript.logMessage(testDriver,"info", "setXpathAndwaitForObjectToLoad keyword is invoked");
      String locator = (String) params.get("arg0");
      String xpathValue = null;
      if (params.get("arg1") != null) {
            xpathValue = (String) params.get("arg1");
      }
      final By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"Mobile");
      int maxWait = Integer.parseInt(testDriver.getGlobalParamMap().get("wait.high"));
      DriverScript.logMessage(testDriver,"info", "Maximum of " + maxWait + " seconds wait will be applied to search for the ");
      Wait<MobileDriver> wait = new FluentWait<MobileDriver>(testDriver.getMobileDriver()).withTimeout(maxWait, TimeUnit.SECONDS)
                  .pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
      try {
            wait.until(new Function<MobileDriver, WebElement>() {
                  public WebElement apply(MobileDriver driver) {
                        return testDriver.getMobileDriver().findElement(objLocator);
                  }
            });
      } catch (TimeoutException ex) {
            DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen");
            throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen"));
      }

      DriverScript.logMessage(testDriver,"info", "setXpathAndwaitForObjectToLoad keyword is invoked");
}


/**
 * @name IsElementExist
 * @description verifies whether element exists in the screen
 * @param arg1
 * @return void
 * @usage Actions.isElementExist, XpathOfTargetElement
 * @throws ObjectNameNotFoundException
 * @throws ElementFailException
 * @throws BrowserException
 */

}
