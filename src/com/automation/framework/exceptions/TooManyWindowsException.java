package com.automation.framework.exceptions;

public class TooManyWindowsException extends Exception {
	private static final long serialVersionUID = 1L;
    public TooManyWindowsException() {
        super();
    }

    public TooManyWindowsException(String s) {
        super(s);
    }
}
