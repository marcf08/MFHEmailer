package marcus.email.emailconfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import marcus.email.GUI.EmailSettingsLogic;
import marcus.email.database.Patron;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGrid.Response;
import com.sendgrid.SendGridException;

/**
 * This class creates a simple email via the API settings and similar settings.
 * @author Marcus
 *
 */
public class EmailToSend {

	//The email will pull this information from the settings file.
	private static SendGridSettings settings;
	
	//This datamember handles the sending of the email
	private SendGrid send;
	
	//While redundant, the application will set up an email object to modify.
	private Email email;
	
	//The properties file is needed in order to pull the api settings and the
	//from address specified in the settings area
	private Properties prop;
	
	/**
	 * This class gets the API key from the other classes.
	 */
	public EmailToSend() {
		settings = new SendGridSettings();
		email = new Email();
		prop = new Properties();
		readProperties();
		send = new SendGrid(prop.getProperty("SendGridAPIKey"));

		
		
	}
	
	/**
	 * This method loads the properties file in a read only manner.
	 */
	private void readProperties() {
		File settings = new File (EmailSettingsLogic.LOC);
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(settings);
			 prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Given a patron, this method builds an email. It takes the
	 * patron and strips the requisite information. It sends the email,
	 * and returns the server reply.
	 * @param patron a patron to whom this email will be sent
	 * @param subject the message subject
	 * @param emailHtml the html text of the message
	 * @return the server response
	 */
	public boolean buildSendEmail(Patron patron, String subject, String emailHtml) {
		email.addTo(patron.getPatronEmail());
		email.setFrom(prop.getProperty("FromAddress"));
		email.setHtml(emailHtml);
		
		Response serverReply = null;
		try {
			serverReply = send.send(email);
		} catch (SendGridException e) {
			// Nothing to handle, the reply will let us know if 
			// it was successful.
		}
		
		return serverReply.getStatus();
	}
	
	/**
	 * Given a list of patrons (as an array), this method sends the list to 
	 * all of them.
	 * @param list the list of patrons to send emails to
	 * @param occasion the occasion 
	 */
	public boolean[] sendEmailsToList(Patron[] list, char occasion) {
		boolean[] results = new boolean[list.length];
		
		

		for (int i = 0; i < list.length; i++) {
			
		}
		
		return results;
		
	}
}
