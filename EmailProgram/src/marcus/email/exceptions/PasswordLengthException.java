package marcus.email.exceptions;
/**
 * The password length exception is thrown if the password is not of the proper size.
 *
 * @author Marcus
 *
 */

public class PasswordLengthException extends Exception {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	//The string is for the message thrown by the program.
	private static final String PW_ERR = "Passwords not long enough.";
	
	/**
	 * The constructor inherits from the parent class.
	 */
	public PasswordLengthException() {
		super(PW_ERR);
	}
	
	/**
	 * This constructor throws via the error message.
	 */
	public PasswordLengthException(String message) {
		super(message);
	}
	
	/**
	 * This constructor makes the error message throwable.
	 */
	public PasswordLengthException(Throwable cause) {
		super(cause);
	}
}
