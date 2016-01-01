package marcus.email.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

import marcus.email.GUI.FileConstants;
import marcus.email.database.Patron;

/**
 * This class prepares and sends a list of promotional emails--emails sent to the entire data base.
 * @author Marcus
 *
 */
public class SendPromo {
	private ArrayList<Patron> recipients;
 	private EmailTemplate template;
 	private SendGrid send;
 	private Properties prop;
 	private FileInputStream fis;
 	private String api;
 	private File file;
 	
 	
	//Simply instantiates the main data member
	public SendPromo() {
		recipients = new ArrayList<Patron>();
		prop = new Properties();
	}
	
	/**
	 * This method tries to load the file in order to get the properties file.
	 * @param filePath the path to default setup
	 */
	public void defaultSetup(String filePath) {
		file = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
			send = new SendGrid(prop.getProperty(FileConstants.CONFIG_API));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a list of patrons to this promo email.
	 * @param patrons to whom the emails will be sent
	 */
	public void setPatrons(ArrayList<Patron> patrons) {
		recipients.addAll(patrons);
	}
	
	/**
	 * Sets the template to  the parameter
	 * @param template the template to set
	 */
	public void setTemplate(EmailTemplate template) {
		this.template = template;
	}
	
	/**
	 * Sets up send grid given an api key
	 * @param api
	 */
	public void setSendGridKey(String api) {
		send = new SendGrid(api);
 	}
	
	/**
	 * Gets the list of patrons assigned to this promo.
	 * @return the patrons assigned to this promo.
	 */
	public ArrayList<Patron> getPatrons() {
		ArrayList<Patron> copy = new ArrayList<Patron>();
		copy.addAll(recipients);
		return copy;
	}
	
	/**
	 * This method attempts to send the promotional emails.
	 * @return result the results of sending this promotion
	 */
	public String send() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < recipients.size(); i++) {
			Email emailToSend = new Email();
			emailToSend.addTo(recipients.get(i).getPatronEmail());
			emailToSend.setFrom(FileConstants.CONFIG_FROM);
			emailToSend.setSubject(template.getFormattedSubject(recipients.get(i)));
			emailToSend.setHtml(template.addPatronInformation(recipients.get(i)));
			try {
				SendGrid.Response response = send.send(emailToSend);
				result.append(response.getMessage());
			} catch (SendGridException e) {
				result.append(e.getMessage());
				i++;
		}
		
		}
		return result.toString();
	}
		
}
