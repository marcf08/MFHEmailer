package marcus.email.admin;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * The admin class is the only class for the emailer GUI. There will only be
 * one admin user to configure settings and send the emails.
 * @author Marcus
 *
 */
public class AdminAccount {
	/**
	 * The username is stored as a String. 
	 */
	private static String USER = "mfh_admin";
	/**
	 * The password hash contains the salted hash created
	 * by the password hash class.
	 */
	private String hashedPw;
	/**
	 * The minimum password size is five (5) characters.
	 */
	private static final int MIN_PW_SIZE = 5;
	/**
	 * The admin account constuctor is null.
	 */
	public AdminAccount() {
		//Null constructor
	}
	
	/**
	 * The verify user name method ensures the user name
	 * matches to the user name stored as a String.
	 * @return true if the user name matches the one provided
	 * by the user and false otherwise.
	 */
	public boolean verifyUserName(String userName) {
		return (userName.equals(USER));
	}
	
	/**
	 * The verify password method verifies a password via the
	 * password hash class supplied in another file. For testing, the
	 * method is allowed to crash for the purposes of using the
	 * stack trace generated. 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean verifyPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return (PasswordHash.validatePassword(password, hashedPw));
	}
	
	/**
	 * The set password method gets the user's password and
	 * stores it as a salted hash. It throws errors for testing--later
	 * it will be updated with a more robust try/catch fix.
	 * @param password 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void setPasswordHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException  {
		if (password.length <= MIN_PW_SIZE) {
			//Throw error
		}
		hashedPw = PasswordHash.createHash(password);
	}
	
	/**
	 * The get pw hash method retrieves the password hash.
	 */
	public String getPwHash() {
		return this.hashedPw;
	}
}
