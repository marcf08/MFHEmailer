package marcus.email.database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The patron class consists of several simple data members that relates to a
 * patron. It includes the first, last name, email, and date of birth.
 * 
 * @author Marcus
 *
 */
public class Patron {
	// String for first name
	private String firstName;

	// String for last name
	private String lastName;

	// String pattern for date
	private static final String DATE_FORMAT = "MM/DD/YY";

	// Ints for invalid date--to be used in the event of a
	// parse error. This section also provides minimum years.
	private int defYear = 0000;
	private int defMonth = 00;
	private int defDay = 00;
	private int minYear = 1900;
	private int minDay = 0;
	private int maxDay = 31;
	private int minMonth = 0;
	private int maxMonth = 12;

	// Patron's date of birth
	private Date patronDOB;

	// Date the patron was added to the system
	private Date patronSince;

	/**
	 * The non-null constructor sets up a patron with all attributes. It also
	 * calls the method set the date the patron was added.
	 */
	public Patron(String firstName, String lastName, String dob) {
		this.firstName = firstName;
		this.lastName = lastName;

		// Set the date of registration
		patronAddedDate();
		checkAndSetDate(dob);
	}

	/**
	 * This helper method simply registers the date the patron was added.
	 */
	private void patronAddedDate() {
		patronSince = new Date();
	}

	/**
	 * This method checks the date and sets it appropriately. If the date is
	 * not valid, or if there were any errors parsing the string, this method
	 * automatically sets the date to the default, 0000/00/00.
	 * 
	 * @param dob
	 *            a date of birth formatted as a String--the method checks this
	 *            and, if valid, sets the date
	 */
	private void checkAndSetDate(String dob) {
		try {
			String yearToConvert = dob.substring(0, 3);
			String monthToConvert = dob.substring(5, 6);
			String dayToConvert = dob.substring(7, 8);
			
			int year = Integer.parseInt(yearToConvert);
			int month = Integer.parseInt(monthToConvert);
			int day = Integer.parseInt(dayToConvert);

			// Ensure the day is valid, if not, set as default
			if (!isValidDate(year, month, day)) {
				setDOB(defYear, defMonth, defDay);
			}
			
			//If there are no errors, set the valid date
			setDOB(year, month, day);
	
		} catch (IndexOutOfBoundsException e) {
			setDOB(defYear, defMonth, defDay);
		} catch (NumberFormatException e) {
			// If there's an error, simply set a default day
			setDOB(defYear, defMonth, defDay);
		}
	}

	/**
	 * This helper method converts a date supplied to register the date of
	 * birth.
	 */
	private void setDOB(int year, int month, int day) {
		// We create a calendar object and set it to the birth day
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);

		// Then set the date of birth to the calendar's time
		patronDOB = cal.getTime();
	}

	/**
	 * This method simply checks the date to ensure it's valid.
	 * 
	 * @return true if the date is valid and false otherwise
	 */
	private boolean isValidDate(int year, int month, int day) {
		if (year < minYear) {
			return false;
		}

		if (month < minMonth) {
			return false;
		}

		if (month > maxMonth) {
			return false;
		}

		if (day < minDay) {
			return false;
		}

		if (day > minDay) {
			return false;
		}

		// At this point, the date should be correct
		return true;
	}

	
	/**
	 * Getter for the first name.
	 * @return the patron's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Setter for the first name.
	 * @param firstName the patron's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the last name
	 * @return the patron's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the last name
	 * @param lastName the patron's last name
	 */
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter for the date of birth
	 * @return patronDOB the patron's date of birth
	 */
	public Date getPatronDOB() {
		return patronDOB;
	}

	/**
	 * Setter for the date of birth.
	 * @param patronDOB the patron's date of birth
	 */
	public void setPatronDOB(Date patronDOB) {
		this.patronDOB = patronDOB;
	}
	
	/**
	 * Getter for the patron's first start date
	 * @return patronSince the patron's registration date
	 */
	public Date getPatronSince() {
		return patronSince;
	}

}
