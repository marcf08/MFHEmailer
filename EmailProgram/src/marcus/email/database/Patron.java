package marcus.email.database;
import java.util.Calendar;
import java.util.Date;

/**
 * The patron class consists of several simple data members that relates to a
 * patron. It includes the first, last name, email, and date of birth.
 * @author Marcus
 */
public class Patron implements Comparable <Patron> {
	//Basic data members for a patron
	private String firstName;
	private String lastName;
	private String email;
	private Date patronDOB;
	private Date patronSince;

	// Ints for invalid date.
	private final int defYear = 0000;
	private final int defMonth = 00;
	private final int defDay = 00;
	private final int minYear = 1900;
	private final int minDay = 0;
	private final int minMonth = 0;
	private final int maxMonth = 12;

	/**
	 * This class requires a null constructor in order to use the
	 * check and set date method.
	 */
	public Patron() {
		patronAddedDate();
	}
	
	/**
	 * The non-null constructor sets up a patron with all attributes. It also
	 * calls the method set the date the patron was added.
	 */
	public Patron(String firstName, String lastName, String dob, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

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
			String dayToConvert = dob.substring(8,10);
			
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
	 * This helper method sets the email.
	 * @param email the patron's email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Getter for the patron's email
	 * @return patronSince the patron's registration date
	 */
	public String getPatronEmail() {
		return email;
	}

	/**
	 * Gets the last name
	 * @return the patron's last name
	 */
	public String getLast() {
		return lastName;
	}
	
	/**
	 * Gets the first name
	 * @return the patron's first name
	 */
	public String getFirst() {
		return firstName;
	}
	
	/**
	 * Gets the date of birth in a simple string
	 * @return the date of birth
	 */
	public String getDOB() {
		StringBuffer toReturn = new StringBuffer(patronDOB.toString());
		toReturn.delete(0, 3);
		toReturn.delete(10,23);
		return toReturn.toString();
	}
	
	/**
	 * Gets the patron since date
	 */
	public String getPatronSinceString() {
		StringBuffer toReturn = new StringBuffer(patronSince.toString());
		toReturn.delete(0, 3);
		toReturn.delete(10,23);
		toReturn.insert(0, "Since: ");
		return toReturn.toString();
	}
}
