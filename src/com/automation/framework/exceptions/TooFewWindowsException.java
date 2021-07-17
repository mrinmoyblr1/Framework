package com.automation.framework.exceptions;

public class TooFewWindowsException extends Exception {
	private static final long serialVersionUID = 1L;
    public TooFewWindowsException() {
        super();
    }

    public TooFewWindowsException(String s) {
        super(s);
    }
}
