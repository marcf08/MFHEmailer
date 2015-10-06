package marcus.email.emailconfiguration;


/**
 * The GUI settings logic class gets the email port server settings.
 * It provides setters and getters for the settings.
 * @author Marcus
 *
 */
public class ServerSettingsLogic {
	//Settings information stored in Strings
	private String smtp;
	private String portNumber;
	private String smtpEnable;
	private String setTrue;
	private String setFalse;
	
	/**
	 * The constructor starts null.
	 */
	public ServerSettingsLogic() {
		//Null constructor
	}
	
	/**
	 * This constructor sets the data members initially given the arguments.
	 * @param smtp a smtp server
	 * @param portNumber the port number
	 * @param smtpEnable the enable criteria
	 * @param setTrue for true config settings
	 * @param setFalse for false config settings
	 */
	public ServerSettingsLogic(String smtp, String portNumber, String smtpEnable, String setTrue, String setFalse) {
		this.smtp = smtp;
		this.portNumber = portNumber;
		this.smtpEnable = smtpEnable;
		this.setTrue = setTrue;
		this.setTrue = setFalse;
	}
	
	

}

