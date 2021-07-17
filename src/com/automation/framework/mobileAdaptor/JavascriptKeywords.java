package com.automation.framework.mobileAdaptor;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.MobileAdaptorException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.JavaScript;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;

import io.appium.java_client.MobileDriver;
import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

public class JavascriptKeywords implements JavaScript{
	final static Logger logger = Logger.getLogger(JavascriptKeywords.class);

	/**
	 * @name scrollAndClick
	 * @description scroll to the element and perform click operation on it
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions
	 */
	public void scrollAndClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Scroll And Click action is invoked");
		String locator = (String) params.get("arg0");
		WebElement element = null;
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getMobileDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getMobileDriver().findElement(ObjectRead
						.getObject(testDriver,locator,"mobile"));
				((JavascriptExecutor) testDriver.getMobileDriver()).executeScript(
						"arguments[0].scrollIntoView(true);", element);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator
							+ " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator
								+ " element is displayed");
						element.click();
						DriverScript.logMessage(testDriver,"info", locator
								+ " scrollandclick successful");
					} else {
						logger.error(locator
								+ "element found, but its not displayed");
						throw new ElementFailException(new Throwable(locator
								+ "element found, but its not displayed"));
					}
				} else {
					logger.error(locator + "element found, but its disabled");
					throw new ElementFailException(new Throwable(locator
							+ "element found, but its disabled"));
				}
			} catch (ElementNotVisibleException e) {
				logger.error(locator
						+ "Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, testDriver.getMobileDriver(), e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			}
		}
		DriverScript.logMessage(testDriver,
				"info", "Scroll And Click action is executed");

	}

	/**
	 * @name injectJsOnNotVisible
	 * @param element
	 * @description injected element is not visible
	 * @param driver
	 * @param ElementNotVisibleException
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	private void injectJsOnNotVisible(TestDriver testDriver,WebElement element, MobileDriver driver,
			ElementNotVisibleException e) throws BrowserException,
			ElementFailException, ObjectNameNotFoundException {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		if ((Boolean) javascriptExecutor.executeScript("arguments[0].click();",
				element)) {
			DriverScript.logMessage(testDriver,"info",
					"Click Successful on triggering javscript");
		} else {
			throw new ElementFailException(new Throwable(
					"Unable to click after triggering javscript"));
		}
	}
	/**
	 * @name scrollToBottom
	 * @description scroll to bottom of the page
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void scrollToBottom(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException, MobileAdaptorException {
		DriverScript.logMessage(testDriver,"info", "Scroll To Bottom action is invoked");
		if (testDriver.getMobileDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else {
			try {
				((JavascriptExecutor) testDriver.getMobileDriver())
						.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight, document.body.scrollHeight,document.documentElement.clientHeight));");
				DriverScript.logMessage(testDriver,
						"info", "Scroll To Bottom successful");
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			}
		}
		DriverScript.logMessage(testDriver,
				"info", "Scroll To Bottom action is executed");

	}


	/**
	 * @name handleAlert
	 * @description handle the pop up to accept or cancel
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void handleAlert(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException, MobileAdaptorException {
		DriverScript.logMessage(testDriver,"info", "Handle Alert action is invoked");
		if (testDriver.getMobileDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else {
			testDriver.getMobileDriver().switchTo().alert().accept();
			DriverScript.logMessage(testDriver,"info", "Handle Alert successful");
		}
		DriverScript.logMessage(testDriver,"info", "Handle Alert action is executed");

	}

}
