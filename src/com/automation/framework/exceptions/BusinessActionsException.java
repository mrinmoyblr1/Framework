/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author manoj
 *
 */
public class BusinessActionsException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessActionsException() {
	}

	public BusinessActionsException(String message) {
		super(message);
	}

	public BusinessActionsException(Throwable cause) {
		super(cause);
	}

	public BusinessActionsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessActionsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
