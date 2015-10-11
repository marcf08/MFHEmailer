package marcus.email.database;
import java.util.Calendar;
import java.util.Date;

/**
 * The patron class consists of several simple data members that relates to a
 * patron. It includes the first, last name, email, and date of birth.
 * @author Marcus
 */
public class Patron implements Comparable <Patron> {
	// String for first name
	private String firstName;

	// String for last name
	private String lastName;

	// Ints for invalid date--to be used in the event of a
	// parse error. This section also provides minimum years.
	private final int defYear = 0000;
	private final int defMonth = 00;
	private final int defDay = 00;
	private final int minYear = 1900;
	private final int minDay = 0;
	private final int minMonth = 0;
	private final int maxMonth = 12;

	// Patron's date of birth
	private Date patronDOB;

	// Date the patron was added to the system
	private Date patronSince;

	/**
	 * This class requires a null constructor in order to use the
	 * check and set date method.
	 */
	public Patron() {
	}
	
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
	public void checkAndSetDate(String dob) {
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
	 * @param year the year of birth
	 * @param month the month of birth
	 * @param day the day of birth
	 */
	private void setDOB(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		
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

		return true;
	}

	/**
	 * The comparable method allows us to compare two patrons
	 * by their last name for sorting purposes.
	 * @param patronOther the patron to compare this patron to
	 */
	public int compareTo(Patron patronOther) {
		return this.lastName.compareTo(patronOther.getLastName());
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

	/**
	 * This method converts the patron to a string. It is used for testing.
	 * @return the patron details
	 */
	public String toString() {
		return getLastName() + "\t" + getFirstName() + "\t" + getPatronDOB().toString() + "\t";
	}
}
