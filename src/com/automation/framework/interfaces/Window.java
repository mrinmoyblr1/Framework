package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;

public interface Window {

	public void closeBrowser(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void navigateToURL(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void switchBetweenWindows(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void switchToWindowTitle(TestDriver testDriver,HashMap<String, Object> params)throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void maximize(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void closeAllWindows(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void switchToLastWindow(TestDriver testDriver,HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException;

}