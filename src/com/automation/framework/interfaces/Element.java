package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;

/**
 * 
 * @author Automation COE
 *
 */
public interface Element {
	//Ritesh modified
	public void click(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void getText(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException ;
	
	public void doubleClick(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void rightClick(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void IsElementExist(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void isElementDisplayed(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void getElementAttributeValue(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void mouseHover(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void clear(TestDriver testDriver,HashMap<String, Object> params)throws BrowserException, ElementFailException, ObjectNameNotFoundException ;
	
	public void setValueToXpathAndClick(TestDriver testDriver,HashMap<String, Object> params)throws BrowserException, ElementFailException, ObjectNameNotFoundException ;
}
