/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author manoj
 *
 */
public class BrowserException extends Exception {
	private static final long serialVersionUID = 1L;

	public BrowserException() {
	}

	public BrowserException(String message) {
		super(message);
	}

	public BrowserException(Throwable cause) {
		super(cause);
	}

	public BrowserException(String message, Throwable cause) {
		super(message, cause);
	}

	public BrowserException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
