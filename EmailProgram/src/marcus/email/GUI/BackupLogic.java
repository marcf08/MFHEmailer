package marcus.email.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Properties;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGrid.Response;
import com.sendgrid.SendGridException;

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
	public static final String API_KEY = "SendGridAPIKey";
	public static final String FROM_KEY = "FromAddress";
	public static final String SUBJ = "Backup";
	
	
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
		String dateStamp = LocalDate.now().toString();
		dateStamp.replace("-", "_");
		String fileName = "PatronDatabaseBackup_" + dateStamp;
		return EmailerClientGUI.dblogic.createBackUp(fileName);
	}
	
	
	/**
	 * This method sends an email with the backup file to the selected email.
	 * @param email the email to which the backup is sent
	 */
	public boolean sendBackup(String email) {
		try {
			FileInputStream fis = new FileInputStream(EmailSettingsLogic.LOC);
			prop.load(fis);

			SendGrid send = new SendGrid(prop.getProperty(API_KEY));
			Email backupEmail = new Email();

			backupEmail.addTo(email);
			backupEmail.setSubject(SUBJ + LocalDate.now().toString());
			backupEmail.addAttachment(SUBJ, setupDatabaseBackup());
			backupEmail.setFrom(prop.getProperty(FROM_KEY));
			backupEmail.setText("See attached backup file --MFH Emailer");

			Response response = send.send(backupEmail);
			return response.getStatus();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SendGridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * This method returns the backup file created by the patron DB logic class.
	 * @param dir a symbolic file for which to get the path, taken from the browse button
	 * in the main GUI
	 */
	public void saveBackUpFile(File dir) {
		try {
			File toSave = new File(dir.getAbsolutePath() + ".txt");
			FileWriter write = new FileWriter(toSave);
			write.write(EmailerClientGUI.dblogic.createBackUpString());
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
