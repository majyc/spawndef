package resurgence.spawndef;

public class InvalidFormatException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8431764300944957936L;

	public InvalidFormatException() {
		super();
	}

	public InvalidFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFormatException(String message) {
		super(message);
	}

	public InvalidFormatException(Throwable cause) {
		super(cause);
	}

}
