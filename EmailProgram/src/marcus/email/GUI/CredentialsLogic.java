package marcus.email.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import marcus.email.admin.PasswordHash;

/**
 * This class stores the pw settings and modifies them.
 * @author Marcus
 *
 */
public class CredentialsLogic {
	
	private Properties prop;
	private final String HASH_KEY = "AdminOldHash";
	
	/**
	 * This is the simple constructor.
	 */
	public CredentialsLogic() {
		prop = new Properties();
	}
	
	/**
	 * This method allows the admin to change the password.
	 * @param oldHash to ensure it's a valid user
	 * @param passwordNew the new password hash 
	 * @param passwordNewConfirm the new password hash for confirmation
	 * @return true if the password was changed, false otherwise
	 */
	public boolean changePassword(String oldPW, String passwordNewInit, String passwordConfirm) {
		try {
			
			if (!passwordNewInit.equals(passwordConfirm)) {
				return false; //Bail if the passwords don't match
			}
			
			
			if (!PasswordHash.validatePassword(oldPW, getOldHash())) {
				return false; //Bail if the old hash doesn't match the input
			}
			
			FileOutputStream fos = new FileOutputStream(new File(EmailSettingsLogic.LOC));
			prop.setProperty(HASH_KEY, PasswordHash.createHash(passwordConfirm));
			prop.store(fos, null);
			return true; //Return true so we know if it was a success
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * This method gets the old hash from the properties file.
	 * @return the old password hash
	 */
	public String getOldHash() {
		try {
			FileInputStream fis = new FileInputStream(new File(EmailSettingsLogic.LOC));
			prop.load(fis);
			return prop.getProperty(HASH_KEY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
