/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author manoj
 *
 */
public class KeywordTraceException extends Exception {

	private static final long serialVersionUID = 1L;

	public KeywordTraceException() {
	}

	public KeywordTraceException(String message) {
		super(message);
	}

	public KeywordTraceException(Throwable cause) {
		super(cause);
	}

	public KeywordTraceException(String message, Throwable cause) {
		super(message, cause);
	}

	public KeywordTraceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
