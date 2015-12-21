package marcus.email.database;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;

/**
 * The patron class consists of several simple data members that relates to a
 * patron. It includes the first, last name, email, and date of birth.
 * @author Marcus
 */
public class Patron implements Comparable <Patron>, Serializable {
	//The class is serializable for storage
	private static final long serialVersionUID = 3903729962060817467L;
	//Basic data members for a patron
	private String firstName;
	private String lastName;
	private String email;

	private LocalDate patronAnniv;
	private LocalDate patronDOB;
	private LocalDate patronSince;

	// Ints for invalid date.
	private final int defYear = 0000;
	private final int defMonth = 00;
	private final int defDay = 00;

	/**
	 * This class requires a null constructor in order to use the
	 * check and set date method.
	 */
	public Patron(String email) {
		this.email = email;
		patronAddedDate();
	}
	
	/**
	 * The non-null constructor sets up a patron with all attributes. It also
	 * calls the method set the date the patron was added.
	 */
	public Patron(String firstName, String lastName, String dob, String email, String anniv) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

		patronAddedDate();
		checkAndSetDate(dob);
		checkAndSetAnniv(anniv);
	}

	/**
	 * This helper method simply registers the date the patron was added.
	 */
	private void patronAddedDate() {
		patronSince = new LocalDate();
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
		
			String yearToConvert = dob.substring(0, 4);
			String monthToConvert = dob.substring(5, 7);
			String dayToConvert = dob.substring(8,10);
			
			int year = Integer.parseInt(yearToConvert);
			int month = Integer.parseInt(monthToConvert);
			int day = Integer.parseInt(dayToConvert);

			setDOB(year, month, day);
		} catch (IndexOutOfBoundsException e) {
			// If there's an error, simply set a default day
			setDOB(defYear, defMonth, defDay);
		} catch (NumberFormatException e) {
			// If there's an error, simply set a default day
			setDOB(defYear, defMonth, defDay);
		}
	}
	
	/**
	 * This method checks the anniversary date and sets it appropriately. If the date is
	 * not valid, or if there were any errors parsing the string, this method
	 * automatically sets the date to the default..
	 * 
	 * @param dob
	 *            a date of birth formatted as a String--the method checks this
	 *            and, if valid, sets the date
	 */
	public void checkAndSetAnniv(String anniv) {
		try {
			
			String yearToConvert = anniv.substring(0, 4);
			String monthToConvert = anniv.substring(5, 7);
			String dayToConvert = anniv.substring(8,10);
			
			int year = Integer.parseInt(yearToConvert);
			int month = Integer.parseInt(monthToConvert);
			int day = Integer.parseInt(dayToConvert);

			setAnniv(year, month, day);
	
		} catch (IndexOutOfBoundsException e) {
			setAnniv(defYear, defMonth, defDay);
		} catch (NumberFormatException e) {
			// If there's an error, simply set a default day
			setAnniv(defYear, defMonth, defDay);
		}
	}
	
	/**
	 * This helper method converts a date supplied to the anniversary.
	 * @param year the year of anniv
	 * @param month the month of the anniv
	 * @param day the day of the anniv
	 */
	private void setAnniv(int year, int month, int day) {
		patronAnniv = new LocalDate(year, month, day);
	}
	
	/**
	 * This helper method converts a date supplied to register the date of
	 * birth.
	 * @param year the year of birth
	 * @param month the month of birth
	 * @param day the day of birth
	 */
	private void setDOB(int year, int month, int day) {
		patronDOB = new LocalDate(year, month, day);
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
	public LocalDate getPatronDOB() {
		return patronDOB;
	}

	/**
	 * Setter for the date of birth.
	 * @param patronDOB the patron's date of birth
	 */
	public void setPatronDOB(LocalDate patronDOB) {
		this.patronDOB = patronDOB;
	}
	
	/**
	 * Getter for the patron's first start date
	 * @return patronSince the patron's registration date
	 */
	public LocalDate getPatronSince() {
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
	 * Gets the date of birth in a simple string
	 * @return the date of birth
	 */
	public String getDOB() {
		return patronDOB.toString();
	}
	
	/**
	 * Gets the patron since date
	 */
	public String getPatronSinceString() {
		return patronSince.toString();
	}
	
	/**
	 * This is a simple getter for the anniversary date.
	 * @return the patron anniversary date
	 */
	public String getAnniv() {
		return this.patronAnniv.toString();
	}
	
	
}
