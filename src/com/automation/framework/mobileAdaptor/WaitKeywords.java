package com.automation.framework.mobileAdaptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.MobileAdaptorException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;

public class WaitKeywords {
	/**
	 * @name waitForObjectToLoad
	 * @description waits till the element is loaded. Max wait time is wait.high
	 *              value provided in Globalprameters sheet
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.waitForObjectToLoad , XpathOfTargetElement
	 */
	public void waitForObjectToLoad(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "waitForObjectToLoad action is invoked");
		String locator = (String) params.get("arg0");
		final By objLocator = ObjectRead.getObject(testDriver,locator,"Mobile");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		int maxWait = Integer.parseInt(testDriver.getGlobalParamMap().get("wait.high"));
		DriverScript.logMessage(testDriver,"info",
				"Maximum of " + maxWait + " seconds wait will be applied to search for " + locator);
		Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(testDriver.getMobileDriver())
				.withTimeout(maxWait, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class);
		try {
			wait.until(new Function<MobileDriver, WebElement>() {
				public WebElement apply(MobileDriver driver) {
					return testDriver.getMobileDriver().findElement(objLocator);
				}
			});
			DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		} catch (TimeoutException ex) {
			DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator + " ," + locator
					+ " locator does not exists in the screen");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
					+ locator + " locator does not exists in the screen"));
		}
		DriverScript.logMessage(testDriver,"info", "waitForObjectToLoad keyword is invoked");
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
			throws BrowserException, ElementFailException, ObjectNameNotFoundException ,MobileAdaptorException {
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
		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				WebDriverWait wait = new WebDriverWait(testDriver.getMobileDriver(), 30);
				wait.until(ExpectedConditions
						.visibilityOf(testDriver.getMobileDriver().findElement(ObjectRead.getObject(testDriver,locator,"Mobile"))));
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
		if (testDriver.getMobileDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				WebDriverWait wait = new WebDriverWait(testDriver.getMobileDriver(), 30);
				wait.until(ExpectedConditions.elementToBeClickable(ObjectRead.getObject(testDriver,locator,"Mobile")));

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
