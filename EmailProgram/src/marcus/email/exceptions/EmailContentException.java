package marcus.email.exceptions;
/**
 * The password length exception is thrown if the password is not of the proper size.
 *
 * @author Marcus
 *
 */

public class EmailContentException extends Exception {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	//The string is for the message thrown by the program.
	private static final String EM_ERR = "Email content not valid.";
	
	/**
	 * The constructor inherits from the parent class.
	 */
	public EmailContentException() {
		super(EM_ERR);
	}
	
	/**
	 * This constructor throws via the error message.
	 */
	public EmailContentException(String message) {
		super(message);
	}
	
	/**
	 * This constructor makes the error message throwable.
	 */
	public EmailContentException(Throwable cause) {
		super(cause);
	}
}