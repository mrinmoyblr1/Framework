package com.automation.framework.pojs;

import org.openqa.selenium.WebDriver;

public class TestStepResults {
	private String status;
	private String comment;
	private String session;
	private String errMsg;
	private WebDriver driver;
	private String returnValue;
	public enum ReturnValueTypes{
		INTEGER,STRING,DOUBLE,BOOLEAN
	}
	private String returnValueType;
	
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public String getReturnValueType() {
		return returnValueType;
	}
	public void setReturnValueType(String returnValueType) {
		this.returnValueType = returnValueType;
	}
}
