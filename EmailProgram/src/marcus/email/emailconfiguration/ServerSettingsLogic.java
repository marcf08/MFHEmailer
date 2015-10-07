package marcus.email.emailconfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import marcus.email.GUI.GUILogic;


/**
 * The GUI settings logic class gets the email port server settings.
 * It provides setters and getters for the settings.
 * @author Marcus
 *
 */
public class ServerSettingsLogic {
	//Server settings has to write to a properties file
	//For that reason, it will use an instance of the logic class
	private static GUILogic logic;
	
	//Settings information stored in Strings
	private String smtp;
	private String portNumber;
	private String smtpEnable;
	private String setTrue;
	private String testEmail;
	/**
	 * The constructor starts null.
	 * @throws IOException until we fix it with try/catch
	 */
	public ServerSettingsLogic() throws IOException {
		logic = new GUILogic();
	}
	
	/**
	 * This constructor sets the data members initially given the arguments.
	 * @param smtp a smtp server
	 * @param portNumber the port number
	 * @param smtpEnable the enable criteria
	 * @param setTrue for true config settings
	 * @param setFalse for false config settings
	 */
	public ServerSettingsLogic(String smtp, String portNumber, String smtpEnable, String setTrue, String testEmail) {
		this.smtp = smtp;
		this.portNumber = portNumber;
		this.smtpEnable = smtpEnable;
		this.setTrue = setTrue;
		this.testEmail = testEmail;
	}
	
	
	/**
	 * This method saves the values to the properties file for use later.
	 * @throws IOException this will be corrected later
	 * @throws FileNotFoundException this will be corrected later
	 */
	public void storeServerSettings() throws FileNotFoundException, IOException {
		logic.storeServerSettings(smtp, portNumber, smtpEnable, setTrue, testEmail);
	}
	
	

}

