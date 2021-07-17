package com.automation.framework.exceptions;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.framework.pojs.TestStepResults;

public class ElementFailException extends Exception {
	/**
	 * 
	 */
	public final static Logger logger = Logger
			.getLogger(ElementFailException.class);
	public enum ExceptionType {

		InvalidSelectorException, NoSuchElementException, WebDriverException ,ElementNotVisibleException, Exception
    }
	private static final long serialVersionUID = 1L;
	// Each exception message will be hold here
	private String message;
	public ElementFailException() {
        super();
    }

	public ElementFailException(String s) {
        super(s);
    }
	
	public ElementFailException(Throwable cause) {
		super(cause);
	}

  
	public ElementFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElementFailException(ExceptionType exceptionType, TestStepResults stepResults,
			WebDriver driver, WebElement element, Exception e) {
		switch (exceptionType) {
		case InvalidSelectorException: failMessage("Invalid xpath, verify the xpath syntax",stepResults, e);
			break;
		case NoSuchElementException: failMessage("Invalid locator or locator not found ",stepResults, e);
			break;
		case WebDriverException: failMessage("Error in Webdriver",stepResults, e);
			break;
		case ElementNotVisibleException :  JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		if ((Boolean) javascriptExecutor.executeScript("arguments[0].click();",
				element)) {
			stepResults.setStatus("Pass");
			stepResults.setComment("Clicked successfully");
			stepResults.setErrMsg("");

		} else {
			failMessage("Locator found and not visible to perform click operation",stepResults, e);
		}	
		default: 
			failMessage("Error occured",stepResults, e);
			break;
		}
	}
	
	private void failMessage(String msg,TestStepResults stepResults, Exception e) {
		logger.error(msg + e.getMessage());
		stepResults.setStatus("fail");
		stepResults.setComment(msg);
		stepResults.setErrMsg(msg + e.getMessage());
	}

	// Message can be retrieved using this accessor method
	public String getMessage() {
		return message;
	}
}
