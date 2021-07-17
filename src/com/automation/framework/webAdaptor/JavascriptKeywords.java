package com.automation.framework.webAdaptor;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.JavaScript;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

public class JavascriptKeywords implements JavaScript {

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
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(ObjectRead
						.getObject(testDriver,locator,"web"));
				((JavascriptExecutor) testDriver.getWebDriver()).executeScript(
						"arguments[0].scrollIntoView(true);", element);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator
							+ " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator
								+ " element is displayed");
//						element.click();
//						DriverScript.logMessage(testDriver,"info", locator
//								+ " scrollandclick successful");
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
				injectJsOnNotVisible(testDriver,element, testDriver.getWebDriver(), e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,
				"info", "Scroll And Click action is executed");

	}

	
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
	public void setXpathAndScrollAndClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Scroll And Click action is invoked");
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		WebElement element = null;
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				((JavascriptExecutor) testDriver.getWebDriver()).executeScript(
						"arguments[0].scrollIntoView(true);", element);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator
							+ " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator
								+ " element is displayed");
//						element.click();
//						DriverScript.logMessage(testDriver,"info", locator
//								+ " scrollandclick successful");
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
				injectJsOnNotVisible(testDriver,element, testDriver.getWebDriver(), e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,
				"info", "Scroll And Click action is executed");

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
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Scroll To Bottom action is invoked");
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else {
			try {
				((JavascriptExecutor) testDriver.getWebDriver())
						.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight, document.body.scrollHeight,document.documentElement.clientHeight));");
				DriverScript.logMessage(testDriver,
						"info", "Scroll To Bottom successful");
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,
				"info", "Scroll To Bottom action is executed");

	}

	/**
	 * @name scrollToTop
	 * @description scroll to top of the page
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void scrollToTop(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Scroll To Top action is invoked");
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else {
			try {
				((JavascriptExecutor) testDriver.getWebDriver())
						.executeScript("window.scrollBy(0, -250)", "");
				DriverScript.logMessage(testDriver,
						"info", "Scroll To Top successful");
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,
				"info", "Scroll To Top action is executed");

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
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Handle Alert action is invoked");
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else {
			try {
				testDriver.getWebDriver().switchTo().alert().accept();
				DriverScript.logMessage(testDriver,"info", "Handle Alert successful");
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,"info", "Handle Alert action is executed");

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
	private void injectJsOnNotVisible(TestDriver testDriver,WebElement element, WebDriver driver,
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
	 * @name scrollAndClick
	 * @description scroll to the element and perform click operation on it
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions
	 */
	public void scrollAndClickBasedOnTags(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info",
				"scrollAndClickBasedOnTags action is invoked");
		String locator1 = (String) params.get("arg0");
		By objLocator1 = ObjectRead.getObject(testDriver,locator1,"web");
		String locator2 = (String) params.get("arg1");
		String xpathValue = (String) params.get("arg2");
		By objLocator2 = ObjectRead.getObject(testDriver,locator2, xpathValue,"web");
		WebElement element = null;
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator1 != null && locator1.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator1);
				DriverScript.logMessage(testDriver,"info",
						"Scrolling down to find element " + locator2);
				for (int i = 0; i < 500; i++) {

					try {
						testDriver.getWebDriver().findElement(objLocator2).click();
						DriverScript.logMessage(testDriver,"info", locator2
								+ " is clicked");
						break;
					} catch (NoSuchElementException ex) {
						if(i<=2){
						element.sendKeys(Keys.PAGE_DOWN);
					}else{
						element.sendKeys(Keys.ARROW_DOWN);
					}
					}
					}
			}catch (ElementNotVisibleException e) {
				logger.error("Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, testDriver.getWebDriver(), e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
		}
		}
		DriverScript.logMessage(testDriver,"info",
				"scrollAndClickBasedOnTags action is executed");

	}
	
	
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
	public void scrollAndViewBasedOnTags(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info",
				"scrollAndViewBasedOnTags action is invoked");
		String locator1 = (String) params.get("arg0");
		By objLocator1 = ObjectRead.getObject(testDriver,locator1,"web");
		String locator2 = (String) params.get("arg1");
		String xpathValue = (String) params.get("arg2");
		By objLocator2 = ObjectRead.getObject(testDriver,locator2, xpathValue,"web");
		WebElement element = null;
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator1 != null && locator1.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator1);
				DriverScript.logMessage(testDriver,"info",
						"Scrolling down to find element " + locator2);
				for (int i = 0; i < 500; i++) {

					try {
						testDriver.getWebDriver().findElement(objLocator2);
						DriverScript.logMessage(testDriver,"info", locator2
								+ " is clicked");
						break;
					} catch (NoSuchElementException ex) {
						element.sendKeys(Keys.PAGE_DOWN);
					}
				}
			} catch (ElementNotVisibleException e) {
				logger.error("Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, testDriver.getWebDriver(), e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,"info",
				"scrollAndViewBasedOnTags action is executed");

	}
	
	
	public void scrollDownInTag(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info",
				"scrollDownInTag action is invoked");
		String locator1 = (String) params.get("arg0");
		By objLocator1 = ObjectRead.getObject(testDriver,locator1,"web");
		WebElement element = null;
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator1 != null && locator1.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator1);
				DriverScript.logMessage(testDriver,"info",
						"Scrolling down in a tag " + locator1);
				element.sendKeys(Keys.ARROW_DOWN);
			} catch (ElementNotVisibleException e) {
				logger.error("Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, testDriver.getWebDriver(), e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,"info",
				"scrollDownInTag action is executed");

	}
	/**
	 * @name scrollByKeyDownAndClick
	 * @description scroll to the element and perform click operation on it
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions
	 */
	public void scrollByKeyDownAndClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info",
				"scrollByKeyDownAndClick action is invoked");
		String locator1 = (String) params.get("arg0");
		By objLocator1 = ObjectRead.getObject(testDriver,locator1,"web");
		String locator2 = (String) params.get("arg1");
		String xpathValue = (String) params.get("arg2");
		By objLocator2 = ObjectRead.getObject(testDriver,locator2, xpathValue,"web");
		WebElement element = null;
		if (testDriver.getWebDriver() == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator1 != null && locator1.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator1);
				DriverScript.logMessage(testDriver,"info",
						"Scrolling down to find element " + locator2);
				for (int i = 0; i < 500; i++) {

					try {
						if(testDriver.getWebDriver().findElement(objLocator2).isDisplayed()){
						testDriver.getWebDriver().findElement(objLocator2).click();
						DriverScript.logMessage(testDriver,"info", locator2
								+ " is clicked");
						break;
					}else{
						element.sendKeys(Keys.ARROW_DOWN);
					}
					} catch (NoSuchElementException ex) {
						element.sendKeys(Keys.ARROW_DOWN);
					}
				}
			} catch (ElementNotVisibleException e) {
				logger.error("Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, testDriver.getWebDriver(), e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,"info",
				"scrollByKeyDownAndClick action is executed");

	}
	
	/**
	 * @name navigateToPreviousPage
	 * @description Navigate to previous page
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions
	 */
	public void navigateToPreviousPage(TestDriver testDriver,String abc)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		
		//testDriver.getWebDriver().executeScript("window.history.go(-1)");
		JavascriptExecutor js = (JavascriptExecutor) testDriver.getWebDriver();
		js.executeScript("window.history.go(-1)");
	
	}
	

}
