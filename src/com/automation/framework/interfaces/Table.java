package com.automation.framework.interfaces;

import java.util.HashMap;

import com.automation.framework.pojs.TestDriver;

public interface Table {

	
	public void getNumberOfRowOfTable(TestDriver testDriver,HashMap<String, Object> params) throws Exception;

	
	public void getNumberOfColumnOfTable(TestDriver testDriver,HashMap<String, Object> params) throws Exception;

	
	public void getDataFromParticularCell(TestDriver testDriver,HashMap<String, Object> params) throws Exception;
	

	
	
	
}
