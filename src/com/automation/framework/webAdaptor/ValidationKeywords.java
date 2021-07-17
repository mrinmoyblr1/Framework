package com.automation.framework.webAdaptor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.Validation;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;

public class ValidationKeywords implements Validation {
	
	/**
	 * @name verifyBrokenLink
	 * @description verify the broken lines
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyBrokenLink(TestDriver testDriver,HashMap<String, Object> params)
                  throws BrowserException, ObjectNameNotFoundException,
                  ElementFailException {
            DriverScript.logMessage(testDriver,"info","verifyBrokenLink is invoked");
            // List<WebElement> element = null;
            String locator = (String) params.get("arg0");
            By objLocator = ObjectRead.getObject(testDriver,locator,"web");
            // for rest Service
            DriverScript.logMessage(testDriver,"info","locator is " + locator);
            if (testDriver.getWebDriver() == null) {
                DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
                  throw new BrowserException(
                              new Throwable(
                                          "Browser is not launched or Driver is failed to initialize"));
            } else if (locator != null && locator.length() > 0) {
                  try {
                        String linkName = testDriver.getWebDriver().findElement(objLocator).getAttribute("href");
                              URL url = new URL(linkName);
                              HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                              connection.setRequestMethod("GET");
                              connection.connect();

                              int code = connection.getResponseCode();
                            
                              if (code==200)
                              {
                                DriverScript.logMessage(testDriver,"info",locator +" and "+linkName +" link is working fine");
                              }else{
                                  DriverScript.logMessage(testDriver,"error",linkName +" link is broken");
                                          throw new ElementFailException(new Throwable(linkName +" link is broken"));
                              }

                  } catch (MalformedURLException | ProtocolException e) {
                      DriverScript.logMessage(testDriver,"error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                                    } catch (InvalidSelectorException e) {
                      DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
                        throw new ElementFailException(new Throwable(
                                    "Invalid xpath, verify the xpath syntax: " + locator));
                  } catch (NoSuchElementException e) {
                      DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found:," + locator+" ,"+locator+" locator link does not exists in the screen to verify broken link");
                        throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator link does not exists in the screen to verify broken link"));
                  } catch (WebDriverException e) {
                      DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in Webdriver " + e));
                  } catch (IOException e) {
                      DriverScript.logMessage(testDriver,"error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                  }
            } else {
                DriverScript.logMessage(testDriver,"error","Incorrect parameters or error while parsing parameter");
                  throw new ElementFailException(new Throwable(
                              "Incorrect parameters or error while parsing parameter"));
            }
            DriverScript.logMessage(testDriver,"info","verifyBrokenLink action is executed");
      }

/**
	 * @name setValueToXpathAndverifyElementText
	 * @description set Value To Xpath And verifyElementText
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void setValueToXpathAndverifyElementText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Verify Element Text action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		
		String inputext = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator,inputext,"web");
		String expectedText = (String) params.get("arg2");
		
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				DriverScript.logMessage(testDriver,"info","Expected text is " + expectedText);
				String actualText = null;
				//Modified by injecting javascript
				/*element = D2testDriver.getWebDriver().findElement(objLocator);
				actualText = element.getText();*/
				
				element= testDriver.getWebDriver().findElement(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) testDriver.getWebDriver();
                actualText  = (String) executor.executeScript("return arguments[0].innerText",element);
                DriverScript.logMessage(testDriver,"info","expected value=" + expectedText
						+ " and actual value=" + actualText);
                
				if (!(actualText.equals(expectedText))) {
					DriverScript.logMessage(testDriver,"error","Expected value for a locator "+locator+" " + expectedText
							+" and actual value is " + actualText);
					throw new ElementFailException(new Throwable("Expected value for a locator "+locator+" " + expectedText
							+" and actual value is " + actualText));
				}
				DriverScript.logMessage(testDriver,"info","setValueToXpathAndverifyElementText is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Verify Element Text action is executed");

	}
	/**
	 * @name verifyElementText
	 * @description verifyElementText
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.VerifyElementText , XpathOfTargetElement ,ValueWhichYouWantToCompare
	 */
	public void verifyElementText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Verify Element Text action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String expectedText = (String) params.get("arg1");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				DriverScript.logMessage(testDriver,"info","Expected text is " + expectedText);
				String actualText = null;
				element = testDriver.getWebDriver().findElement(ObjectRead
						.getObject(testDriver,locator,"web"));
				actualText = element.getText();
				DriverScript.logMessage(testDriver,"info","Actual text is " + actualText);
				if (!(actualText.equals(expectedText))) {
					DriverScript.logMessage(testDriver,"error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				DriverScript.logMessage(testDriver,"info","verify element text is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Verify Element Text action is executed");

	}
	


	/**
	 * @name verifyBrokenLinkByLinkName
	 * @description verify the broken lines
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyBrokenLinkByLinkName(TestDriver testDriver,HashMap<String, Object> params)
                  throws BrowserException, ObjectNameNotFoundException,
                  ElementFailException {
            DriverScript.logMessage(testDriver,"info","verifyBrokenLinkByLinkName is invoked");
            // List<WebElement> element = null;
            String linkName = (String) params.get("arg0");
            
            // for rest Service
            DriverScript.logMessage(testDriver,"info","Link is " + linkName);
            if (testDriver.getWebDriver() == null) {
                DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
                  throw new BrowserException(
                              new Throwable(
                                          "Browser is not launched or Driver is failed to initialize"));
            } 
                  try {
                              URL url = new URL(linkName);
                              HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                              connection.setRequestMethod("GET");
                              connection.connect();

                              int code = connection.getResponseCode();
                            
                              if (code==200)
                              {
                                DriverScript.logMessage(testDriver,"info",linkName +" link is working fine");
                              }else{
                                  DriverScript.logMessage(testDriver,"error",linkName +" link is broken");
                                          throw new ElementFailException(new Throwable(linkName +" link is broken"));
                              }

                  } catch (MalformedURLException | ProtocolException e) {
                      DriverScript.logMessage(testDriver,"error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                  } catch (WebDriverException e) {
                      DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in Webdriver " + e));
                  } catch (IOException e) {
                      DriverScript.logMessage(testDriver,"error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                  }
            
            DriverScript.logMessage(testDriver,"info","verifyBrokenLinkByLinkName action is executed");
      }


	/**
	 * @name verifyPageTitle
	 * @description verify the page title
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyPageTitle(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Verify Page Title action is invoked");
		
		String expectedTitle = (String) params.get("arg0");
		DriverScript.logMessage(testDriver,"info","Expected Title is "+expectedTitle);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				String actualTitle = testDriver.getWebDriver().getTitle();
				DriverScript.logMessage(testDriver,"info","Page Title is "+actualTitle);
				if (!(actualTitle.equals(expectedTitle))) {
					throw new ElementFailException(new Throwable(
							"Page title mismatch"));
				}
				DriverScript.logMessage(testDriver,"info","Verify Page Title action is successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Verify Page Title action is executed");

	}
	
	/**
	 * @name verifyPageTitle
	 * @description verify the page title
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void setValueToXpathAndverifyElementContainsText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","setValueToXpathAndverifyElementContainsText action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		
		String inputext = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(testDriver,locator,inputext,"web");
		String expectedText = (String) params.get("arg2");
		
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				DriverScript.logMessage(testDriver,"info","Expected text is " + expectedText);
				String actualText = null;
				element = testDriver.getWebDriver().findElement(objLocator);
				actualText = element.getText();
                DriverScript.logMessage(testDriver,"info","expected value=" + expectedText
						+ " and actual value=" + actualText);
                
				if (!(actualText.equals(expectedText))) {
					DriverScript.logMessage(testDriver,"error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				DriverScript.logMessage(testDriver,"info","setValueToXpathAndverifyElementText is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","setValueToXpathAndverifyElementContainsText action is executed");

	}
	

	
	/**
	 * @name verifyTextUsingJavascript
	 * @description verifyTextUsingJavascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.VerifyElementText , XpathOfTargetElement ,ValueWhichYouWantToCompare
	 */
	public void verifyTextUsingJavascript(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","verifyElementContainsText action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String expectedText = (String) params.get("arg1");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				DriverScript.logMessage(testDriver,"info","Expected text is " + expectedText);
				String actualText = null;
				element = testDriver.getWebDriver().findElement(ObjectRead
						.getObject(testDriver,locator,"web"));
				actualText = element.getText();
				DriverScript.logMessage(testDriver,"info","Actual text is " + actualText);
				if (!(actualText.contains(expectedText))) {
					DriverScript.logMessage(testDriver,"error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				DriverScript.logMessage(testDriver,"info","verify element text contains is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","verifyElementContainsText action is executed");

	}
	
	/**
	 * @name verifyElementContainsText
	 * @description verifyElementText
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.VerifyElementText , XpathOfTargetElement ,ValueWhichYouWantToCompare
	 */
	public void verifyElementContainsText(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","verifyElementContainsText action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String expectedText = (String) params.get("arg1");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				DriverScript.logMessage(testDriver,"info","Expected text is " + expectedText);
				String actualText = null;
				element = testDriver.getWebDriver().findElement(ObjectRead
						.getObject(testDriver,locator,"web"));
				actualText = element.getText();
				DriverScript.logMessage(testDriver,"info","Actual text is " + actualText);
				if (!(actualText.contains(expectedText))) {
					DriverScript.logMessage(testDriver,"error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				DriverScript.logMessage(testDriver,"info","verify element text contains is successful");
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","verifyElementContainsText action is executed");

	}
	
	/**
	 * @name setValueToXpathAndVerifyElementTextUsingJavascript
	 * @description verify element text using javascript by passing xpath value
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void setValueToXpathAndVerifyElementTextUsingJavascript(TestDriver testDriver,HashMap<String, Object> params)
              throws BrowserException, ObjectNameNotFoundException,
              ElementFailException {
				DriverScript.logMessage(testDriver,"info","stValueToXpathAndVerifyElementTextUsingJavascript action is invoked");
				WebElement element = null;
				String locator = (String) params.get("arg0");
				String xapthValue = (String) params.get("arg1");
				By objLocator = ObjectRead.getObject(testDriver,locator,xapthValue,"web");
				String expectedText = (String) params.get("arg2");
				DriverScript.logMessage(testDriver,"info","locator is " + locator);
				if (testDriver.getWebDriver() == null) {
		              DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
		              throw new BrowserException(new Throwable(
		            		  "Browser is not launched or Driver is failed to initialize"));
				} else if (locator != null && locator.length() > 0) {
		              try {
                              String actualText = null;
                              DriverScript.logMessage(testDriver,"info","Expected Text= " + expectedText);
                              element= testDriver.getWebDriver().findElement(objLocator);
                              JavascriptExecutor executor = (JavascriptExecutor)testDriver.getWebDriver();
                              actualText = (String) executor.executeScript("return arguments[0].value", element);
                              DriverScript.logMessage(testDriver,"info","Text from the given locator is actualText=" + actualText);
                             
                              if (actualText != null && actualText!= "") {                                             
                          
                            	  DriverScript.logMessage(testDriver,"info","setValueToXpathAndVerifyElementTextUsingJavascript is successful");
                                          if (!(actualText.equals(expectedText))) {
                            					DriverScript.logMessage(testDriver,"error","expected value= " + expectedText
                            							+ " and actual value is " + actualText);
                            					throw new ElementFailException(new Throwable(
                            							"expected value= " + expectedText
                            									+ " and actual value is " + actualText));
                            				}
                                          
                              } else {
                                          DriverScript.logMessage(testDriver,"error","Element found, but  Element Text Not Found");
                                          throw new ElementFailException(
                                                      "Element found, but  Element Text Not Found");
                              }
                              
                             
              				
		              } catch (InvalidSelectorException e) {
		                              DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid xpath, verify the xpath syntax: " + locator));
		              } catch (NoSuchElementException e) {
		                              DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid locator or locator not found: " + locator));
		              } catch (WebDriverException e) {
		                              DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
		                              throw new ElementFailException(new Throwable(
		                                                              "Error in Webdriver " + e));
		              }
				} else {
				              DriverScript.logMessage(testDriver,"error","Incorrect parameters or error while parsing parameter");
				              throw new ElementFailException(new Throwable(
				                                              "Incorrect parameters or error while parsing parameter"));
				}
				DriverScript.logMessage(testDriver,"info","stValueToXpathAndVerifyElementTextUsingJavascript action is executed");
		
		}
	
	/**
	 * @name verifyElementTextUsingJavascript
	 * @description verify element text using javascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyElementTextUsingJavascript(TestDriver testDriver,HashMap<String, Object> params)
              throws BrowserException, ObjectNameNotFoundException,
              ElementFailException {
				DriverScript.logMessage(testDriver,"info","verifyElementTextUsingJavascript action is invoked");
				WebElement element = null;
				String locator = (String) params.get("arg0");
				By objLocator = ObjectRead.getObject(testDriver,locator,"web");
				String expectedText = (String) params.get("arg1");
				DriverScript.logMessage(testDriver,"info","locator is " + locator);
				if (testDriver.getWebDriver() == null) {
		              DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
		              throw new BrowserException(new Throwable(
		            		  "Browser is not launched or Driver is failed to initialize"));
				} else if (locator != null && locator.length() > 0) {
		              try {
                              String actualText = null;
                              DriverScript.logMessage(testDriver,"info","Expected Text= " + expectedText);
                              element= testDriver.getWebDriver().findElement(objLocator);
                              JavascriptExecutor executor = (JavascriptExecutor)testDriver.getWebDriver();
                              actualText = (String) executor.executeScript("return arguments[0].value", element);
                              DriverScript.logMessage(testDriver,"info","Text from the given locator is actualText=" + actualText);
                             
                              if (actualText != null && actualText!= "") {                                             
                          
                            	  DriverScript.logMessage(testDriver,"info","verifyElementTextUsingJavascript successful");
                                          if (!(actualText.equals(expectedText))) {
                            					DriverScript.logMessage(testDriver,"error","expected value= " + expectedText
                            							+ " and actual value is " + actualText);
                            					throw new ElementFailException(new Throwable(
                            							"expected value= " + expectedText
                            									+ " and actual value is " + actualText));
                            				}
                                          
                              } else {
                                          DriverScript.logMessage(testDriver,"error","Element found, but  Element Text Not Found");
                                          throw new ElementFailException(
                                                      "Element found, but  Element Text Not Found");
                              }
                              
                             
              				
		              } catch (InvalidSelectorException e) {
		                              DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid xpath, verify the xpath syntax: " + locator));
		              } catch (NoSuchElementException e) {
		                              DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid locator or locator not found: " + locator));
		              } catch (WebDriverException e) {
		                              DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
		                              throw new ElementFailException(new Throwable(
		                                                              "Error in Webdriver " + e));
		              }
				} else {
				              DriverScript.logMessage(testDriver,"error","Incorrect parameters or error while parsing parameter");
				              throw new ElementFailException(new Throwable(
				                                              "Incorrect parameters or error while parsing parameter"));
				}
				DriverScript.logMessage(testDriver,"info","verifyElementTextUsingJavascript action is executed");
		
		}
	  
	/**
	 * @name verifyPageURL
	 * @description Verifies page tiltle with the expected input URL
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyPageURL(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Verify Page URL  action is invoked");
		String expectedURL = null;
		String inputParams = (String) params.get("arg0");
		expectedURL = inputParams;
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				DriverScript.logMessage(testDriver,"info","Expected URL is " + expectedURL);
				String actualURL = null;
				actualURL = testDriver.getWebDriver().getCurrentUrl();
				DriverScript.logMessage(testDriver,"info","Actual URL is " + actualURL);
				if (!(actualURL.equals(expectedURL))) {
					DriverScript.logMessage(testDriver,"error","expected value= " + expectedURL
							+ " and actual value is " + actualURL);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedURL
									+ " and actual value is " + actualURL));
				}
				DriverScript.logMessage(testDriver,"info","verifyPageURL is successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Verify Page URL action is executed");

	}
}