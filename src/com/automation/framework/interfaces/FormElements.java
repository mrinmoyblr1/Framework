package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.pojs.TestDriver;

public interface FormElements {

	public void isRadioButtonSelected(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void isCheckboxChecked(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void selectCheckBox(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void unSelectCheckBox(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void setMandatoryTextToElement(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void setOptionalTextToElement(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;

	public void selectValueFromDropdown(TestDriver testDriver,HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException;
}
