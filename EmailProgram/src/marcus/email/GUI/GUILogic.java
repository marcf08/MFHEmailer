package marcus.email.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import marcus.email.admin.AdminAccount;

/**
 * GUI logic is responsible for getting the input from the GUI and writing it
 * to the properties file.
 * 
 * @author Marcus
 *
 */

public class GUILogic {
	/**
	 * The admin account is used for accessing/setting the password.
	 */
	private AdminAccount admin;
	/**
	 * The prop data member is the properties object we will modify.
	 */
	private Properties prop;
	/**
	 * The file name is the name of the properties file.
	 */
	private File propFile;
	/**
	 * The string is for the name of the prop file.
	 */
	private static final String CONFIG_FILE = "C:/Users/Marcus/git/EmailProgram/EmailProgram/resources/config.properties";
	/**
	 * This string is for a simple heading in the properties file.
	 * In addition, there are some other parts of the key-value pair.
	 */
	private static final String SETUP = "Setup";
	private static final String EMAIL = "email";
	private static final String FIRST_OPEN = "firstrun";
	private static final String FIRST_OPEN_FALSE = "1";
	
	//These strings related to the key value pairs in the properties file
	private static final String smtp = "smtp";
	private static final String portNumber = "portNumber";
	private static final String smtpEnable = "smtpEnable";
	private static final String setTrue = "setTrue";
	private static final String smtpSetFalse = "smtpSetFalse";
	private static final String testEmail = "testEmail";
	
	
	/**
	 * The constructor instantiates the properties object.
	 * @throws IOException 
	 */
	public GUILogic() throws IOException {
		admin = new AdminAccount();
		prop = new Properties();
		propFile = new File(CONFIG_FILE);
		prop.load(new FileInputStream(CONFIG_FILE));
	}

	/**
	 * This method stores the password hash to the properties file.
	 * @param password the user's password to be stored as a hash
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void storePasswordHash(char [] password) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		admin.setPasswordHash(password);
		prop.setProperty("passwordHash", admin.getPwHash());
		prop.store(new FileOutputStream(CONFIG_FILE), SETUP);
	}
	
	/**
	 * This method stores the email to the propeties file.
	 * @param email the user's email
	 * @throws IOException for now until we implement try/catch
	 * @throws FileNotFoundException for now until we implement try/catch
	 */
	public void storeEmail(String email) throws FileNotFoundException, IOException {
		prop.setProperty(EMAIL, email);
		prop.store(new FileOutputStream(CONFIG_FILE), SETUP);
	}
	
	/**
	 * This method sets whether or not the application has been opened for
	 * the first time.
	 * @throws IOException for now, until we can handle this later
	 * @throws FileNotFoundException for now, until we can handle this later
	 */
	public void storeFirstOpen() throws FileNotFoundException, IOException {
		prop.setProperty(FIRST_OPEN, FIRST_OPEN_FALSE);
		prop.store(new FileOutputStream(CONFIG_FILE), SETUP);
	}
	
	/**
	 * This method stores information related to the server settings.
	 */
	public void storeServerSettings(String smtp, String portNumber, String smtpEnable, String setTrue, String testEmail) {
		prop.setProperty(this.smtp, smtp);
		prop.setProperty(this.portNumber, portNumber);
		prop.setProperty(this.smtpEnable, smtpEnable);
		prop.setProperty(this.setTrue, setTrue);
		prop.setProperty(this.testEmail, testEmail);
		
		
	}
}
	

