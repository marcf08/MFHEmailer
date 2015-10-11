package marcus.email.util;

import java.util.Date;

import marcus.email.database.Patron;

/**
 * This simple class handles the acquisition of patron information
 * from the add patron GUI. //TODO: This needs to send the new patron
 * to the database.
 * @author Marcus
 *
 */

public class PatronLogic {
	
	//These data members will be set by the add patron gui,
	//and retrieved with the getter methods.
	private String firstName;
	private String lastName;
	private String email;
	private String birthday;

	
	/**
	 * The null constructor enables access to the logic in order
	 * to create patrons.
	 */
	public PatronLogic() {
		//Null
	}
	
	/**
	 * Default constructor -- uses information from GUI to construct a new patron
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param birthday the birthday
	 */
	public PatronLogic(String firstName, String lastName, String email, String birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.email = email;
		
	}
	
	/**
	 * This method gets the patron using the information obtained from the GUI.
	 * It uses the logic of this class to set this information.
	 * @return the newly created patron from the GUI
	 */
	public Patron getPatronFromGUI() {
		Patron toAdd = new Patron(this.firstName, this.lastName, this.birthday, this.email);
		System.out.println(toAdd.toString()); //TODO: FOR TESTING
		return toAdd;
	}

	/**
	 * Sets the first name
	 * @param firstName the first name from the gui
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name
	 * @param lastName the last name from the gui
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Sets the email
	 * @param email the email from the gui
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the birthday
	 * @param birthday the birthday from the gui
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}
