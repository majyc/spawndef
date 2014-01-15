package com.resurgence.spawndef;

public class EmptyElementException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8431764300944957936L;

	public EmptyElementException() {
		super();
	}

	public EmptyElementException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyElementException(String message) {
		super(message);
	}

	public EmptyElementException(Throwable cause) {
		super(cause);
	}

}
