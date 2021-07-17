package com.automation.framework.utilities;

import java.io.BufferedReader;
/**
 * @author manoj
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;

public class ObjectRead {

	public String alMID;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ObjectRead.class);
//	public static Map<String, JSONObject> mapObject = new HashMap<String, JSONObject>();

	/**
	 * @param almID
	 * @throws DriverScriptException 
	 * @throws JSONException
	 */
	public ObjectRead(TestDriver testDriver,String sheetName) throws DriverScriptException {
		testDriver.setExecuted(true);
		getMapFromPageObject(testDriver,sheetName);
	}
	
	/**
	 * Gets the object.
	 *
	 * @param realName the real name
	 * @return the object
	 * @throws ObjectNameNotFoundException the object name not found exception
	 */
	public static By getObject(TestDriver testDriver,String realName,String platform)
			throws ObjectNameNotFoundException {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","openWorkBook(File) - start"); //$NON-NLS-1$
		}
		return getObject(testDriver,realName,null,platform);

	}

	/**
	 * Gets the object.
	 *
	 * @param realName
	 *            the real name
	 * @return the object
	 * @throws ObjectNameNotFoundException
	 */
	public static By getObject(TestDriver testDriver,String realName, String xpathValue,String platform)
			throws ObjectNameNotFoundException {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","openWorkBook(File) - start"); //$NON-NLS-1$
		}
		String locatorType;
		String objectPath;
		JSONObject object = null;
		if(platform.toLowerCase().equals("web")){
		object = (JSONObject) testDriver.getMapWebObject().get(testDriver.getCurrentPage()).get(realName);
		}else if(platform.toLowerCase().equals("mobile")){
			object = (JSONObject) testDriver.getMapMobileObject().get(testDriver.getCurrentPage()).get(realName);
		}
		DriverScript.logMessage(testDriver,"info","object mapping..");
		if (object != null) {
			try {
				locatorType = object.getString("idType");
				objectPath = object.getString("symbolicName");
				/*System.out.println("------------------");
				System.out.println(objectPath);
				System.out.println("------------------");*/
				if (xpathValue != null) {
					if (objectPath.contains("$$$")) {
						 String[] strs = objectPath.split(Pattern.quote("$$$"));
						objectPath = strs[0] + xpathValue + strs[2];
						
					} else {
						DriverScript.logMessage(testDriver,"error","No $$$ symbol to take input value for xpath");
						throw new ObjectNameNotFoundException(
								new Throwable(
										"No $$$ symbol to handle input value for xpath"));
					}
				}
				DriverScript.logMessage(testDriver,"info",realName+"'s object type is "+locatorType+" and object is " + objectPath);
			} catch (JSONException e) {
				DriverScript.logMessage(testDriver,"error","Error in handling Json");
				throw new ObjectNameNotFoundException(new Throwable(realName
						+ " Object values may not be provided "));

			}
			switch (locatorType.toLowerCase()) {
			case "xpath":
				DriverScript.logMessage(testDriver,"info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				return By.xpath(objectPath);
			case "css":
				DriverScript.logMessage(testDriver,"info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				return By.cssSelector(objectPath);
			case "id":
				DriverScript.logMessage(testDriver,"info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				return By.id(objectPath);
			case "name":
				DriverScript.logMessage(testDriver,"info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				return By.name(objectPath);
			case "classname":
				DriverScript.logMessage(testDriver,"info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				return By.className(objectPath);
			case "tagname":
				DriverScript.logMessage(testDriver,"info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				return By.tagName(objectPath);
			case "linktext":
				DriverScript.logMessage(testDriver,"info","object mapping..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				return By.linkText(objectPath);
			default:
				DriverScript.logMessage(testDriver,"error","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					DriverScript.logMessage(testDriver,"info","getObject() - end"); //$NON-NLS-1$
				}
				throw new ObjectNameNotFoundException(new Throwable(locatorType+"Invalid locator type"));

			}
		} else {
			DriverScript.logMessage(testDriver,"testStepDriverFail ",realName
					+ " real name doesnot exists in "+testDriver.getCurrentPage());
			
			throw new ObjectNameNotFoundException(new Throwable(realName
					+ " real name doesnot exists in "+testDriver.getCurrentPage()));
		}

	}

	
	/**
	 * Gets the map object.
	 *
	 * @return the map object
	 * @throws DriverScriptException 
	 */
	///renamed getMapFromPageObject to getMapFromPageObject_xlsrenamed 
	public void getMapFromPageObject_xlsrenamed(TestDriver testDriver,String sheetName) throws DriverScriptException {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getMapFromPageObject() - start"); //$NON-NLS-1$
		}
		DriverScript.logMessage(testDriver,"info","Getting Map object");
		try {
			XLUtil xlUtil = new XLUtil();
			File objectRepoworkbookPath = new File(testDriver.getGlobalParamMap().get("objectRepository")+sheetName+".xlsx");
			
			if(objectRepoworkbookPath.exists()){				
				
			xlUtil.openWorkBook(testDriver,objectRepoworkbookPath);
			if(xlUtil.isSheetExists(testDriver,sheetName)){
			int totalRowCnt = xlUtil
					.getTotalRowCount(testDriver,sheetName)+1;
			
			
			String idType = null;
			String symbolicName = null;
			Map<String, JSONObject> mobileObject = new HashMap<String, JSONObject>();
			Map<String, JSONObject> webObject = new HashMap<String, JSONObject>();
			
			
			for (int row = 1; row < totalRowCnt; row++) {
				String slNo = xlUtil.getCellValue(testDriver,sheetName,
						row, 0);
				if ("EndOfSheet".equals(slNo)) {
					break;
				}
				
				
				String realName = xlUtil.getCellValue(testDriver,
						sheetName, row, 2);
				if(testDriver.getWebMedium()!=null){
					JSONObject jsonObject = new JSONObject();
				idType = xlUtil.getCellValue(testDriver,sheetName,
						row, 3);
				symbolicName = xlUtil.getCellValue(testDriver,
						sheetName, row, 4);
				jsonObject.put("symbolicName", symbolicName);
				jsonObject.put("idType", idType);
				webObject.put(realName, jsonObject);
				}
				if(testDriver.getMobileMedium()!=null){
					JSONObject jsonObject = new JSONObject();
					idType = xlUtil.getCellValue(testDriver,sheetName,
							row, 5);
					symbolicName = xlUtil.getCellValue(testDriver,
							sheetName, row, 6);
					jsonObject.put("symbolicName", symbolicName);
					jsonObject.put("idType", idType);
					mobileObject.put(realName, jsonObject);
				}
			
				
			}
			testDriver.getMapWebObject().put(sheetName, webObject);
			testDriver.getMapMobileObject().put(sheetName, mobileObject);
			}else{
				DriverScript.logMessage(testDriver,"testStepDriverFail", testDriver.getGlobalParamMap().get("objectRepository")+sheetName+" sheetname does not exists in "+testDriver.getGlobalParamMap().get("objectRepository")+sheetName+".xlsx workbook");
				throw new DriverScriptException(new Throwable(testDriver.getGlobalParamMap().get("objectRepository")+sheetName+" sheetname does not exists in "+testDriver.getGlobalParamMap().get("objectRepository")+sheetName+".xlsx workbook"));
			}
			}else{
				DriverScript.logMessage(testDriver,"testStepDriverFail", testDriver.getGlobalParamMap().get("objectRepository")+sheetName+".xlsx"+" workbook does not exists" + "its searching for");
				throw new DriverScriptException(new Throwable(testDriver.getGlobalParamMap().get("objectRepository")+sheetName+".xlsx"+" workbook does not exists"));
			}
		} catch (JSONException e) {
			DriverScript.logMessage(testDriver,"error","getMapFromPageObject()"+e); //$NON-NLS-1$
			e.printStackTrace();
		}
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getMapObject() - end"); //$NON-NLS-1$
		}
	}
	

	public void getMapFromPageObject(TestDriver testDriver,String fileName) throws DriverScriptException {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getMapFromPageObject() - start"); //$NON-NLS-1$
		}
		DriverScript.logMessage(testDriver,"info","Getting Map object");
		try {
			CsvUtil csvUtil = new CsvUtil();
			File objectRepoworkbookPath = new File(testDriver.getGlobalParamMap().get("objectRepository")+fileName+".csv");
			
			if(objectRepoworkbookPath.exists()){				
				
					File csvFilePath = objectRepoworkbookPath;
			        //BufferedReader br = null;
			        String line = "";
			        String cvsSplitBy = ",";


			        	String idType = null;
						String symbolicName = null;
						Map<String, JSONObject> mobileObject = new HashMap<String, JSONObject>();
						Map<String, JSONObject> webObject = new HashMap<String, JSONObject>();
						
						csvUtil.openCsv(testDriver, csvFilePath);
						
			            while ((line = csvUtil.getEachRowOfCSV(testDriver)) != null) {
			                // use comma as separator

			            	if(line.contains("-EOF-")){
			            		break;
			            	}
			                String[] value = line.split(cvsSplitBy);			                

			                String realName = value[2];
							if(testDriver.getWebMedium()!=null){
								JSONObject jsonObject = new JSONObject();
								idType = value[3];
								
								symbolicName = value[4];
								if(csvUtil.checkIfXapthHasComma(testDriver,symbolicName)){
									symbolicName = value[4] + "," + value[5];
										//@manoj review
										// For mobile medium this value may affect need to handle like value[5]=value[6] and value[6]=value[7]
								}
								
								jsonObject.put("symbolicName", symbolicName);
								jsonObject.put("idType", idType);
								webObject.put(realName, jsonObject);
							}
							if(testDriver.getMobileMedium()!=null){
								//@manoj review
								// For mobile medium above web value with comma(,) may affect need to handle like value[5]=value[6] and value[6]=value[7]
								//Also in the xpath of mobile element need to check for commas(,) as above for web
								JSONObject jsonObject = new JSONObject();
								idType = value[5];
								symbolicName = value[6];
								jsonObject.put("symbolicName", symbolicName);
								jsonObject.put("idType", idType);
								mobileObject.put(realName, jsonObject);
							}
			                

			            }

						testDriver.getMapWebObject().put(fileName, webObject);
						testDriver.getMapMobileObject().put(fileName, mobileObject);
						}else{
							DriverScript.logMessage(testDriver,"testStepDriverFail", testDriver.getGlobalParamMap().get("objectRepository")+fileName+".csv"+" File does not exists" + "its searching for");
							throw new DriverScriptException(new Throwable(testDriver.getGlobalParamMap().get("objectRepository")+fileName+".csv"+" File does not exists"));
						}
					} catch (JSONException e) {
						DriverScript.logMessage(testDriver,"error","getMapFromPageObject()"+e); //$NON-NLS-1$
						e.printStackTrace();
					}
					if (LOG.isDebugEnabled()) {
						DriverScript.logMessage(testDriver,"info","getMapObject() - end"); //$NON-NLS-1$
					}
				}
	
	
	/**
	 * Gets the test data array.
	 *
	 * @param testDatafile the test datafile
	 * @return the test data array
	 * @throws DriverScriptException 
	 */
	public static Object[][] getTestDataArrayForWebService(String testDatafile,TestDriver testDriver) throws DriverScriptException{   
		Object[][] testDataArray = null;
		Hashtable<String, Hashtable<String, String>> hashtable = null;
		XLUtil xlUtil = new XLUtil();

		String sheetName = "testData";
		DriverScript.testDataFile = testDatafile;
		   try{
			  String fileName =  searchFile(testDriver,new File(testDriver.getGlobalParamMap().get("testDataPath")),testDatafile+".xlsx");
			  if(fileName!=null){ 	
			  xlUtil.openWorkBook(testDriver,new File(fileName));
			   	if (xlUtil.isSheetExists(testDriver,sheetName)) {
			   		DriverScript.logMessage(testDriver,"info",sheetName+" sheet exists in object Repository workbook");
					   int totalRows = xlUtil.getTotalRowCount(testDriver,sheetName)+1;

					   testDataArray=new Object[1][1];

					   hashtable = new Hashtable<String, Hashtable<String,String>>();
					   for (int row = 1; row <= totalRows; row++) {

							Hashtable<String, String> fieldValues = new Hashtable<String, String>();
							String functionalityName = xlUtil.getCellValue(testDriver,sheetName, row, 0);


							if (functionalityName.toLowerCase().equals("endofsheet")) {
								DriverScript.logMessage(testDriver,"info", "End of business component sheet...");
								break;
							}



							if(functionalityName.length()>0){
							DriverScript.logMessage(testDriver,"info", " Functionality Name "+functionalityName);
							int columnCount = xlUtil.getTotalColumnCount(testDriver,sheetName,row);
							try {
								for (int colNo = 1; colNo < columnCount + 1; colNo++) {
									String cellValue = xlUtil.getCellValue(testDriver,sheetName, row, colNo);
									if (cellValue != null && cellValue.length() != 0) {
										int catchEqual = 0;
										for (int strItr = 0; strItr < cellValue.length(); strItr++) {
											if(cellValue.charAt(strItr)=='='){
												catchEqual=strItr;
											}
										}
										String key = cellValue.substring(0,catchEqual).trim();
										String value =cellValue.substring(catchEqual+1,cellValue.length()).trim();
										DriverScript.logMessage(testDriver,"info", " Map key "+key+" Map value "+value);



										fieldValues.put(key, value);
									}
								}
								hashtable.put(functionalityName, fieldValues);
							} catch (IndexOutOfBoundsException ex) {
								DriverScript.logMessage(testDriver,"testStepDriverFail", " Incorrect values / format is  provided for "


										+ " functionality name " + functionalityName );
								throw new DriverScriptException(new Throwable(" Incorrect values / format is  provided for " 
										+ " functionality name " + functionalityName ));
							}
						}
							
					}
					   testDataArray[0][0] = hashtable;
			   	DriverScript.logMessage(testDriver,"info", "Checking for the existence of the Business Components");
			  }
			   	if(xlUtil.isSheetExists(testDriver,"businesscomponents")){
			   		DriverScript.logMessage(testDriver,"info", "Business Components exists and parsing the Business component sheet.");
			   	getBusinessComponents(xlUtil,testDriver);
			   	DriverScript.logMessage(testDriver,"info", "parsing the Business component sheet ends");
			   	}
			  }
			  
			  else{
				  DriverScript.logMessage(testDriver,"testStepDriverFail",  testDatafile+ " file does not exists");
				  throw new DriverScriptException(new Throwable(testDatafile+ " file does not exists"));
			  }



			   	}catch(Exception exx){
			   		DriverScript.logMessage(testDriver,"testStepDriverFail",  "Error occured while parsing "+testDatafile+".xlsx testdata file");
			   		throw new DriverScriptException(new Throwable("Error occured while parsing "+testDatafile+".xlsx testdata file",exx.getCause()));
			   	}
		   	



		return(testDataArray);
	}
	
/**
	 * Gets the test data array.
	 *
	 * @param testDatafile the test datafile
	 * @return the test data array
	 * @throws DriverScriptException 
	 */
	public static Object[][] getTestDataArray(TestDriver testDriver,String testDatafile) throws DriverScriptException{   
		Object[][] testDataArray = null;
		Hashtable<String, String> hashtable = null;
		XLUtil xlUtil = new XLUtil();
		String execute;
		String sheetName = "testData";
		DriverScript.testDataFile = testDatafile;

			  String fileName =  searchFile(testDriver,new File(testDriver.getGlobalParamMap().get("testDataPath")),testDatafile+".csv");
			  if(fileName!=null){ 	
			  xlUtil.openWorkBook(testDriver,new File(fileName));
			   	if (xlUtil.isSheetExists(testDriver,sheetName)) {
			   		DriverScript.logMessage(testDriver,"info",sheetName+" sheet exists in object Repository workbook");
					   int cj,ci;
					   int startRow = 1;
					   int startCol = 1;
					   boolean isSkipped = false;
					   int totalExecutableRows = xlUtil.getTotalRowCountWithExecuteFlag(testDriver,sheetName);
					   int totalRows = xlUtil.getTotalRowCount(testDriver,sheetName)+1;
					   int totalCols = xlUtil.getTotalColumnsInRow(testDriver,sheetName);
					   testDataArray=new Object[totalExecutableRows][1];
					   ci=0;
					   for(int i=startRow; i < totalRows;i++,ci++){
						   cj=0;
						    execute = xlUtil.getCellValue(testDriver,sheetName,
									i, 0);
							if ("endofsheet".equals(execute.toLowerCase())) {
								break;
							}
						   hashtable = new Hashtable<String, String>();
						   for(int j=startCol; j < totalCols;j++,cj++){
							   if(0 == cj
									   && !xlUtil.getCellValue(testDriver,sheetName, i, j-1).equalsIgnoreCase("Y")) {
								   isSkipped = true;
								   ci=ci-1;
								   break;
							   } 
							   	 hashtable.put(xlUtil.getCellValue(testDriver,sheetName, 0, j), xlUtil.getCellValue(testDriver,sheetName, i, j));
						   }
						   if(isSkipped){
							   DriverScript.logMessage(testDriver,"info","Row "+(i+1)+" had been skipped");
							   isSkipped = !isSkipped;
						   } else{
							   testDataArray[ci][0] = hashtable;
							   DriverScript.logMessage(testDriver,"info",testDataArray[ci][0].toString());
						   }
					   }
					}
			   	DriverScript.logMessage(testDriver,"info", "Checking for the existence of the Business Components");
			   	if(xlUtil.isSheetExists(testDriver,"businesscomponents")){
			   		DriverScript.logMessage(testDriver,"info", "Business Components exists and parsing the Business component sheet.");
			   	getBusinessComponents(xlUtil,testDriver);
			   	DriverScript.logMessage(testDriver,"info", "parsing the Business component sheet ends");
			   	}
			  }else{
				  DriverScript.logMessage(testDriver,"testStepDriverFail",  testDatafile+ " testdata file does not exists");
				  throw new DriverScriptException(new Throwable(testDatafile+ " testdata file does not exists"));
			  }




		   	
		return(testDataArray);
	}
	
	/**
	 * Gets the business action by almid.
	 *
	 * @return the business action by almid
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public static void getBusinessComponents(XLUtil xlUtil,TestDriver testDriver) throws DriverScriptException {

		Map<String, Hashtable<String, String>> businessActionsMap = new HashMap<String, Hashtable<String, String>>();
		DriverScript.logMessage(testDriver,"info", "Opening Business Component workbook...");
		String businessComponentSheetName = "businesscomponents";
		if (xlUtil.isSheetExists(testDriver,businessComponentSheetName)) {
			int totalRowCntLocalParameter = xlUtil.getTotalRowCount(testDriver,businessComponentSheetName);
			for (int row = 1; row <= totalRowCntLocalParameter; row++) {
				Hashtable<String, String> fieldValues = new Hashtable<String, String>();
				String functionalityName = xlUtil.getCellValue(testDriver,businessComponentSheetName, row, 1);
				String businessAction = xlUtil.getCellValue(testDriver,businessComponentSheetName, row, 0);
				if (businessAction.toLowerCase().equals("endofsheet")) {
					DriverScript.logMessage(testDriver,"info", "End of business component sheet...");
					break;
				}
				if(businessAction.length()>0){
				DriverScript.logMessage(testDriver,"info", " Business Component Name"+businessAction+" Functionality Name "+functionalityName);
				int columnCount = xlUtil.getTotalColumnCount(testDriver,businessComponentSheetName,row);
				try {
					for (int colNo = 2; colNo < columnCount + 2; colNo++) {
						String cellValue = xlUtil.getCellValue(testDriver,businessComponentSheetName, row, colNo);
						if (cellValue != null && cellValue.length() != 0) {
							int catchEqual = 0;
							for (int strItr = 0; strItr < cellValue.length(); strItr++) {
								if(cellValue.charAt(strItr)=='='){
									catchEqual=strItr;
								}
							}
							String key = cellValue.substring(0,catchEqual);
							String value =cellValue.substring(catchEqual+1,cellValue.length());
							DriverScript.logMessage(testDriver,"info", " Map key "+key+" Map value "+value);
							fieldValues.put(key, value);
						}
					}
				} catch (IndexOutOfBoundsException ex) {
					DriverScript.logMessage(testDriver,"testStepDriverFail", " Incorrect values / format is  provided for "
							+ " functionality name " + functionalityName + " Business Component name" + businessAction);
					throw new DriverScriptException(new Throwable(" Incorrect values / format is  provided for " 
							+ " functionality name " + functionalityName + " Business Component name" + businessAction));
				}
				businessActionsMap.put(businessAction + functionalityName, fieldValues);
			}
			}
			testDriver.setBusinessActionsMap(businessActionsMap);
		} else {
			DriverScript.logMessage(testDriver,"testStepDriverFail", businessComponentSheetName + " sheet does not exists");
			throw new DriverScriptException(new Throwable(businessComponentSheetName + " sheet does not exists"));
		}
			DriverScript.logMessage(testDriver,"info", "getBusinessActionByALMID method: end");
	}

	
	 public static String searchFile(TestDriver testDriver,File folder,String fileNameToSearch) throws DriverScriptException {

			if (folder.isDirectory()) {
			 File absoluteFile = folder.getAbsoluteFile();
			 DriverScript.logMessage(testDriver,"info","Searching directory ... " + absoluteFile);
				String file  = null;
				File checkFile = new File(absoluteFile.getPath(),fileNameToSearch);
				if(checkFile.exists()){
					return checkFile.getAbsolutePath().toString();	
				}
				else{
				for (int i = 0; i < folder.listFiles().length; i++) {
					File recurssiveFolder = folder.listFiles()[i];
					
					if (recurssiveFolder.isDirectory()) {
						 DriverScript.logMessage(testDriver,"info","Searching directory ... " + recurssiveFolder);
					File temp = new File(recurssiveFolder.getAbsoluteFile().getPath(),fileNameToSearch);
					 DriverScript.logMessage(testDriver,"info",temp + " file");
					if(temp.exists()){
						DriverScript.logMessage(testDriver,"info","File exists");
						  file = temp.getAbsolutePath().toString();
						  return file;
					  }
					}
			    }
				}
				return file;
		      }else{
		    	  DriverScript.logMessage(testDriver,"testStepDriverFail",folder + " folder does not exists");
				throw new DriverScriptException(new Throwable(folder+" folder does not exists"));
		      }
		  }

}