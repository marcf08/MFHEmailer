package marcus.email.GUI;

import java.util.Arrays;

import marcus.email.exceptions.EmailContentException;
import marcus.email.exceptions.EmailException;
import marcus.email.exceptions.PasswordException;
import marcus.email.exceptions.PasswordLengthException;

/**
 * The GUI verify method checks the password and the email to ensure
 * they comply with what we request. 
 * @author Marcus
 *
 */

public class VerifyGUI {
	
	//Constant for the minimum password size
	public static final int PW_MIN = 5;
	
	//Constant for email minimum size
	public static final int EM_MIN = 0;
	
	//Email validation constants
	//All emails should contain both of these
	public static final String PERIOD = ".";
	public static final String AT_SYMBOL = "@";
	
	/**
	 * The constructor is null.
	 */
	public VerifyGUI() {
		//Null
	}
	
	/**
	 * The verify password method verifies that the password fields match.
	 * @throws PasswordException if the passwords do not match 
	 */
	public void passwordMatch(char [] passwordInitial, char [] passwordConfirm) throws PasswordException {
		if (!Arrays.equals(passwordInitial,passwordConfirm)) {
			throw new PasswordException();
		}
	}
	
	/**
	 * The verify email method verifies that the email fields match.
	 * @throws EmailException if the emails do not match
	 */
	public void emailMatch(String emailInitial, String emailConfirm) throws EmailException {
		if (!emailInitial.equals(emailConfirm)) {
			throw new EmailException();
		}
		
	}
	
	/**
	 * This method ensures the password is of the proper length.
	 * @throws PasswordLengthException if the password is too short
	 */
	public void isProperLength(char[] pw) throws PasswordLengthException {
		if ((pw.length <= PW_MIN)) {
			throw new PasswordLengthException();
		}
	}
	
	/**
	 * This method runs a few checks on the email to do the best we can
	 * to get a valid email address. This check is not very comprehensive,
	 * but it should prevent the situation in which the user accidentally enters
	 * a blank address, or one with no period or at symbol.
	 * @param email an email address to verify
	 * @throws EmailContentException if the email seems invalid
	 */
	public void isValidEmail(String email) throws EmailContentException {
		if (email.length() <= EM_MIN) {
			throw new EmailContentException();
		}
		if (!email.contains(PERIOD)) {
			throw new EmailContentException();
		}
		if (!email.contains(AT_SYMBOL)) {
			throw new EmailContentException();
		}
	}
}
