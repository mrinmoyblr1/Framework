package com.automation.framework.actionInterpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.lang.*;

import org.apache.log4j.Logger;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.KeywordTraceException;
import com.automation.framework.exceptions.MobileAdaptorException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.mobileAdaptor.ElementKeywords;
import com.automation.framework.mobileAdaptor.Formkeywords;
import com.automation.framework.mobileAdaptor.JavascriptKeywords;
import com.automation.framework.mobileAdaptor.WaitKeywords;
import com.automation.framework.pojs.TestDriver;
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
public class Mobile {

	private static volatile Map<String, Map<String, Object>> methods = new HashMap<String, Map<String, Object>>(
			50);
	private static Map<String, Object> objMethods;
	private static String packageNameForBaseKeywords = "com.automation.framework.mobileAdaptor";

	private static final Logger LOG = Logger.getLogger(Mobile.class);
	
	public static enum MobileAction {
		wait,clickUsingJavascript,doubleTapToLocation,tapToLocationBasedOnElement,setValueToXpathAndClick,setXpathAndwaitForObjectToLoad,clearText,setText,click,iselementExists,verifyChecked,selectValueFromDropdown
		,handleAlert,scrollToBottom,scrollAndClick,waitForObjectToLoad,waitUntilElementVisible,waitUntilElementClickable,longPress,elementCount,IsElementExist
	}

	public Mobile() {
		objMethods = new HashMap<String, Object>();
		objMethods.put("elmentKeywords", new ElementKeywords());
		objMethods.put("formkeywords", new Formkeywords());
		objMethods.put("javascriptKeywords", new JavascriptKeywords());
		objMethods.put("waitKeywords", new WaitKeywords());
//		objMethods.put("businessComponents", new BusinessActionFactory());
	}

	/**
	 * The Enum webElementEnum.
	 */


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
	public static void mobileAdaptor(TestDriver testDriver,MobileAction elementEnum, String... args)
			throws MobileAdaptorException, ObjectNameNotFoundException {
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "mobileElementActionInvoke(Actions, String, String..)- Start"); //$NON-NLS-1$
		}
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String className = DriverScript.getClassname(element.getClassName());
		testDriver.setCurrentPage(className);
		
		switch (args.length) {
		case 4:
			mobileElementInvoke(testDriver,elementEnum, args[0], args[1], args[2], args[3]);
			break;
		case 3:
			mobileElementInvoke(testDriver,elementEnum, args[0], args[1], args[2], null);
			break;
		case 2:
			mobileElementInvoke(testDriver,elementEnum, args[0], args[1], null, null);
			break;
		case 1:
			mobileElementInvoke(testDriver,elementEnum, args[0], null, null, null);
			break;
		default:
			DriverScript
					.logMessage(testDriver,
							"info",
							"calling mobileElementInvoke(elementEnum, object, null, null) with no arguments by default");
			mobileElementInvoke(testDriver,elementEnum, null, null, null, null);
			break;
		}
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "mobileElementActionInvoke(Actions, String, String..)- End"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * webadaptor invoke to execute the keywords
	 * @param elementEnum
	 * @param object
	 * @param text
	 * @param string
	 * @throws MobileAdaptorException
	 * @throws ObjectNameNotFoundException 
	 */
	private static void mobileElementInvoke(TestDriver testDriver,MobileAction elementEnum, String arg0,
			String arg1, String arg2, String args) throws MobileAdaptorException, ObjectNameNotFoundException {
		if(testDriver.getLoggers()==null){
		ExtentTest loggers = null;
		String testClassName = testDriver.getAlmID();
		testDriver.setScriptInit(true);
		if(DriverScript.extentReports!=null){
		if(testDriver.getMobileMedium()!= null && testDriver.getMobileMedium().length()==0){	
			testDriver.setTestName(testClassName);
		}else{
			testDriver.setTestName(testClassName+"_"+testDriver.getMobileMedium());
			if(DriverScript.platform.toLowerCase().contains("web")){
				testDriver.setTestName(testClassName+"_"+testDriver.getMobileMedium()+"_"+testDriver.getWebMedium());
			}
		}
		loggers = DriverScript.extentReports.startTest(testDriver.getTestName());
		}else{
			ExtentReports extentReports = new ExtentReports("report//"+testClassName+".html");
			loggers = extentReports.startTest(testClassName);
			testDriver.setExtentReports(extentReports);
		}
		testDriver.setLoggers(loggers);
		}
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "mobileElementInvoke(Actions, String, String, String)- start"); //$NON-NLS-1$
		}
		try {
			Map<String, Object> argMap = new HashMap<String, Object>();
			Mobile d2WebElement = new Mobile();
			Method actionMethd = null;
			String methodName = elementEnum.name();
			synchronized (methods) {
				actionMethd = traceKeyword(testDriver,arg0, arg1, arg2, args, argMap,
						d2WebElement, actionMethd, methodName,
						packageNameForBaseKeywords);
				DriverScript.logMessage(testDriver,"info",
						"calling traceKeyword method mobileElementInvoke");
			}
			if (actionMethd != null) {
				DriverScript
						.logMessage(testDriver,"info",
								"verifying and invoking actionMethod for mobileElementInvoke ");
				try {
					actionMethd.invoke(methods.get(methodName)
							.get("objectPath"),testDriver, argMap);
				} catch (IllegalAccessException | IllegalArgumentException e) {
					DriverScript
							.logMessage(testDriver,
									"error",
									"mobileElementInvoke(Actions, String, String, String)- passing illigal argument or illigal action");
					throw new MobileAdaptorException(
							"Error while executing keywords" + e);
				} catch (InvocationTargetException e) {
					testDriver.setMobileFailure(true);
					try {
						throw e.getCause();
					} catch (Throwable e1) {
						if(e1.toString().contains("ObjectNameNotFoundException")){
							throw new ObjectNameNotFoundException(new Throwable(e1.getCause()));
						}
						throw new MobileAdaptorException(e1.getCause());
					}
				}
			}
		} catch (KeywordTraceException e) {
			throw new MobileAdaptorException(e);
		}
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "mobileElementInvoke(Actions, String, String, String)- end"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * trace the class of the keyword by providing keyword name 
	 * @param object
	 * @param text
	 * @param argMap
	 * @param d2WebElement
	 * @param actionMethd
	 * @param methodName
	 * @return
	 * @throws DriverScriptException
	 */
	public static Method traceKeyword(TestDriver testDriver,String arg0, String arg1, String arg2,
			String arg3, Map<String, Object> argMap, Mobile d2WebElement,
			Method actionMethd, String methodName, String packageName)
			throws KeywordTraceException {

		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "traceKeyword(String, String ,argMap,  d2WebElement ,actionMethd ,methodName,packageName) - Start"); //$NON-NLS-1$
		}
		argExtract(testDriver,arg0, "arg0", argMap);
		argExtract(testDriver,arg1, "arg1", argMap);
		argExtract(testDriver,arg2, "arg2", argMap);
		argExtract(testDriver,arg3, "arg3", argMap);

		boolean foundMethod = false;
		if (methods.get(methodName) == null) {
			DriverScript.logMessage(testDriver,"info", "verifying invoking the method");
			for (Map.Entry<String, Object> entry : objMethods.entrySet()) {
				try {
					actionMethd = entry.getValue().getClass()
							.getMethod(methodName,TestDriver.class, HashMap.class);
					Map<String, Object> miniMap = new HashMap<String, Object>();
					miniMap.put("methodPath", actionMethd);
					miniMap.put("objectPath", entry.getValue());
					methods.put(methodName, miniMap);
					foundMethod = true;
					break;
				} catch (NoSuchMethodException | SecurityException e) {
					DriverScript.logMessage(testDriver,"error", methodName
							+ "does not exists in  "
							+ entry.getValue().getClass().getName()
							+ " checking in next class....");
				}
				
			}
			if (!foundMethod) {
				DriverScript.logMessage(testDriver,"error",
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
					.logMessage(testDriver,
							"info",
							"traceKeyword(String, String ,argMap,  d2WebElement ,actionMethd ,methodName,packageName) - End");
		}
		return actionMethd;
	}

	/**
	 * Arg extract- extract the value of the value which are stored in the map during script execution
	 *
	 * @param arg the arg
	 * @param key the key
	 * @param argMap the arg map
	 * @throws KeywordTraceException the keyword trace exception
	 */
	private static void argExtract(TestDriver testDriver,String arg, String key,
			Map<String, Object> argMap) throws KeywordTraceException {
		if (arg != null) {
			if (arg.startsWith("###")) {
				arg = getValueFromMap(testDriver,arg);
			}
			DriverScript.logMessage(testDriver,"info", "putting text into argMap");
			argMap.put(key, arg);
		}
	}

	/**
	 * Gets the value from map.
	 *
	 * @param args the args
	 * @return the value from map
	 * @throws KeywordTraceException the keyword trace exception
	 */
	private static String getValueFromMap(TestDriver testDriver,String args)
			throws KeywordTraceException {
		String strValue = args.substring(3, args.length());
		if (testDriver.getMapValues().get(strValue) == null) {
			throw new KeywordTraceException(new Throwable(strValue
					+ " is not set by any keywords"));
		}
		return testDriver.getMapValues().get(strValue);

	}

}