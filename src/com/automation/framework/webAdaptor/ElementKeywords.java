package com.automation.framework.webAdaptor;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.Element;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;
import com.google.common.base.Function;

public class ElementKeywords implements Element {
	

	/**
	 * @name Click
	 * @description click on element
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void click(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Click keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						element.click();
						DriverScript.logMessage(testDriver,"info", locator + " is clicked");
					} else {
						DriverScript.logMessage(testDriver,"error", locator + " element found, but its disabled ,please try clickUsingJavascript action to perform click operation");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its disabled ,please try clickUsingJavascript action to perform click operation"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator + " element found, but its disabled ,please use clickUsingJavascript action to perform click operation");
					throw new ElementFailException(new Throwable(locator + " element found, but its disabled ,please use clickUsingJavascript action to perform click operation"));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, e);
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "Click keyword is executed");
	}
	
	
	
	
	/**
	 * @name isElementNotDisplayed
	 * @description verifies whether element exists and not displayed in the screen
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementNotDisplayed(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "isElementNotDisplayed keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its  displayed"));
						
					} else {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
					}
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
		DriverScript.logMessage(testDriver,"info", "isElementNotDisplayed keyword is executed");
	}

	



/**
	 * @name setValueToXpathAndElementNotDisplayed
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then validates whether element is displayed or not
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndClick, xpath ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndElementNotDisplayed(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndElementNotDisplayed keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isDisplayed()) {
					DriverScript.logMessage(testDriver,"error", locator + " element found, but its not displayed");
					throw new ElementFailException(
							new Throwable(locator + " element found, but its not displayed"));
					
				} else {
					DriverScript.logMessage(testDriver,"info", locator + " element is not displayed");
				}
		} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, e);
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndElementNotDisplayed keyword is executed");

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
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				int size  = testDriver.getWebDriver().findElements(objLocator).size();
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
	 * @name send Keyboard keys 
	 * @description to provide input value to the textbox element
	 * @param arg1
	 * @return void
	 * @usage Actions.sendKeyboardActions, XpathOfTargetElement, Value
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void sendKeyboardActions(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info", "sendKeyboardActions keyboard is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		String inputext = (String) params.get("arg1");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		DriverScript.logMessage(testDriver,"info", "inputext is " + inputext);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				element.sendKeys(Keys.valueOf(inputext));
				DriverScript.logMessage(testDriver,"info", inputext + " is passed in to " + locator + " element");
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
		DriverScript.logMessage(testDriver,"info", "sendKeyboardActions keyword is executed");

	}

	
	/**
	 * @name setXpathAndElementCount
	 * @description click on element
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setXpathAndElementCount(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "setXpathAndElementCount keyword is invoked"); // Ritesh
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator,xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				int size  = testDriver.getWebDriver().findElements(objLocator).size();
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
		DriverScript.logMessage(testDriver,"info", "setXpathAndElementCount keyword is executed");
	}
	
	
	/**
	 * @name clickUsingJavascript
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
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = testDriver.getWebDriver().findElement(objLocator);
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
						 ((JavascriptExecutor)testDriver.getWebDriver()).executeScript("arguments[0].click();", element);
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
				injectJsOnNotVisible(testDriver,element, e);
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "clickUsingJavascript keyword is executed");
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
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info", "Set element Text keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		String inputext = (String) params.get("arg1");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		DriverScript.logMessage(testDriver,"info", "inputext is " + inputext);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				//element.clear();
				DriverScript.logMessage(testDriver,"info", locator + " is cleared ");
				element.sendKeys(inputext);
				int counter = 0;
				
				/*while(counter<5){
					element = testDriver.getWebDriver().findElement(objLocator);
					JavascriptExecutor executor = (JavascriptExecutor) testDriver.getWebDriver();
					String value = (String) executor.executeScript("return arguments[0].value", element);
					if(!value.equals(inputext)){
						DriverScript.logMessage(testDriver,"info", "Retrying set text.. "+inputext);
					element.clear();
					element.sendKeys(inputext);
					}else{
						break;
					}
				}
				DriverScript.logMessage(testDriver,"info", inputext + " is passed in to " + locator + " element");*/
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Set element Text keyword is executed");

	}


	/**
	 * @name getText
	 * @description Extract text from element and save it to HashMap
	 * @param arg1
	 * @return void
	 * @usage Actions.getText, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void getText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "Get element Text keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.getText() != null && element.getText() != "") {
					String value = element.getText();
					testDriver.getMapValues().put(locator, value);
					DriverScript.logMessage(testDriver,"info", "Extracted text is " + value);
					DriverScript.logMessage(testDriver,"info", "Get element Text successful and saved in to map");
				} else {
					DriverScript.logMessage(testDriver,"error",
							"element found and element text is empty. Please use getTextUsingJavascript keyword to perform this action");
					throw new ElementFailException(
							"element found and element text is empty. Please use getTextUsingJavascript keyword to perform this action");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Get element Text keyword is executed");

	}
	
	


	/**
	 * @name
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then extract and save text of
	 *              the element
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndClick, xpath ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndGetText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndGetText keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.getText() != null && element.getText() != "") {
					String value = element.getText();
					testDriver.getMapValues().put(locator, value);
					DriverScript.logMessage(testDriver,"info", "Extracted text is " + value);
					DriverScript.logMessage(testDriver,"info", "Get element Text successful");
				} else {
					DriverScript.logMessage(testDriver,"error", "element found and element text is empty");
					throw new ElementFailException("element found and element text is empty");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndGetText keyword is executed");

	}

	/**
	 * @name doubleClick
	 * @description double click on an element
	 * @param arg1
	 * @return void
	 * @usage Actions.doubleClick, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void doubleClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException { // Ritesh
		DriverScript.logMessage(testDriver,"info", "Double Click keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(testDriver.getWebDriver());
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						action.doubleClick(element).perform();
						DriverScript.logMessage(testDriver,"info", locator + " is double clicked");
						DriverScript.logMessage(testDriver,"info", "Double Click successful");
					} else {
						DriverScript.logMessage(testDriver,"error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its not displayed"));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error", locator+" found and not visible to perform operation");
				throw new ElementFailException(locator+" found and not visible to perform operation");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Double Click keyword is executed");
	}

	/**
	 * @name setValueToXpathAndDoubleClick
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then double click on the
	 *              element
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndDoubleClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndDoubleClick keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(testDriver.getWebDriver());
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						action.doubleClick(element).perform();
						DriverScript.logMessage(testDriver,"info", " Double Click on " + locator + " is  successful");
					} else {
						DriverScript.logMessage(testDriver,"error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its not displayed"));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error", locator+" found and not visible to perform operation");
				throw new ElementFailException(locator+" found and not visible to perform operation");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndDoubleClick keyword is executed");
	}

	/**
	 * @name setValueToXpathAndRightClick
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then right click on the
	 *              element
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndRightClick, xpath
	 *        ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndRightClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndRightClick keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(testDriver.getWebDriver());
				element = testDriver.getWebDriver().findElement(objLocator);
				action.contextClick(element).perform();
				DriverScript.logMessage(testDriver,"info", "Right Click on " + locator + " is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform Right click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}

		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndRightClick keyword is executed");
	}

	/**
	 * @name rightClick
	 * @description Right click on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.rightClick, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void rightClick(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "Right Click keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(testDriver.getWebDriver());
				element = testDriver.getWebDriver().findElement(objLocator);
				action.contextClick(element).perform();
				DriverScript.logMessage(testDriver,"info", "Right Click " + locator + " is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform Right click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform Right click operation"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}

		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Right Click keyword is executed");
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
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				testDriver.getWebDriver().findElement(objLocator);
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
	 * @name isElementNotExists
	 * @description verifies whether element does not exists in the screen
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementNotExists(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "isElementNotExists keyword is invoked");
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				testDriver.getWebDriver().findElement(objLocator);
				DriverScript.logMessage(testDriver,"error", locator+" locator exists in the screen");
				throw new ElementFailException(new Throwable(locator+" locator exists in the screen"));
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"info", locator + " exists");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "isElementNotExists keyword is executed");
	}
	
	
	/**
	 * @name setValueToXpathAndIsElementNotExists
	 * @description verifies whether element does not exists in the screen
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndIsElementNotExists(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndIsElementNotExists keyword is invoked");
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				testDriver.getWebDriver().findElement(objLocator);
				DriverScript.logMessage(testDriver,"error", locator+" locator exists in the screen");
				throw new ElementFailException(new Throwable(locator+" locator exists in the screen"));
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"info", locator + " exists");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndIsElementNotExists keyword is executed");
	}


	/**
	 * @name isElementDisplayed
	 * @description verifies whether element exists and displayed in the screen
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementDisplayed(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "isElementDisplayed keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " locator is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " locator is displayed");
					} else {
						DriverScript.logMessage(testDriver,"error", locator + " locator found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator + " locator found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " locator found, but its not displayed"));
				}
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
		DriverScript.logMessage(testDriver,"info", "Is element Displayed keyword is executed");
	}

	/**
	 * @name setValueToXpathAndElementDisabled
	 * @description verifies whether element exists and disabled
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndElementDisabled(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndElementDisabled keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"error", locator + " element is enabled");
					throw new ElementFailException(
							new Throwable( locator + " element is enabled"));
				}
				else {
					DriverScript.logMessage(testDriver,"info", locator + " element is disabled");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndElementDisabled keyword is executed");
	}

	
	
	/**
	 * @name isElementDisabled
	 * @description verifies whether element exists and disabled
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementDisabled(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "isElementDisabled keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"error", locator + " element is enabled");
					throw new ElementFailException(
							new Throwable( locator + " element is enabled"));
				}
				else {
					DriverScript.logMessage(testDriver,"info", locator + " element is disabled");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "isElementDisabled keyword is executed");
	}

	
	/**
	 * @name setValueToXpathAndgetElementAttributeValue
	 * @description Extracts attribute value and saved in the map to retrieve
	 *              the value for further execution
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndGetElementAttributeValue(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndGetElementAttributeValue keyword is invoked");
		WebElement element = null;
		String attribute = null;

		String locator = (String) params.get("arg0");
		attribute = (String) params.get("arg2");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);

				if (element.getAttribute(attribute) != null && element.getAttribute(attribute) != "") {
					String value = element.getAttribute(attribute);
					testDriver.getMapValues().put(locator, value);
					DriverScript.logMessage(testDriver,"info", "Get element Attribute Value successful");
				} else {
					DriverScript.logMessage(testDriver,"info", locator + " element found and unable to get attribute value");
					throw new ElementFailException(locator + " element found and unable to get attribute value");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndGetElementAttributeValue keyword is executed");

	}
	
	
	/**
	 * @name getElementAttributeValue
	 * @description Extracts attribute value and saved in the map to retrieve
	 *              the value for further execution
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void getElementAttributeValue(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "Get element Attribute Value keyword is invoked");
		WebElement element = null;
		String attribute = null;

		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		attribute = (String) params.get("arg1");
		// for rest Service

		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);

				if (element.getAttribute(attribute) != null && element.getAttribute(attribute) != "") {
					String value = element.getAttribute(attribute);
					testDriver.getMapValues().put(locator, value);
					DriverScript.logMessage(testDriver,"info", "Get element Attribute Value successful");
				} else {
					DriverScript.logMessage(testDriver,"info", locator + " element found and unable to get attribute value");
					throw new ElementFailException(locator + " element found and unable to get attribute value");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Get element Attribute Value keyword is executed");

	}


	/**
	 * @name clear
	 * @description clears old data of the textbox element
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions.clear, XpathOfTargetElement
	 */
	public void clear(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "Clear keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				element.clear();
				DriverScript.logMessage(testDriver,"info", locator + " clear is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to clear the textbox");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to clear the textbox"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Clear keyword is executed");

	}

	/**
	 * @name mouseHover
	 * @description mouse hover on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.mouseHover , XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void mouseHover(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Mouse Hover keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(testDriver.getWebDriver());
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						action.moveToElement(element).build().perform();
						DriverScript.logMessage(testDriver,"info", "Mouse Hover on " + locator + " is successful");
					} else {
						DriverScript.logMessage(testDriver,"error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator + " element found, but its disabled");
					throw new ElementFailException(
							new Throwable(locator + " element found, but its disabled" + locator));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to mousehover");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to mousehover"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error", "Locator found and not visible to perform operation ");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Mouse Hover keyword is executed");

	}
	
	/**
	 * @name mouseHover
	 * @description mouse hover on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.mouseHover , XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndMouseHover(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "Mouse Hover keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(testDriver.getWebDriver());
				element = testDriver.getWebDriver().findElement(objLocator);
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info", locator + " element is displayed");
						action.moveToElement(element).build().perform();
						DriverScript.logMessage(testDriver,"info", "Mouse Hover on " + locator + " is successful");
					} else {
						DriverScript.logMessage(testDriver,"error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error", locator + " element found, but its disabled");
					throw new ElementFailException(
							new Throwable(locator + " element found, but its disabled" + locator));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to mousehover");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to mousehover"));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error", "Locator found and not visible to perform operation ");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "Mouse Hover keyword is executed");

	}

	/**
	 * @name injectJsOnNotVisible
	 * @param arg1
	 * @return void
	 * @throws ElementFailException
	 */
	private void injectJsOnNotVisible(TestDriver testDriver,WebElement element, ElementNotVisibleException e) throws ElementFailException {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) testDriver.getWebDriver();
		if ((Boolean) javascriptExecutor.executeScript("arguments[0].click();", element)) {
			DriverScript.logMessage(testDriver,"info", "Click Successful on triggering javscript");
		} else {
			throw new ElementFailException(new Throwable("Unable to  perform  click opearation"));
		}
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
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		System.out.println("$$$$$$$$Locator fetched is "+objLocator);
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = testDriver.getWebDriver().findElement(objLocator);
				System.out.println("Element is "+element);
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
				injectJsOnNotVisible(testDriver,element, e);
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
	public void setValueToXpathAndClickUsingJavscript(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndClickUsingJavscript keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = testDriver.getWebDriver().findElement(objLocator);
				DriverScript.logMessage(testDriver,"info", locator + " element is enabled");
					 ((JavascriptExecutor)testDriver.getWebDriver()).executeScript("arguments[0].click();", element);
					DriverScript.logMessage(testDriver,"info", locator + " is clicked");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation "));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(testDriver,element, e);
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndClickUsingJavscript keyword is executed");

	}

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
		final By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		int maxWait = Integer.parseInt(testDriver.getGlobalParamMap().get("wait.high"));
		DriverScript.logMessage(testDriver,"info", "Maximum of " + maxWait + " seconds wait will be applied to search for "+locator);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(testDriver.getWebDriver()).withTimeout(maxWait, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class);
		try {
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return testDriver.getWebDriver().findElement(objLocator);
				}
			});
			DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		} catch (TimeoutException ex) {
			DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen"));
		}
		DriverScript.logMessage(testDriver,"info", "waitForObjectToLoad keyword is invoked");
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
		final By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		int maxWait = Integer.parseInt(testDriver.getGlobalParamMap().get("wait.high"));
		DriverScript.logMessage(testDriver,"info", "Maximum of " + maxWait + " seconds wait will be applied to search for the ");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(testDriver.getWebDriver()).withTimeout(maxWait, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		try {
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return testDriver.getWebDriver().findElement(objLocator);
				}
			});
		} catch (TimeoutException ex) {
			DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen"));
		}

		DriverScript.logMessage(testDriver,"info", "setXpathAndwaitForObjectToLoad keyword is invoked");
	}

		/**
	 * xpath must contain $$$value$$$.$$$value$$$ will be replaced by the actual
	 * value passed and then set the value in the text box
	 * 
	 * @name setValueToXpathAndSetText
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then set the value in the
	 *              text box
	 * @param params
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @return void
	 */
	public void setValueToXpathAndSetText(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndSetText keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		String inputext = (String) params.get("arg2");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				element.clear();
				DriverScript.logMessage(testDriver,"info", locator + " is cleared ");
				element.sendKeys(inputext);
				int counter = 0;
				
				while(counter<5){
					element = testDriver.getWebDriver().findElement(objLocator);
					JavascriptExecutor executor = (JavascriptExecutor) testDriver.getWebDriver();
					String value = (String) executor.executeScript("return arguments[0].value", element);
					if(!value.equals(inputext)){
						DriverScript.logMessage(testDriver,"info", "Retrying set text.. "+inputext);
					element.clear();
					element.sendKeys(inputext);
					}else{
						break;
					}
				}
				DriverScript.logMessage(testDriver,"info", inputext + " is passed in to " + locator + " element");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndSetText keyword is executed");

	}
	
	/**
     * @name setTextUsingJavaScript
     * @description to provide input value to the textbox element
     * @param arg1
     * @return void
     * @usage Actions.setText, XpathOfTargetElement, Value
     * @throws ObjectNameNotFoundException
     * @throws ElementFailException
     * @throws BrowserException
     */
     public void setTextUsingJavaScript(TestDriver testDriver,HashMap<String, Object> params)
                   throws ObjectNameNotFoundException, ElementFailException, BrowserException {
            DriverScript.logMessage(testDriver,"info", "Set element Text keyword is invoked");
            WebElement element = null;
            String locator = (String) params.get("arg0");
            By objLocator = ObjectRead.getObject(testDriver,locator,"web");
            String inputext = (String) params.get("arg1");
            // for rest Service
            DriverScript.logMessage(testDriver,"info", "locator is " + locator);
            DriverScript.logMessage(testDriver,"info", "inputext is " + inputext);
            if (testDriver.getWebDriver() == null) {
                   DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
                   throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
            } else if (locator != null && locator.length() > 0) {
                   try {
                         element = testDriver.getWebDriver().findElement(objLocator);
                         DriverScript.logMessage(testDriver,"info", locator + " is cleared ");
                         ((JavascriptExecutor) testDriver.getWebDriver()).executeScript("arguments[0].value = '"+inputext+"';",element);
                         DriverScript.logMessage(testDriver,"info", inputext + " is passed in to " + locator + " element");
                   } catch (InvalidSelectorException e) {
                         DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
                         throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
                   } catch (NoSuchElementException e) {
                         DriverScript.logMessage(testDriver,"error",  "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
                         throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext));
                   } catch (WebDriverException e) {
                         DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
                         throw new ElementFailException(new Throwable("Error in Webdriver " + e));
                   }
            } else {
                   DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
                   throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
            }
            DriverScript.logMessage(testDriver,"info", "Set element Text keyword is executed");

     }

	/**
	 * xpath must contain $$$value$$$.$$$value$$$ will be replaced by the actual
	 * value passed and then set the value in the text box
	 * 
	 * @name setValueToXpathAndSetTextUsingJavascript
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then set the value in the
	 *              text box
	 * @param params
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @return void
	 */
	public void setValueToXpathAndSetTextUsingJavascript(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndSetText keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		String inputext = (String) params.get("arg2");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(objLocator);
				DriverScript.logMessage(testDriver,"info", locator + " is cleared ");
				Actions act = new Actions(testDriver.getWebDriver());
		        act.keyUp(Keys.SHIFT).sendKeys(element, inputext).keyDown(Keys.SHIFT)
		                .build().perform();
				DriverScript.logMessage(testDriver,"info", inputext + " is passed in to " + locator + " element");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndSetText keyword is executed");

	}

	/**
	 * @name setvalueToXpathAndIsElementExist * @description xpath must contain
	 *       $$$value$$$.$$$value$$$ will be replaced by the actual value passed
	 *       and then to verify whether element exists.
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 */
	public void setvalueToXpathAndIsElementExist(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setvalueToXpathAndIsElementExist keyword is invoked");
		// List<WebElement> element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		// for rest Service
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				testDriver.getWebDriver().findElement(objLocator);
				DriverScript.logMessage(testDriver,"info", locator + " exists ");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setvalueToXpathAndIsElementExist Exist keyword is executed");
	}

	/**
	 * @name getTextUsingJavascript
	 * @description Extract the element text using javascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 * @description Extract text from element and save it
	 */
	public void getTextUsingJavascript(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "getTextUsingJavascript keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				String value = null;
				element = testDriver.getWebDriver().findElement(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) testDriver.getWebDriver();
				value = (String) executor.executeScript("return arguments[0].value", element);

				if (value != null && value != "") {

					testDriver.getMapValues().put(locator, value);
					DriverScript.logMessage(testDriver,"info", "Extracted text is " + value);
					DriverScript.logMessage(testDriver,"info", "Get element Text successful and saved in to map");
				} else {
					DriverScript.logMessage(testDriver,"error", "element found, but  element Text Not Found");
					throw new ElementFailException("element found, but  element Text Not Found");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "getTextUsingJavascript is executed");

	}
	
	
	/**
	 * @name setValueToXpathAndGetTextUsingJavascript
	 * @description Extract the element text using javascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 * @description Extract text from element and save it
	 */
	public void setValueToXpathAndGetTextUsingJavascript(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndGetTextUsingJavascript keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator, xpathValue,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				String value = null;
				element = testDriver.getWebDriver().findElement(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) testDriver.getWebDriver();
				value = (String) executor.executeScript("return arguments[0].value", element);

				if (value != null && value != "") {

					testDriver.getMapValues().put(locator, value);
					DriverScript.logMessage(testDriver,"info", "Extracted text is " + value);
					DriverScript.logMessage(testDriver,"info", "Get element Text successful and saved in to map");
				} else {
					DriverScript.logMessage(testDriver,"error", "element found, but  element Text Not Found");
					throw new ElementFailException("element found, but  element Text Not Found");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		DriverScript.logMessage(testDriver,"info", "setValueToXpathAndGetTextUsingJavascript is executed");

	}
	
	
	/**
	 * @name getElementsCount
	 * @description Get the count of elements 
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 * @description Extract text from element and save it
	 */
	public void getElementsCount(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		DriverScript.logMessage(testDriver,"info", "getElementsCount keyword is invoked");
		List<WebElement> element  = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		DriverScript.logMessage(testDriver,"info", "locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				String value = null;
				element = testDriver.getWebDriver().findElements(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) testDriver.getWebDriver();
//				value = (String) executor.executeScript("return arguments[0].value", element.size());
				int count=testDriver.getWebDriver().findElements(objLocator).size();
                    System.out.println("Elements count="+count);
                    value=String.valueOf(count);
					testDriver.getMapValues().put(locator, value);
					DriverScript.logMessage(testDriver,"info", "Count of elements is " + value);
					DriverScript.logMessage(testDriver,"info", "getElementsCount successful and saved in to map");
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
		DriverScript.logMessage(testDriver,"info", "getElementsCount is executed");

	}




}