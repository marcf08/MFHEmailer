package marcus.email.GUI;

import java.util.Properties;

import com.sendgrid.SendGrid;

/**
 * The backup logic class simply controls the backup logic. It handles
 * sending an email with the backup file as well as saving the file to the
 * desktop.
 * @author Marcus
 *
 */
public class BackupLogic {
	//The properties class is needed in order to get the api key.
	Properties prop;
	
	
	/**
	 * This is a simple null constructor.
	 */
	public BackupLogic() {
		prop = new Properties();
	}
	
	
	/**
	 * This method creates the data backup and returns
	 * it as a file.
	 * @return the file with the backup data
	 */
	private File setupDatabaseBackup() {
		
	}
	
	
	/**
	 * This method sends an email with the backup file to the selected email.
	 * @param email the email to which the backup is sent
	 */
	public void sendBackup(String email) {
		SendGrid send = new SendGrid();
	}
	
}
