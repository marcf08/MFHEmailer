package marcus.email.emailconfiguration;



import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * CreateMessage creates the body of an email.
 * @author Marcus
 *
 */
 
public class EmailServer {
	/**
	 * Dynamic settings for email message, contains the to, from,
	 * contents, etc.
	 */
	public String toAddress;
	
	public String fromAddress;

	/**
	 * Static settings for email message
	 */
	public static Properties mailServerProperties;
	public static Session getMailSession;
	public static MimeMessage generateMailMessage;
	
	public static void generateAndSendEmail() throws AddressException, MessagingException {
 
		// Step1
		System.out.println("Setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties set...");
 
		// Step2
		System.out.println("Setup Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("anyone@email.com"));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("anyone@email.com"));
		
		generateMailMessage.setSubject("Test");
		String emailBody = "TEST";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Working...Working...");
 
		// Success?
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Password
		transport.connect("smtp.gmail.com", "test@email.com", "testPassword");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	
	/**
	 * Runs the program -- strictly for testing
	 * @param args
	 * @throws AddressException
	 * @throws MessagingException
	 */
	
	public static void main(String [] args) throws AddressException, MessagingException {
		generateAndSendEmail();
	}
}