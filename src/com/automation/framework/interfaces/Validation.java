package com.automation.framework.interfaces;


import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;

public interface Validation {

	public void verifyPageTitle(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
	
	public void verifyElementText(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void verifyPageURL(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;
}
