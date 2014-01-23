package resurgence.spawndef;

public class MissingElementDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143349513815200898L;

	public MissingElementDataException() {
	}

	public MissingElementDataException(String message) {
		super(message);
	}

	public MissingElementDataException(Throwable cause) {
		super(cause);
	}

	public MissingElementDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingElementDataException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
