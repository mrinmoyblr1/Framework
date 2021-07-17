package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.pojs.TestDriver;


 /**
  * 
  * @author Automation COE
  *
  */
public interface JavaScript {
	  public void scrollAndClick(TestDriver testDriver,HashMap<String, Object> params) throws Exception;
	  
		public void scrollToBottom(TestDriver testDriver,HashMap<String, Object> params) throws Exception;
		
		public void handleAlert(TestDriver testDriver,HashMap<String, Object> params) throws Exception;
		
}
