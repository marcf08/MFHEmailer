package marcus.email.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class stores the api key. It will be edited later in order to
 * make it more difficult to locate the key.
 * @author Marcus
 *
 */
public class EmailSettingsLogic {
	

	private Properties prop;
	public static final String LOC = "C:/Users/Marcus/git/EmailProgram/EmailProgram/resources/config.properties";
	private static final String PROP_KEY = "SendGridAPIKey";
	private static final String PROP_KEY_FROM = "FromAddress";
	
	/**
	 * This instantiates the logic class.
	 */
	public EmailSettingsLogic() {
		prop = new Properties();
	}
	
	/**
	 * This sets the key in the properties file.
	 * @param key the api key
	 */
	public void setKey(String api) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(LOC));
			prop.setProperty(PROP_KEY, api);
			prop.store(fos, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This gets the key from the properties file.
	 */
	public String getKey() {
		try {
			FileInputStream fis = new FileInputStream(new File(LOC));
			prop.load(fis);
			return prop.getProperty(PROP_KEY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This sets the key in the properties file.
	 * @param email the email
	 */
	public void setEmailKey(String email) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(LOC));
			prop.setProperty(PROP_KEY_FROM, email);
			prop.store(fos, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This gets the existing email key from the properties file.
	 * @return the email written to the properties file
	 */
	public String getEmailKey() {
		try {
			FileInputStream fis = new FileInputStream(new File(LOC));
			prop.load(fis);
			return prop.getProperty(PROP_KEY_FROM);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
