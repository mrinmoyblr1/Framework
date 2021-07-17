package com.automation.framework.mobileAdaptor;

import java.util.HashMap;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;

public class Formkeywords {
	
	public void selectValueFromDropdown(TestDriver testDriver,HashMap<String, Object> params)
            throws ObjectNameNotFoundException, ElementFailException, BrowserException {
      DriverScript.logMessage(testDriver,"info"," Select Value From Dropdown action is invoked");
      String locator = (String) params.get("arg0");
      String text = (String) params.get("arg1");
      DriverScript.logMessage(testDriver,"info","locator is " + locator);
      if (testDriver.getMobileDriver() == null) {
            DriverScript.logMessage(testDriver,"error","Browser is not launched or Driver is failed to initialize");
            throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
      } else if (locator != null && locator.length() > 0) {
            try {
                  Select dropdown = new Select(testDriver.getMobileDriver().findElement(ObjectRead.getObject(testDriver,locator,"mobile")));
                  	DriverScript.logMessage(testDriver,"info","Dropdown Found");
                  	dropdown.selectByVisibleText(text);
                  	DriverScript.logMessage(testDriver,"info","Select Value From Dropdown successful");
            } catch (InvalidSelectorException e) {
                  DriverScript.logMessage(testDriver,"error","Invalid xpath, verify the xpath syntax " + locator);
                  throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
            } catch (NoSuchElementException e) {
                  DriverScript.logMessage(testDriver,"error","Invalid locator or locator not found " + e);
                  throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
            }
      } else {
            DriverScript.logMessage(testDriver,"error","Incorrect parameters or error in parameter");
            throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
      }
      DriverScript.logMessage(testDriver,"info","Select Value From Dropdown action is executed");

}
}
