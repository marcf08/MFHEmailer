package marcus.email.emailconfiguration;

import java.io.File;
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
	private GUILogic logic;
	
	//Settings information stored in Strings
	private String smtp;
	private String portNumber;
	private String smtpEnable;
	private String setTrue;
	private String setFalse;
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
	public ServerSettingsLogic(String smtp, String portNumber, String smtpEnable, String setTrue, String setFalse, String testEmail) {
		this.smtp = smtp;
		this.portNumber = portNumber;
		this.smtpEnable = smtpEnable;
		this.setTrue = setTrue;
		this.setTrue = setFalse;
		this.testEmail = testEmail;
	}
	
	
	/**
	 * This method saves the values to the properties file for use later.
	 */
	public void storeServerSettings() {
		logic.storeServerSettings(smtp, portNumber, smtpEnable, setTrue, testEmail);
	}
	
	

}

