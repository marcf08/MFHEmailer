package marcus.email.util.time;

import marcus.email.util.EmailTemplate;

/**
 * Send model is the object used by the GUI to control sending of emails.
 * In particular it starts and stops the email sending.
 * @author Marcus
 */
public class SendModel {
	private AutoSender birthdaySender;
	//TODO: Create autosender for anniversary
	
	public SendModel(EmailTemplate birthdayTemplate, EmailTemplate anniversaryTemplate) {	
		birthdaySender = new AutoSender(template);

	
	}
	
	
	
	
	
}
