package com.automation.framework.actionInterpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.KeywordTraceException;
import com.automation.framework.exceptions.WebServiceAdaptorException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.webService.RESTKeywords;
import com.automation.framework.webService.SOAPKeywords;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

// TODO: Auto-generated Javadoc
/**
 * The Class D2WebElement.
 */

/**
 * @author Jagadish
 *
 */
public class WebServices {

	private static volatile Map<String, Map<String, Object>> methods = new HashMap<String, Map<String, Object>>(
			50);
	private static Map<String, Object> objMethods;
	private static String packageNameForBaseKeywords = "com.automation.framework.webService";

	private static final Logger LOG = Logger.getLogger(WebServices.class);

	public WebServices() {
		objMethods = new HashMap<String, Object>();
		objMethods.put("restkeywords", new RESTKeywords());
		objMethods.put("soapkeywords", new SOAPKeywords());
//		objMethods.put("businessComponents", new BusinessActionFactory());
	}

	public static enum WebServiceAction {
		getResponseForAPostRequest,getResponseForAPostRequestWithHeaderParam,callAndSaveMacroService,modifyRequest,getResponse
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
	 * @throws WebServiceAdaptorException 
	 * @throws DriverScriptException
	 */
	public static void webServiceAdaptor(TestDriver testDriver,WebServiceAction elementEnum,Hashtable<String,String> testData)
			throws WebServiceAdaptorException {
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "webServiceElementActionInvoke(Actions, String, String..)- Start"); //$NON-NLS-1$
		}
		
			webServiceElementInvoke(testDriver,elementEnum, testData);
			if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "webServiceElementActionInvoke(Actions, String, String..)- End"); //$NON-NLS-1$
		}
	}

	/*public static void webServiceAdaptor(TestDriver testDriver,Action elementEnum,Hashtable<String,String> testData,String... args)
			throws WebServiceAdaptorException {
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "webServiceElementActionInvoke(Actions, String, String..)- Start"); //$NON-NLS-1$
		}
			webServiceElementInvoke(testDriver,elementEnum, testData);
		
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "webServiceElementActionInvoke(Actions, String, String..)- End"); //$NON-NLS-1$
		}
	}
*/
	/**
	 * 
	 * webadaptor invoke to execute the keywords
	 * @param elementEnum
	 * @param object
	 * @param text
	 * @param string
	 * @throws WebServiceAdaptorException 
	 */
	private static void webServiceElementInvoke(TestDriver testDriver,WebServiceAction elementEnum, Hashtable<String,String> testData) throws WebServiceAdaptorException {

		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "webServiceElementInvoke(Actions, String, String, String)- start"); //$NON-NLS-1$
		}
		testDriver.setScriptInit(true);
		
		if(testDriver.getLoggers()==null){
		ExtentTest loggers = null;
		String testClassName = testDriver.getAlmID();
		
			
		
		if(DriverScript.extentReports!=null){
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
			
		}else{
			ExtentReports extentReports = new ExtentReports("report//"+testClassName+".html");
			loggers = extentReports.startTest(testClassName);
			testDriver.setExtentReports(extentReports);
		}
		testDriver.setLoggers(loggers);
		}
		try {
//			Map<String, Object> argMap = new HashMap<String, Object>();
//			HashMap<String,String> argMap = new HashMap<String, String>();
			WebServices d2WebElement = new WebServices();
			Method actionMethd = null;
			String methodName = elementEnum.name();
			synchronized (methods) {
				actionMethd = traceKeyword(testDriver,testData,
						d2WebElement, actionMethd, methodName,
						packageNameForBaseKeywords);
				DriverScript.logMessage(testDriver,"info",
						"calling traceKeyword method mobileElementInvoke");
			}
			if (actionMethd != null) {
				DriverScript
						.logMessage(testDriver,"info",
								"verifying and invoking actionMethod for webServiceElementInvoke ");
				try {
					actionMethd.invoke(methods.get(methodName)
							.get("objectPath"),testDriver, (Object)testData);
				} catch (IllegalAccessException | IllegalArgumentException e) {
					DriverScript
							.logMessage(testDriver,
									"error",
									"webServiceElementInvoke(Actions, String, String, String)- passing illigal argument or illigal action");
					throw new WebServiceAdaptorException(
							"Error while executing keywords" + e);
				} catch (InvocationTargetException e) {
					try {
						throw e.getTargetException();
					} catch (Throwable e1) {
						throw new WebServiceAdaptorException(e1.getCause());
					}
				}
			}
		} catch (KeywordTraceException e) {
			throw new WebServiceAdaptorException(e);
		}
		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "webServiceElementInvoke(Actions, String, String, String)- end"); //$NON-NLS-1$
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
	public static Method traceKeyword(TestDriver testDriver,Hashtable<String, String> testData, WebServices d2WebElement,
			Method actionMethd, String methodName, String packageName)
			throws KeywordTraceException {

		if (LOG.isDebugEnabled()) {
			DriverScript
					.logMessage(testDriver,
							"info", "traceKeyword(String, String ,argMap,  d2WebElement ,actionMethd ,methodName,packageName) - Start"); //$NON-NLS-1$
		}
		boolean foundMethod = false;
		if (methods.get(methodName) == null) {
			DriverScript.logMessage(testDriver,"info", "verifying invoking the method");
			for (Map.Entry<String, Object> entry : objMethods.entrySet()) {
				try {
					actionMethd = entry.getValue().getClass()
							.getMethod(methodName,TestDriver.class, Hashtable.class);
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