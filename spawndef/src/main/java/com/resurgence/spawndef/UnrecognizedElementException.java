package com.resurgence.spawndef;

public class UnrecognizedElementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7408167927560218287L;

	public UnrecognizedElementException() {
		super();
	}

	public UnrecognizedElementException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnrecognizedElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnrecognizedElementException(String message) {
		super(message);
	}

	public UnrecognizedElementException(Throwable cause) {
		super(cause);
	}

}
