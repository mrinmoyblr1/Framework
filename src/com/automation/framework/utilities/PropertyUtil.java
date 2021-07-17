package com.automation.framework.utilities;

/**
 * @author manoj
 *
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.pojs.TestDriver;

/**
 * This class is used to instantiate the data source using the properties file.
 * Pooled connection creation, closing implementation is provided with this
 * class.
 */
public class PropertyUtil {

	/** The Constant LOG. */

	/** properties. */
	private Properties properties;

	/**
	 * Instantiates a property file.
	 */
	public PropertyUtil() {
		properties = new Properties();
	}

	/**
	 * Instantiates a property file.
	 * 
	 * @param file
	 * @throws WebAdaptorException
	 * 
	 */
	public PropertyUtil(TestDriver testDriver,File file) {
		properties = new Properties();
		initPropertyFile(testDriver,file);
	}

	/**
	 * @param file
	 */
	private void initPropertyFile(TestDriver testDriver,File file) {
			DriverScript.logMessage(testDriver,"info","initPropertyFile(File) - start"); //$NON-NLS-1$
		try {

			initialize(testDriver,file);
		} catch (DriverScriptException driverScriptException) {
			DriverScript.logMessage(testDriver,"error",driverScriptException.getMessage());
			driverScriptException.printStackTrace();

		}
			DriverScript.logMessage(testDriver,"info","initPropertyFile(String) - end"); //$NON-NLS-1$
		}

	/**
	 * Gets the property.
	 * 
	 * @param propertyName
	 * 
	 * @return the property
	 */
	public String getProperty(String propertyName) {
		String returnString = properties.getProperty(propertyName);
		return returnString;
	}

	/**
	 * Sets properties file
	 * 
	 * @param properties
	 * 
	 */
	public void setMainproperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * This method handles the loading property file int
	 * 
	 * @param filePath
	 * @throws WebAdaptorException
	 * 
	 */
	public final void initialize(TestDriver testDriver,File filePath) throws DriverScriptException {
			DriverScript.logMessage(testDriver,"info","initialize(File) - start"); //$NON-NLS-1$

		if (filePath.exists()) {
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(
						filePath));
				Properties props = new Properties();
				props.load(inputStream);
				properties.clear();// clear property
				properties.putAll(props);
			} catch (Exception e) {
				DriverScript.logMessage(testDriver,"error","PropertyUtil Init " + e);
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (Exception e2) {
						DriverScript.logMessage(testDriver,"error","PropertyUtil Init close is :" + e2);
					}
				}
			}
		} else {
			throw new DriverScriptException(
					"unable to find globalconfig.properties property file");
		}

			DriverScript.logMessage(testDriver,"info","initialize(File) - end"); //$NON-NLS-1$
		}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}
	
	
	public final HashMap<String, String> readPropertyFile(TestDriver testDriver, File filePath) {
		 DriverScript.logMessage(testDriver,"info","In readPropertyFile method");
		 //Properties properties = new Properties();
		 HashMap<String, String> propvals = new HashMap<String, String>();
		 try {
			 DriverScript.logMessage(testDriver,"info","Property File Loaded Succesfully");
			 Set<String> propertyNames = properties.stringPropertyNames();
			 for (String Property : propertyNames) {
				 DriverScript.logMessage(testDriver,"info",Property + ":" + properties.getProperty(Property));
				 propvals.put(Property, properties.getProperty(Property));
			 }
			 DriverScript.logMessage(testDriver,"info","HashMap generated::" + propvals);
		 
		 } catch (Exception e) {
			 DriverScript.logMessage(testDriver,"error","PropertyUtil parsing error :" + e);
	
		 }
		 return propvals;
	}
	

}
