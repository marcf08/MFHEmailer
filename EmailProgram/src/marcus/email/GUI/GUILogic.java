package marcus.email.GUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import marcus.email.admin.AdminAccount;

/**
 * GUI logic is responsible for getting the input from the GUI and writing it
 * to the properties file.
 * 
 * @author Marcus
 */

public class GUILogic {
	
	//This is the admin account
	private AdminAccount admin;

	//Data member for the properties file
	private Properties prop;

	//This is the current address of the config file.
	//It should be modified to use a relative path later on.
	private static final String CONFIG_FILE = "C:/Users/Marcus/git/EmailProgram/EmailProgram/resources/config.properties";
	
	//These strings relate to initialization settings in the properties file
	private static final String SETUP = "Setup";
	private static final String EMAIL = "email";
	private static final String FIRST_OPEN = "firstrun";
	private static final String FIRST_OPEN_FALSE = "1";
	
	//These strings related to the key value pairs in the properties file
	private String smtp = "smtp";
	private String portNumber = "portNumber";
	private String smtpEnable = "smtpEnable";
	private String setTrue = "setTrue";
	private String testEmail = "testEmail";
	
	
	/**
	 * The constructor instantiates the properties object.
	 * @throws IOException 
	 */
	public GUILogic() throws IOException {
		admin = new AdminAccount();
		prop = new Properties();
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
	 * @param smtp the smtp server
	 * @param portNumber the port number
	 * @param smtpEnable the enable field
	 * @param setTrue the set true field
	 * @param testEmail the test email address
	 * @throws IOException for now, until we can handle later
	 * @throws FileNotFoundException for now, until we can handle later
	 */
	public void storeServerSettings(String smtp, String portNumber, String smtpEnable, String setTrue, String testEmail) throws FileNotFoundException, IOException {
		prop.setProperty(this.smtp, smtp);
		prop.setProperty(this.portNumber, portNumber);
		prop.setProperty(this.smtpEnable, smtpEnable);
		prop.setProperty(this.setTrue, setTrue);
		prop.setProperty(this.testEmail, testEmail);
		prop.store(new FileOutputStream(CONFIG_FILE), SETUP);
	}
}
	

