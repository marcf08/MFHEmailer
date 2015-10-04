package marcus.email.exceptions;
/**
 * The exceptions class ensures the passwords match. If they do not,
 * the program throws this exception.
 *
 * @author Marcus
 *
 */

public class EmailException extends Exception {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	//The string is for the message thrown by the program.
	private static final String EMAIL_ERR = "Email fields do not match.";
	
	/**
	 * The constructor inherits from the parent class.
	 */
	public EmailException() {
		super(EMAIL_ERR);
	}
	
	/**
	 * This constructor throws via the error message.
	 */
	public EmailException(String message) {
		super(message);
	}
	
	/**
	 * This constructor makes the error message throwable.
	 */
	public EmailException(Throwable cause) {
		super(cause);
	}
}
