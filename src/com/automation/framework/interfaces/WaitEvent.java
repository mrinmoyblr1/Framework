package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;

public interface WaitEvent {

	public void implicitWait(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void waitUntilElementVisible(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void waitUntilElementClickable(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void wait(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
}
