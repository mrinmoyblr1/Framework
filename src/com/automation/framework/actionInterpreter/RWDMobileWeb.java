package com.automation.framework.actionInterpreter;

import com.automation.framework.actionInterpreter.Mobile.MobileAction;
import com.automation.framework.actionInterpreter.Web.WebAction;
import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.MobileAdaptorException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.pojs.TestDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class RWDMobileWeb {
	public static enum Action  {
		click,getElementsCount,sendKeyboardActions,setValueToXpathAndMouseHover,scrollAndClickBasedOnTagsForLabel,getText,handleDownloadPopup,scrollByKeyDownAndClick,setValueToXpathAndVerifyElementContainsText,verifyElementContainsText,switchToFrameBasedOnWebElement,switchToFrameBasedOnFrameNo,isElementNotExists,setValueToXpathAndVerifyElementTextUsingJavascript,setValueToXpathAndDoubleClick, setValueToXpathAndRightClick, 
		setXpathAndwaitForObjectToLoad, doubleClick, rightClick, IsElementExist, isElementDisplayed, 
		getElementAttributeValue, mouseHover, clear, setValueToXpathAndClick, selectCheckBox, unSelectCheckBox, 
		isCheckboxChecked, isRadioButtonSelected, selectValueFromDropdown, setMandatoryTextToElement, 
		setOptionalTextToElement,setXpathAndScrollAndClick, scrollAndClick, scrollToBottom, handleAlert, injectJsOnNotVisible,scrollDownInTag, 
		verifyPageTitle, verifyElementText, verifyPageURL, implicitWait, waitUntilElementVisible,clickUsingJavascript, 
		waitUntilElementClickable, closeBrowser, navigateToURL, switchBetweenWindows, switchToWindowTitle, 
		maximize,setXpathAndElementCount,elementCount, switchToFrame, closeAllWindows, switchToLastWindow, 
		setText, setValueToXpathAndGetText, setValueToXpathAndverifyElementText, scrollAndViewBasedOnTags,switchBackFromFrameBasedWebElement,
		clickOnContextMenu, setValueToXpathAndSetText, isElementDisabled, setValueToXpathAndIsElementNotExists, setvalueToXpathAndIsElementExist,verifyBrokenLink, 
		isElementNotDisplayed,setValueToXpathAndElementNotDisplayed,setValueToXpathAndElementDisabled,setValueToXpathAndGetTextUsingJavascript,setValueToXpathAndGetElementAttributeValue,scrollAndClickBasedOnTags,setValueToXpathAndSetTextUsingJavascript,verifyBrokenLinkByLinkName,setTextUsingJavaScript,getTextUsingJavascript, verifyTextUsingJavascript,verifyElementTextUsingJavascript
		,inputToTextBoxMobile,verifyChecked,tapToLocationBasedOnElement,doubleTapToLocation,clearText,iselementExists,waitForObjectToLoad,wait, tap,navigateToPreviousPage,tapUsingJavascript,
		navigateToForwardPage,scrollToTop,SetValueToXpathandwaitUntilElementClickable,setValueToXpathAndClickUsingJavscript,getDropDownvalues
		}
	public static void invokeKeyword(TestDriver testDriver,Action action, String... args)
			throws WebAdaptorException,MobileAdaptorException, ObjectNameNotFoundException{
		testDriver.setExecuted(true);
		if(testDriver.getLoggers()==null){
		ExtentTest loggers = null;
		String testClassName = testDriver.getAlmID();
		if(DriverScript.extentReports!=null){
		if(testDriver.getWebMedium()!= null && testDriver.getWebMedium().length()==0){	
			loggers = DriverScript.extentReports.startTest(testClassName );
		}else{
			loggers = DriverScript.extentReports.startTest(testClassName+"_"+testDriver.getWebMedium());
		}
		
		}else{
			ExtentReports extentReports = new ExtentReports("report//"+testClassName+".html");
			loggers = extentReports.startTest(testClassName);
			testDriver.setExtentReports(extentReports);
		}
		testDriver.setLoggers(loggers);
		}
		if(testDriver.getWebMedium()!=null){
			Web.webAdaptor(testDriver, WebAction.valueOf(action.name()), args);
		}else if(testDriver.getMobileMedium()!=null){
			Mobile.mobileAdaptor(testDriver, MobileAction.valueOf(action.name()), args);
		}
		
	}

}
