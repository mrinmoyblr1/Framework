package com.automation.framework.actionInterpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.KeywordTraceException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.webAdaptor.ElementKeywords;
import com.automation.framework.webAdaptor.Formkeywords;
import com.automation.framework.webAdaptor.JavascriptKeywords;
import com.automation.framework.webAdaptor.ValidationKeywords;
import com.automation.framework.webAdaptor.WaitKeywords;
import com.automation.framework.webAdaptor.WindowsKeywords;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

// TODO: Auto-generated Javadoc
/**
 * The Class D2WebElement.
 */

/**
 * @author manoj
 *
 */
public class Web {

	private static volatile Map<String, Map<String, Object>> methods = new HashMap<String, Map<String, Object>>(
			50);
	private static Map<String, Object> objMethods;
	private static String packageNameForBaseKeywords = "com.automation.framework.webAdaptor";

	private static final Logger LOG = Logger.getLogger(Web.class);

	public Web() {
		objMethods = new HashMap<String, Object>();
		objMethods.put("elmentKeywords", new ElementKeywords());
		objMethods.put("formkeywords", new Formkeywords());
		objMethods.put("javascriptKeywords", new JavascriptKeywords());
		objMethods.put("validationKeywords", new ValidationKeywords());
		objMethods.put("waitKeywords", new WaitKeywords());
		objMethods.put("windowsKeywords", new WindowsKeywords());
		// objMethods.put("businessComponents", new BusinessActionFactory());
	}

	/**
	 * The Enum webElementEnum.
	 */
	public static enum WebAction {
		clear, elementCount, clickUsingJavascript, setValueToXpathAndGetText, isElementNotExists, sendKeyboardActions, setValueToXpathAndDoubleClick, setXpathAndElementCount, isElementDisplayed, setValueToXpathAndRightClick, isElementNotDisplayed, click, setText, IsElementExist, doubleClick, rightClick, mouseHover, getText, setValueToXpathAndElementDisabled, setValueToXpathAndElementNotDisplayed, setValueToXpathAndIsElementNotExists,
		setValueToXpathAndGetElementAttributeValue, setvalueToXpathAndIsElementExist, getTextUsingJavascript, setValueToXpathAndMouseHover, setValueToXpathAndClick, setXpathAndwaitForObjectToLoad, isElementDisabled, waitForObjectToLoad, getElementAttributeValue, setValueToXpathAndSetText, setTextUsingJavaScript, setValueToXpathAndGetTextUsingJavascript, setValueToXpathAndClickUsingJavscript, setValueToXpathAndSetTextUsingJavascript, 
		getElementsCount, isCheckboxChecked, setOptionalTextToElement, isRadioButtonSelected, selectValueFromDropdown, getDropDownvalues, setMandatoryTextToElement, unSelectCheckBox, selectCheckBox, scrollAndClickBasedOnTagsForLabel, scrollToBottom, scrollAndClick, handleAlert, scrollToTop, scrollDownInTag, scrollAndClickBasedOnTags, setXpathAndScrollAndClick, scrollByKeyDownAndClick,
		setValueToXpathAndVerifyElementTextUsingJavascript,verifyBrokenLink,verifyPageTitle,verifyPageURL,verifyTextUsingJavascript,verifyElementContainsText,verifyElementTextUsingJavascript,verifyBrokenLinkByLinkName,verifyElementText,setValueToXpathAndverifyElementText,setValueToXpathAndverifyElementContainsText,
		wait,waitUntilElementClickable,waitUntilElementVisible,implicitWait,SetValueToXpathandwaitUntilElementClickable,navigateToPreviousPage,handleDownloadPopup,switchToLastWindow,switchToFrameBasedOnWebElement,switchToFrameBasedOnFrameNo,switchToWindowTitle,navigateToForwardPage,switchBetweenWindows,closeBrowser,maximize,navigateToURL,switchBackFromFrameBasedWebElement,closeAllWindows
	}

	/**
	 * Web element action invoke.
	 *
	 * @param elementEnum
	 *            the element enum
	 * @param object
	 *            the object
	 * @param args
	 *            the args
	 * @throws ObjectNameNotFoundException 
	 * @throws DriverScriptException
	 */
	public static void webAdaptor(TestDriver testDriver, WebAction elementEnum,
			String... args) throws WebAdaptorException, ObjectNameNotFoundException {
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(
							testDriver,
							"info", "webElementActionInvoke(Actions, String, String..)- Start"); //$NON-NLS-1$
		}
		
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String className = DriverScript.getClassname(element.getClassName());
		testDriver.setCurrentPage(className);
		
		switch (args.length) {
		case 4:
			webElementInvoke(testDriver, elementEnum, args[0], args[1],
					args[2], args[3]);
			break;
		case 3:
			webElementInvoke(testDriver, elementEnum, args[0], args[1],
					args[2], null);
			break;
		case 2:
			webElementInvoke(testDriver, elementEnum, args[0], args[1], null,
					null);
			break;
		case 1:
			webElementInvoke(testDriver, elementEnum, args[0], null, null, null);
			break;
		default:
			DriverScript
					.logMessage(
							testDriver,
							"info",
							"calling webElementInvoke(elementEnum, object, null, null) with no arguments by default");
			webElementInvoke(testDriver, elementEnum, null, null, null, null);
			break;
		}
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(
							testDriver,
							"info", "webElementActionInvoke(Actions, String, String..)- End"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * webadaptor invoke to execute the keywords
	 * 
	 * @param elementEnum
	 * @param object
	 * @param text
	 * @param string
	 * @throws WebAdaptorException
	 * @throws ObjectNameNotFoundException 
	 */
	private static void webElementInvoke(TestDriver testDriver,
			WebAction elementEnum, String arg0, String arg1, String arg2,
			String args) throws WebAdaptorException, ObjectNameNotFoundException {

		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(
							testDriver,
							"info", "webElementInvoke(Actions, String, String, String)- start"); //$NON-NLS-1$
		}
		testDriver.setScriptInit(true);
		if (testDriver.getLoggers() == null) {
			ExtentTest loggers = null;
			String testClassName = testDriver.getAlmID();
			if (DriverScript.extentReports != null) {
				if (testDriver.getWebMedium() != null
						&& testDriver.getWebMedium().length() == 0) {
					testDriver.setTestName(testClassName);
				} else{
					testDriver.setTestName(testClassName+"_"+testDriver.getWebMedium());
					if(DriverScript.platform.toLowerCase().contains("mobile")){
						testDriver.setTestName(testClassName+"_"+testDriver.getWebMedium()+"_"+testDriver.getMobileMedium());
					}
				}
				loggers = DriverScript.extentReports.startTest(testDriver.getTestName());

			} else {
				ExtentReports extentReports = new ExtentReports("report//"
						+ testClassName + ".html");
				loggers = extentReports.startTest(testClassName);
				
				
				testDriver.setExtentReports(extentReports);
			}
			testDriver.setLoggers(loggers);
		}
		try {
			Map<String, Object> argMap = new HashMap<String, Object>();
			Web d2WebElement = new Web();
			Method actionMethd = null;
			String methodName = elementEnum.name();
			synchronized (methods) {
				if (testDriver.getWebDriver() != null
						&& !methodName.equals("setText")) {
					((JavascriptExecutor) testDriver.getWebDriver())
							.executeScript("window.focus();");
				}
				actionMethd = traceKeyword(arg0, arg1, arg2, args, argMap,
						d2WebElement, actionMethd, methodName,
						packageNameForBaseKeywords, testDriver);
				DriverScript.logMessage(testDriver, "info",
						"calling traceKeyword method webElementInvoke");
			}
			if (actionMethd != null) {
				DriverScript
						.logMessage(testDriver, "info",
								"verifying and invoking actionMethod for webElementInvoke ");
				try {
					actionMethd.invoke(methods.get(methodName)
							.get("objectPath"), testDriver, argMap);
				} catch (IllegalAccessException | IllegalArgumentException e) {
					DriverScript
							.logMessage(
									testDriver,
									"error",
									"webElementInvoke(Actions, String, String, String)- passing illigal argument or illigal action");
					throw new WebAdaptorException(
							"Error while executing keywords" + e);
				} catch (InvocationTargetException e) {
					testDriver.setWebFailure(true);
					try {
						throw e.getCause();
					} catch (Throwable e1) {
						if(e1.toString().contains("ObjectNameNotFoundException")){
							throw new ObjectNameNotFoundException(new Throwable(e1.getCause()));
						}
						throw new WebAdaptorException(e1.getCause());
					}
				}
			}
		} catch (KeywordTraceException e) {
			throw new WebAdaptorException(e);
		}
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(
							testDriver,
							"info", "webElementInvoke(Actions, String, String, String)- end"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * trace the class of the keyword by providing keyword name
	 * 
	 * @param object
	 * @param text
	 * @param argMap
	 * @param d2WebElement
	 * @param actionMethd
	 * @param methodName
	 * @return
	 * @throws DriverScriptException
	 */
	public static Method traceKeyword(String arg0, String arg1, String arg2,
			String arg3, Map<String, Object> argMap, Web d2WebElement,
			Method actionMethd, String methodName, String packageName,
			TestDriver testDriver) throws KeywordTraceException {

		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(
							testDriver,
							"info", "traceKeyword(String, String ,argMap,  d2WebElement ,actionMethd ,methodName,packageName) - Start"); //$NON-NLS-1$
		}
		argExtract(arg0, "arg0", argMap, testDriver);
		argExtract(arg1, "arg1", argMap, testDriver);
		argExtract(arg2, "arg2", argMap, testDriver);
		argExtract(arg3, "arg3", argMap, testDriver);

		boolean foundMethod = false;
		if (methods.get(methodName) == null) {
			DriverScript.logMessage(testDriver, "info",
					"verifying invoking the method");
			for (Map.Entry<String, Object> entry : objMethods.entrySet()) {
				try {
					actionMethd = entry
							.getValue()
							.getClass()
							.getMethod(methodName, TestDriver.class,
									HashMap.class);
					Map<String, Object> miniMap = new HashMap<String, Object>();
					miniMap.put("methodPath", actionMethd);
					miniMap.put("objectPath", entry.getValue());
					methods.put(methodName, miniMap);
					foundMethod = true;
					break;
				} catch (NoSuchMethodException | SecurityException e) {
					DriverScript.logMessage(testDriver, "error", methodName
							+ "does not exists in  "
							+ entry.getValue().getClass().getName()
							+ " checking in next class....");
				}

			}
			if (!foundMethod) {
				DriverScript.logMessage(testDriver, "error",
						"method not found in the classes ");
				throw new KeywordTraceException(new Throwable(methodName
						+ " keyword not found in the " + packageName
						+ " package classes"));
			}
		} else {
			actionMethd = (Method) methods.get(methodName).get("methodPath");
		}
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(
							testDriver,
							"info",
							"traceKeyword(String, String ,argMap,  d2WebElement ,actionMethd ,methodName,packageName) - End");
		}
		return actionMethd;
	}

	/**
	 * Arg extract- extract the value of the value which are stored in the map
	 * during script execution
	 *
	 * @param arg
	 *            the arg
	 * @param key
	 *            the key
	 * @param argMap
	 *            the arg map
	 * @throws KeywordTraceException
	 *             the keyword trace exception
	 */
	private static void argExtract(String arg, String key,
			Map<String, Object> argMap, TestDriver testDriver)
			throws KeywordTraceException {
		if (arg != null) {
			if (arg.startsWith("###")) {
				arg = getValueFromMap(testDriver, arg);
			}
			DriverScript.logMessage(testDriver, "info",
					"putting text into argMap");
			argMap.put(key, arg);
		}
	}

	/**
	 * Gets the value from map.
	 *
	 * @param args
	 *            the args
	 * @return the value from map
	 * @throws KeywordTraceException
	 *             the keyword trace exception
	 */
	private static String getValueFromMap(TestDriver testDriver, String args)
			throws KeywordTraceException {
		String strValue = args.substring(3, args.length());
		String stringValue = testDriver.getMapValues().get(strValue);
		if (stringValue == null) {
			throw new KeywordTraceException(new Throwable(strValue
					+ " is not set by any keywords"));
		}
		return stringValue;

	}

}