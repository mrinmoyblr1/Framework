/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author manoj
 *
 */
public class DriverScriptException extends Exception {

	private static final long serialVersionUID = 1L;

	public DriverScriptException() {
	}

	public DriverScriptException(String message) {
		super(message);
	}

	public DriverScriptException(Throwable cause) {
		super(cause);
	}

	public DriverScriptException(String message, Throwable cause) {
		super(message, cause);
	}

	public DriverScriptException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
