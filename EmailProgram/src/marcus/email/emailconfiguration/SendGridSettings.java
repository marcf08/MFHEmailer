package marcus.email.emailconfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import com.sendgrid.SendGrid;

import marcus.email.admin.PasswordHash;

/**
 * This class controls the send grid settings. 
 * @author Marcus
 *
 */
public class SendGridSettings implements Serializable {
	//This is the SendGrid account information
	private String accnt;
	//This is the SendGrid account password
	private String sendGridPasswordHash;
	//This is the properties file
	private Properties prop;
	//This is the settings file
	private String propertiesFileName;
	
	
	
	/**
	 * The null constructor sets up the class--the user will edit the
	 * rest from the GUI.
	 */
	public SendGridSettings() {
		prop = new Properties();
		load();
		
	}
	
	/**
	 * This is a simple setter for the account.
	 * @param accnt
	 */
	public void setAccount(String accnt) {
		this.accnt = accnt;
	}
	
	/**
	 * This is a simple setter for the password.
	 */
	public void setPasswordHash(String password) {
		try {
			sendGridPasswordHash = PasswordHash.createHash(password);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method saves the credentials to the properties file.
	 */
	public void save() {
		File file = new File("/EmailProgram/resources/config.properties");
		FileOutputStream fos = null;
		try {
			 fos = new FileOutputStream(file);
			 prop.setProperty("SendGridID", accnt);
			 prop.setProperty("SendGridPW", sendGridPasswordHash);
			 fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method gets the credentials from the properties file.
	 */
	public void load() {
		File file = new File("/EmailProgram/resources/config.properties");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accnt = prop.getProperty("SendGridID");
		sendGridPasswordHash = prop.getProperty("SendGridPW");
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * This method checks the password.
	 */
	public boolean checkPW(String pw) {
		try {
			return PasswordHash.validatePassword(pw, sendGridPasswordHash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

