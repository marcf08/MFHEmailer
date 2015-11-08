import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import marcus.email.GUI.FileConstants;
import marcus.email.admin.PasswordHash;

public class PasswordInitialSet {
	
	public static Properties prop;

	public  static void main(String[] args) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(new File(FileConstants.CONFIG_LOC));
		prop = new Properties();
		System.out.println(prop.get(FileConstants.CONFIG_HASH));
		boolean b = changePassword("testtwo", "password", "password");
		System.out.println(b);
		System.out.println(prop.get(FileConstants.CONFIG_HASH));

		
	
	}
	
	public static boolean changePassword(String oldPW, String passwordNewInit, String passwordConfirm) {
		try {

			if (!passwordNewInit.equals(passwordConfirm)) {
				return false; //Bail if the passwords don't match
			}

			if (!PasswordHash.validatePassword(oldPW, getOldHash())) {
				return false; //Bail if the old hash doesn't match the input
			}
			
			FileOutputStream fos = new FileOutputStream(new File(FileConstants.CONFIG_LOC));
			prop.setProperty(FileConstants.CONFIG_HASH, PasswordHash.createHash(passwordConfirm));
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
		public static String getOldHash() {
			try {
				FileInputStream fis = new FileInputStream(new File(FileConstants.CONFIG_LOC));
				prop.load(fis);
				return prop.getProperty(FileConstants.CONFIG_HASH);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
}
