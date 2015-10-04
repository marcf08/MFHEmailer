package marcus.email.exceptions;
/**
 * The exceptions class ensures the passwords match. If they do not,
 * the program throws this exception.
 *
 * @author Marcus
 *
 */

public class PasswordException extends Exception {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	//The string is for the message thrown by the program.
	private static final String PW_ERR = "Passwords do not match.";
	
	/**
	 * The constructor inherits from the parent class.
	 */
	public PasswordException() {
		super(PW_ERR);
	}
	
	/**
	 * This constructor throws via the error message.
	 */
	public PasswordException(String message) {
		super(message);
	}
	
	/**
	 * This constructor makes the error message throwable.
	 */
	public PasswordException(Throwable cause) {
		super(cause);
	}
}
