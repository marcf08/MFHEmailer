package marcus.email.database;

import java.util.Date;

/**
 * The patron class consists of several simple data members that relates to a patron.
 * It includes the first, last name, email, and date of birth. This is an immutable
 * class.
 * @author Marcus
 *
 */

public class Patron {
	//String for first name
	private String firstName;
	
	//String for last name
	private String lastName;
	
	//String for date of birth
	private Date patronSince;
	
	//String of 
	
	/**
	 * The default constructor sets up a blank patron to 
	 * be initialized later.
	 */
	public Patron() {

	}
	
	/**
	 * The non-null constructor sets up a patron with all attributes.
	 */
	public Patron (String firstName, String lastName, String dob) {
		this.firstName = firstName;
		this.lastName = lastName;
	
		//Register date of 
		patronSince = new Date();
	}
	
	
	/**
	 * This helper method converts a date supplied as to string to 
	 * Java date object.
	 */
	private Date dateFromStringConverter(String dob) {
		Date dob = new Date
	}
}
