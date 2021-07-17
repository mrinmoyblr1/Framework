package com.automation.framework.pojs;

import org.openqa.selenium.WebDriver;

public class TestStep {
	
	private String inputParams;
	private WebDriver cLdriver;

	public String getInputParams() {
		return inputParams;
	}

	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}

	public WebDriver getcLdriver() {
		return cLdriver;
	}

	public void setcLdriver(WebDriver cLdriver) {
		this.cLdriver = cLdriver;
	}

}
