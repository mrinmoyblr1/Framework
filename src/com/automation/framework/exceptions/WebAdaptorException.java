/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author manoj
 *
 */
public class WebAdaptorException extends Exception {

	private static final long serialVersionUID = 1L;

	public WebAdaptorException() {
	}

	public WebAdaptorException(String message) {
		super(message);
	}

	public WebAdaptorException(Throwable cause) {
		super(cause);
	}

	public WebAdaptorException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebAdaptorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
