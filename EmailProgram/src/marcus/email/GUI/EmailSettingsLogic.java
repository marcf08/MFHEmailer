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
			FileOutputStream fos = new FileOutputStream(new File(FileConstants.CONFIG_LOC));
			prop.setProperty(FileConstants.CONFIG_API, api);
			prop.store(fos, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This gets the key from the properties file.
	 * @return the api key from the properties file
	 */
	public String getKey() {
		try {
			FileInputStream fis = new FileInputStream(new File(FileConstants.CONFIG_LOC));
			prop.load(fis);
			return prop.getProperty(FileConstants.CONFIG_API);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This sets the key in the properties file. Specifically, it sets the email key.
	 * @param email the email
	 */
	public void setEmailKey(String email) {
		try {
			FileOutputStream fos = new FileOutputStream(FileConstants.CONFIG_LOC);
			prop.setProperty(FileConstants.CONFIG_FROM, email);
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
			FileInputStream fis = new FileInputStream(FileConstants.CONFIG_LOC);
			prop.load(fis);
			return prop.getProperty(FileConstants.CONFIG_FROM);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
