/**
 * 
 */
package com.automation.framework.exceptions;

/**
 * @author jagadish
 *
 */
public class WebServiceAdaptorException extends Exception {

	private static final long serialVersionUID = 1L;

	public WebServiceAdaptorException() {
	}

	public WebServiceAdaptorException(String message) {
		super(message);
	}

	public WebServiceAdaptorException(Throwable cause) {
		super(cause);
	}

	public WebServiceAdaptorException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebServiceAdaptorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
