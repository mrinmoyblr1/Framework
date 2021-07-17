package com.automation.framework.webAdaptor;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.WaitEvent;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;

public class WaitKeywords implements WaitEvent {

	/**
	 * @name implicitWait
	 * @description wait for specified amount of time
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void implicitWait(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Wait action is invoked");
		String seconds = (String) params.get("arg0");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (seconds != null && seconds == "") {
			try {
				int time = Integer.parseInt(seconds);
				time = time * 1000;
				DriverScript.logMessage(testDriver,"info","Implicit wait time is  " + seconds+" seconds");
				testDriver.getWebDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
				DriverScript.logMessage(testDriver,"info","Wait Action Successful");
			} catch (NumberFormatException e) {
				DriverScript.logMessage(testDriver,"error","Error in NumberFormat" + e);
				throw new ElementFailException(new Throwable("Error in parsing time unit "));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Wait action is executed");

	}


	/**
	 * @name wait
	 * @description Explicit wait. Halts the execution for the amount of time provide as a input such as wait.low/wait.medium/wait.high
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.wait,"wait.high/low/medium"
	 */
	public void wait(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Wait action is invoked");
		try {
			String waitTime = (String) params.get("arg0");
			int time = Integer.parseInt(testDriver.getGlobalParamMap().get(waitTime));
			time = time * 1000;
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				DriverScript.logMessage(testDriver,"error","Error in NumberFormat" + e);
				throw new ElementFailException("Unable to wait");
			}
			DriverScript.logMessage(testDriver,"info","Wait Action Successful");
		} catch (NumberFormatException e) {
			DriverScript.logMessage(testDriver,"error","Error in NumberFormat" + e);
			throw new ElementFailException(new Throwable("Error in parsing time unit"));
		} catch (WebDriverException e) {
			DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
			throw new ElementFailException(new Throwable("Error in Webdriver "));
		}
		DriverScript.logMessage(testDriver,"info","Wait action is executed");

	}

	/**
	 * @name waitUntilElementVisible
	 * @description wait till element is visible
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void waitUntilElementVisible(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Wait Until Element Visible action is invoked");
		String locator = (String) params.get("arg0");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				WebDriverWait wait = new WebDriverWait(testDriver.getWebDriver(), 30);
				wait.until(ExpectedConditions
						.visibilityOf(testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web"))));
				DriverScript.logMessage(testDriver,"info",locator+" wait Until Element Visible successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: "+locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Wait Until Element Visible action is executed");

	}

	/**
	 * @name waitUntilElementClickable
	 * @description wait till element is Clickable.
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void waitUntilElementClickable(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Wait Until Element Clickable action is invoked");
		String locator = (String) params.get("arg0");

		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				WebDriverWait wait = new WebDriverWait(testDriver.getWebDriver(), 100);
				wait.until(ExpectedConditions.elementToBeClickable(ObjectRead.getObject(testDriver,locator,"web")));
				
				DriverScript.logMessage(testDriver,"info",locator+" wait Until Element Clickable successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " +locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: "+locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Wait Until Element Clickable action is executed");

	}

	
	/**
	 * @name waitUntilElementClickable
	 * @description wait till element is Clickable.
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void SetValueToXpathandwaitUntilElementClickable(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Wait Until Element Clickable action is invoked");
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");

		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				WebDriverWait wait = new WebDriverWait(testDriver.getWebDriver(), 30);
				wait.until(ExpectedConditions.elementToBeClickable(objLocator));

				DriverScript.logMessage(testDriver,"info",locator+" wait Until Element Clickable successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " +locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: "+locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Wait Until Element Clickable action is executed");

	}

}
