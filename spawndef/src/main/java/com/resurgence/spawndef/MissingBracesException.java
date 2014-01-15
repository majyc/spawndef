package com.resurgence.spawndef;

public class MissingBracesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8242415918920336072L;

	public MissingBracesException(String message) {
		super(message);
	}

	public MissingBracesException() {
		super();
	}

	public MissingBracesException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MissingBracesException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MissingBracesException(Throwable arg0) {
		super(arg0);
	}
}
