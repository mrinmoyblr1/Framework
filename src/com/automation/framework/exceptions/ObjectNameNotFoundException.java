/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author manoj
 *
 */
public class ObjectNameNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjectNameNotFoundException() {
	}

	public ObjectNameNotFoundException(String message) {
		super(message);
	}

	public ObjectNameNotFoundException(Throwable cause) {
		super(cause);
	}

	public ObjectNameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNameNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
