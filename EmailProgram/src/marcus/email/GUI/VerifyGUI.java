package marcus.email.GUI;

import java.util.Arrays;

import marcus.email.exceptions.EmailException;
import marcus.email.exceptions.PasswordException;

/**
 * The GUI verify method checks the password and the email to ensure
 * they comply with what we request. 
 * @author Marcus
 *
 */

public class VerifyGUI {
	
	//Constant for the minimum password size
	public static final int PW_MIN = 6;
	
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
	 * @return true if the passwords match and false otherwise
	 */
	public boolean isProperLength(String password) {
		return (password.length() >= PW_MIN);
	}
}
