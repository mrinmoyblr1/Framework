package com.automation.framework.exceptions;

public class UnableToLocateValueException extends Exception {

	private static final long serialVersionUID = 1L;
	  public UnableToLocateValueException() {
		  
	        super("unable to find Key value pair");
	    }

	  public UnableToLocateValueException(String s) {
	        super("unable to find Key-value pair for key:"+s);
	    }
}
