/**
 * 
 */
package com.automation.framework.interfaces;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.framework.exceptions.DriverScriptException;

/**
 * @author manoj
 *
 */
public interface TestcaseInterface {

	@BeforeMethod
	public abstract void setup() throws DriverScriptException;

	@AfterMethod
	public abstract void tearDown() throws DriverScriptException;

}