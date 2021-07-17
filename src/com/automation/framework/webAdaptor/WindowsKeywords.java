package com.automation.framework.webAdaptor;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.Window;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;

public class WindowsKeywords implements Window {


	/**
	 * @name closeBrowser
	 * @description close current browser
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.closeBrowser
	 */
	public void closeBrowser(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Close Browser action is invoked");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				testDriver.getWebDriver().quit();
				DriverScript.logMessage(testDriver,"info","Close Browser successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Close Browser action is executed");

	}
	
	
	/**
	 * @name handleDownloadPopup
	 * @description handleDownload popup
	 * @param params
	 * @throws BrowserException
	 * @throws ElementFailException
	 * @usage Actions.closeBrowser
	 */
	public void handleDownloadPopup(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException  {
		DriverScript.logMessage(testDriver,"info","handleDownloadPopup action is invoked");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);

				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);

				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				DriverScript.logMessage(testDriver,"info","handleDownloadPopup successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}catch (AWTException e) {
				DriverScript.logMessage(testDriver,"error","Error in Robot class" + e);
				throw new ElementFailException(new Throwable("Error in Robot class" + e));
			}
		}
		DriverScript.logMessage(testDriver,"info","handleDownloadPopup action is executed");

	}

	/**
	 * @name navigateToURL
	 * @description pass URL
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.navigateToURL, URL
	 */
	public void navigateToURL(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Navigate To URL action is invoked");
		String URL = (String) params.get("arg0");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				DriverScript.logMessage(testDriver,"info","URl " + URL);
				if (URL != "" && URL != null) {
					if (URL.contains("http://") || URL.contains("https://")) {
						testDriver.getWebDriver().get(URL);
						DriverScript.logMessage(testDriver,"info","Navigate To URL successful");
					} else {
						URL = "http://" + URL;
						testDriver.getWebDriver().get(URL);
						DriverScript.logMessage(testDriver,"info","Navigate To URL successful");
					}
				} else {
					DriverScript.logMessage(testDriver,"info","INVALID URL");
					throw new ElementFailException(new Throwable("INVALID URL"));
				}
				DriverScript.logMessage(testDriver,"info","Navigate To URL successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Navigate To URL action is executed");
	}
	
	/**
	 * @name navigateToPreviousPage
	 * @description pass URL
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.navigateToURL, URL
	 */
	
	public void navigateToPreviousPage(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Navigate To Previous Page action is invoked");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				testDriver.getWebDriver().navigate().back();
				DriverScript.logMessage(testDriver,"info","Navigate To Previous Page successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Navigate To Previous Page action is executed");
	}
	
	/**
	 * @name navigateToForwardPage
	 * @description pass URL
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.navigateToURL, URL
	 */
	public void navigateToForwardPage(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Navigate To Forward Page action is invoked");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				testDriver.getWebDriver().navigate().forward();
				DriverScript.logMessage(testDriver,"info","Navigate To Forward Page successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Navigate To Forward Page action is executed");
	}
	
	
	/**
	 * @name switchToFrameBasedOnWebElement
	 * @description Switch to frame of same window based on webelement
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchToFrameBasedOnWebElement(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Switch To Frame action is invoked");
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(testDriver,locator,"web");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				WebElement element = testDriver.getWebDriver().findElement(objLocator);
				testDriver.getWebDriver().switchTo().frame(element);
				DriverScript.logMessage(testDriver,"info","Switch To Frame successful");
			}catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Switch To Frame action is executed");
	}
	
	/**
	 * @name switchToFrameBasedOnFrameNo
	 * @description Switch to frame of same window based on webelement
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchToFrameBasedOnFrameNo(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Switch To Frame action is invoked");
		String frameNoArg = (String) params.get("arg0");
		int frameNo = Integer.parseInt(frameNoArg);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				testDriver.getWebDriver().switchTo().frame(frameNo);
				DriverScript.logMessage(testDriver,"info","Switch To Frame successful");
			}catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Switch To Frame action is executed");
	}

	
	/**
	 * @name switchBetweenWindows
	 * @description switch between the browser windows
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchBetweenWindows(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Switch Between Windows action is invoked");

		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				String handle = testDriver.getWebDriver().getWindowHandle();
				Set<String> handles = testDriver.getWebDriver().getWindowHandles();
				if (handles != null && handles.size() > 1) {
					for (String hnd : handles) {
						if (!hnd.equals(handle)) {
							testDriver.getWebDriver().switchTo().window(hnd);
							DriverScript.logMessage(testDriver,"info","Switch Between Windows successful");
						}
					}
				} else {
					DriverScript.logMessage(testDriver,"error","No window found for switch");
					throw new ElementFailException(new Throwable("No window found for switch"));
				}
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Switch Between Windows action is executed");
	}

	/**
	 * @name switchToWindowTitle
	 * @description switch to window title
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	@SuppressWarnings("unused")
	public void switchToWindowTitle(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Switch To Window Title action is invoked");
		String URL = null;
		String inputParams = (String) params.get("arg0");
		String windowTitle = null;
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				if (windowTitle != null && windowTitle.length() > 0) {
					testDriver.getWebDriver().switchTo().window(windowTitle);
					DriverScript.logMessage(testDriver,"info","Switch To Window Title successful");
				} else {
					DriverScript.logMessage(testDriver,"info","Invalid Window Title");
					throw new ElementFailException(new Throwable("Invalid Window Title"));
				}
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver");
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Switch To Window Title action is executed");
	}

	/**
	 * @name maximize
	 * @description maximise the window
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void maximize(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Maximize action is invoked");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				testDriver.getWebDriver().manage().window().maximize();
				DriverScript.logMessage(testDriver,"info","Maximize successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Maximize action is executed");
	}



	/**
	 * @name closeAllWindows
	 * @description close all window
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.closeAllWindow
	 */
	public void closeAllWindows(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Close All Browser action is invoked");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				Set<String> handles = testDriver.getWebDriver().getWindowHandles();
				if (handles != null && handles.size() > 0) {
					for (int i = handles.size() - 1; i >= 0; i--) {
						testDriver.getWebDriver().switchTo().window(handles.toArray()[i].toString()).close();
					}
					DriverScript.logMessage(testDriver,"info","Close All Browser Successful");
				} else {
					DriverScript.logMessage(testDriver,"info","No Window Found To Close");
					throw new ElementFailException(new Throwable("No Window Found To Close"));
				}
				DriverScript.logMessage(testDriver,"info","Close All Browser successful");
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Close All Browser action is executed");
	}

	/**
	 * @name switchToLastWindow
	 * @description Switch to last window
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchToLastWindow(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		DriverScript.logMessage(testDriver,"info","Switch To Last Window action is invoked");
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				Set<String> handles = testDriver.getWebDriver().getWindowHandles();
				if (handles != null && handles.size() > 1) {
					testDriver.getWebDriver().switchTo().window(handles.toArray()[handles.size() - 1].toString());
					DriverScript.logMessage(testDriver,"info","Switch To Last Window successful");
				} else {
					DriverScript.logMessage(testDriver,"info","No window found for switch");
					throw new ElementFailException(new Throwable("No window found for switch"));
				}
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		DriverScript.logMessage(testDriver,"info","Switch To Last Window action is executed");
	}
	
	/**
     * @name switchBackFromFrameBasedWebElement
     * @description Switch back from the frame of same window based on webelement
     * @param params
     * @throws BrowserException
     * @throws ObjectNameNotFoundException
     * @throws ElementFailException
     * @usage Actions
     */
     public void switchBackFromFrameBasedWebElement(TestDriver testDriver,HashMap<String, Object> params)
                   throws BrowserException, ElementFailException, ObjectNameNotFoundException {
            DriverScript.logMessage(testDriver,"info","Navigate To Forward Page action is invoked");
            if (testDriver.getWebDriver() == null) {
                   DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
                   throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
            } else {
                   try {
                         testDriver.getWebDriver().switchTo().defaultContent();
                         DriverScript.logMessage(testDriver,"info","Switch back from the frame successful");
                   } catch (WebDriverException e) {
                         DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
                         throw new ElementFailException(new Throwable("Error in Webdriver "+e));
                   }
            }
            DriverScript.logMessage(testDriver,"info","Switch back from the frame is executed");
     }

}