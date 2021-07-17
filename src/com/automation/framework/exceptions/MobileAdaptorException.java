/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author manoj
 *
 */
public class MobileAdaptorException extends Exception {

	private static final long serialVersionUID = 1L;

	public MobileAdaptorException() {
	}

	public MobileAdaptorException(String message) {
		super(message);
	}

	public MobileAdaptorException(Throwable cause) {
		super(cause);
	}

	public MobileAdaptorException(String message, Throwable cause) {
		super(message, cause);
	}

	public MobileAdaptorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
