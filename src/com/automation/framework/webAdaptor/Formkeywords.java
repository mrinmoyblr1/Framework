package com.automation.framework.webAdaptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.FormElements;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;

public class Formkeywords implements FormElements {
	final static Logger logger = Logger.getLogger(Formkeywords.class);

	/**
	 * @name selectCheckBox
	 * @description Select the checkbox element
	 * @usage Actions.selectCheckBox , XpathOfTargetCheckBox
	 * @param params
	 *  @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @return void
	 */
	public void selectCheckBox(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info"," Select Checkbox action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web"));
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info",locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info",locator+ " element is displayed");
						if (element.getAttribute("type").toLowerCase().equals("checkbox")) {
							if (!element.isSelected()) {
								DriverScript.logMessage(testDriver,"info",locator +" CheckBox selected");
								element.click();
								DriverScript.logMessage(testDriver,"info",locator + " Checkbox select successfull");
							} else {
								DriverScript.logMessage(testDriver,"info",locator +" Check Box is Already selected");
								throw new ElementFailException(new Throwable("Check Box is Already selected"));
							}
						} else {
							DriverScript.logMessage(testDriver,"error",locator +"element Found,but  not a check box");
							throw new ElementFailException(new Throwable(locator +" element Found,but  not a check box"));
						}
					} else {
						DriverScript.logMessage(testDriver,"error",locator + "element found, but its not displayed");
						throw new ElementFailException(new Throwable(locator +" element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error",locator+ " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator +" element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error","Locator found and not visible to perform operation ");
				throw new ElementFailException(new Throwable("Locator found and not visible to perform operation "));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Select Checkbox action is executed");

	}

	/**
	 * @name unSelectCheckBox
	 * @description uncheck check box
	 * @usage Actions.UnSelectCheckBox , XpathOfTargetCheckBox
	 * @param params
	 * @return void
	 *  @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void unSelectCheckBox(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info"," Uncheck Checkbox action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web"));
				if (element.getAttribute("type").toLowerCase().equals("checkbox")) {
					if (element.isSelected()) {
						DriverScript.logMessage(testDriver,"info",locator+" CheckBox unchecked");
						element.click();
						DriverScript.logMessage(testDriver,"info",locator+" Checkbox unchecked successful");
					} else {
						DriverScript.logMessage(testDriver,"info",locator+" Check Box is Already unchecked");
						throw new ElementFailException(locator +" Check Box is Already unchecked");
					}
				} else {
					DriverScript.logMessage(testDriver,"info",locator +" element Found,but  not a check box");
					throw new ElementFailException(new Throwable(locator +" element Found,but  not a check box"));
				}

			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Uncheck Checkbox action is executed");

	}

	/**
	 * @name isCheckboxChecked
	 * @description checks checkbox is Checked  
	 * @usage Actions.isCheckboxChecked, XpathOfTargetCheckBox
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isCheckboxChecked(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info"," Is Checkbox Checked action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web"));
				boolean actualValue = element.isSelected();
				if (actualValue) {
					DriverScript.logMessage(testDriver,"info","Check box is selected");
				} else {
					DriverScript.logMessage(testDriver,"info","Check box is not selected");
					throw new ElementFailException("CheckBox is not selected");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error","Locator found and not visible to perform operation ");
				throw new ElementFailException(new Throwable("Locator found and not visible to perform operation "));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Is_Checkbox Checked action is executed");

	}

	/**
	 * @name isRadioButtonSelected
	 * @description passxpath of radio button 
	 * @usage Actions.isRadioButtonSelected, XpathOfTargetRadioElement
	 * @param params
	 * @return void
	 *  @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isRadioButtonSelected(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info"," Is RadioButton Selected action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web"));
				if (element.isSelected()) {
					DriverScript.logMessage(testDriver,"info","Radio Button is Selected");
					DriverScript.logMessage(testDriver,"info","Is Radio button perform successful");
				} else {
					DriverScript.logMessage(testDriver,"info","Radio Button is not selected");
					throw new ElementFailException("Radio Button is not selected");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Is RadioButton Selected action is executed");

	}


	/**
	 * @name selectValueFromDropdown
	 * @description Select value from dropdown 
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	@SuppressWarnings("unused")
	public void selectValueFromDropdown(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info"," Select Value From Dropdown action is invoked");
		String locator = (String) params.get("arg0");
		String text = (String) params.get("arg1");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Select dropdown = new Select(testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web")));
				if (dropdown != null) {
					DriverScript.logMessage(testDriver,"info","Dropdown Found");
					dropdown.selectByVisibleText(text);
					DriverScript.logMessage(testDriver,"info","Select Value From Dropdown successful");
				} else {
					DriverScript.logMessage(testDriver,"error","Dropdown not found");
					throw new ElementFailException("Dropdown not found");
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Select Value From Dropdown action is executed");

	}
	
	
	/**
	 * @name getDropDownvalues
	 * @description getDropDownvalues 
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	@SuppressWarnings("unused")
	public void getDropDownvalues(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info"," getDropdown values action is invoked");
		String locator = (String) params.get("arg0");
		String text = (String) params.get("arg1");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Select dropdown = new Select(testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web")));
				List<WebElement> dropDownList =  dropdown.getOptions();
				List<String> dropDownStr = new ArrayList<String>();
				for (int i = 0; i < dropDownList.size(); i++) {
					dropDownStr.add(dropDownList.get(i).getText());
				}
				String dropDownValues = StringUtils.join(dropDownStr,",");
				testDriver.getMapValues().put(locator, dropDownValues);
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Select Value From Dropdown action is executed");

	}

	/**
	 * @name setMandatoryTextToElement
	 * @description Select text to element 
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setMandatoryTextToElement(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info","Set Mandatory Text To element action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String text = (String) params.get("params");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web"));
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info",locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info",locator+ " element is displayed");
						if (text != null && text != "") {
							element.clear();
							element.sendKeys(text);
							DriverScript.logMessage(testDriver,"info",locator+"Set Text successful");
						} else {
							DriverScript.logMessage(testDriver,"info","given Text : " + text);
							DriverScript.logMessage(testDriver,"error",locator+" element found and text is invalid");
							throw new ElementFailException(new Throwable(locator+" element found and text is invalid"));
						}
					} else {
						DriverScript.logMessage(testDriver,"error","element found, but its not displayed");
						throw new ElementFailException(new Throwable("element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error","element found, but its disabled");
					throw new ElementFailException(new Throwable("element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException("Invalid locator or locator not found  " + e);
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error","Locator found and not visible to perform operation");
				throw new ElementFailException(new Throwable("Locator found and not visible to perform operation "));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Set Mandatory Text To element action is executed");

	}

	/**
	 * @name setOptionalTextToElement
	 * @description Select optional text to element 
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setOptionalTextToElement(TestDriver testDriver,HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		DriverScript.logMessage(testDriver,"info","Set Optional Text To element action is invoked");
		WebElement element = null;
		String text = (String) params.get("arg1");
		String locator = (String) params.get("arg0");
		DriverScript.logMessage(testDriver,"info","locator is " + locator);
		if (testDriver.getWebDriver() == null) {
			DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = testDriver.getWebDriver().findElement(ObjectRead.getObject(testDriver,locator,"web"));
				if (element.isEnabled()) {
					DriverScript.logMessage(testDriver,"info",locator + " element is enabled");
					if (element.isDisplayed()) {
						DriverScript.logMessage(testDriver,"info",locator+ " element is displayed");

						element.clear();
						DriverScript.logMessage(testDriver,"info",locator+ " is cleared " );
						element.sendKeys(text);
						DriverScript.logMessage(testDriver,"info",text +" is passed in to "+locator+" element" );
					
					} else {
						DriverScript.logMessage(testDriver,"error",locator+ " element found, but its not displayed");
						throw new ElementFailException(new Throwable(locator+ " element found, but its not displayed"));
					}
				} else {
					DriverScript.logMessage(testDriver,"error",locator+ " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator+ " element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (ElementNotVisibleException e) {
				DriverScript.logMessage(testDriver,"error",locator+" found and not visible to perform operation");
				throw new ElementFailException(new Throwable(locator+" found and not visible to perform operation "));
			} catch (WebDriverException e) {
				DriverScript.logMessage(testDriver,"error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		DriverScript.logMessage(testDriver,"info","Set Optional Text To element action is executed");

	}

}
