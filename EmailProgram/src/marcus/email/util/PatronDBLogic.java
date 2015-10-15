package marcus.email.util;

import java.io.File;
import java.util.ArrayList;

import marcus.email.database.Patron;
import marcus.email.database.PatronDB;

/**
 * This class manages the patron db and sends it to the GUI.
 * @author Marcus
 *
 */
public class PatronDBLogic {
	/**
	 * This is the main data member of the class.
	 */
	private PatronDB db;
	/**
	 * This instantiates the patron db and adds the file.
	 */
	public PatronDBLogic() {
		db = new PatronDB();
		//For testing
		db.importFromFile(new File("C:/Users/Marcus/Desktop/patronTest.txt"));
	}

	/**
	 * Returns the size
	 */
	public int getSize() {
		return db.getSize();
	}

	/**
	 * This method gets the list of first names
	 * @return the list of first names
	 */
	public String[] getListOfFirstNames() {
		String [] firstNames = new String[db.getSize()];
		for (int i = 0; i < db.getSize(); i++) {
			firstNames[i] = db.get(i).getFirst();
		}
		return firstNames;
	}

	/**
	 * This method gets the list of last names. The optional
	 * parameter allows the list to search via some last name.
	 * @param partialLast the partial last name to search for
	 * @return the last of last names
	 */
	public String[] getListOfLastNames(String partialLast) {
		if (partialLast == null) {
			String [] lastNames = new String[db.getSize()];
			for (int i = 0; i < db.getSize(); i++) {
				lastNames[i] = db.get(i).getLast();
			}
			return lastNames;
		}
		
		//We need the array list in order to obtain the size of names
		//to be presented to the user.
		ArrayList<String> last = new ArrayList<String>();
		for (int i = 0; i < db.getSize(); i++) {
			if (db.get(i).getLast().contains(partialLast)) {
				last.add(db.get(i).getLast());
			}
		}
		String [] lastPartial = new String[last.size()];
		lastPartial = last.toArray(lastPartial);
		return lastPartial;
	}

	/**
	 * This method returns the list of emails.
	 * @return the list of emails
	 */
	public String[] getListOfEmails() {
		String [] emails = new String[db.getSize()];
		for (int i = 0; i < db.getSize(); i++) {
			emails[i] = db.get(i).getPatronEmail();
		}
		return emails;
	}

	/**
	 * This method returns the list of date of births
	 * @return the date of birth list
	 */
	public String[] getListOfBirthdays() {
		String [] birthdays = new String[db.getSize()];
		for (int i = 0; i < db.getSize(); i++) {
			birthdays[i] = db.get(i).getDOB();
		}
		return birthdays;
	}

	/**
	 * This method returns the list of dates of patron since
	 * @return the patron since date
	 */
	public String[] getListOfPatronSince() {
		String [] since = new String[db.getSize()];
		for (int i = 0; i < db.getSize(); i++) {
			since[i] = db.get(i).getPatronSinceString();
		}
		return since;
	}

	/**
	 * This method returns the patron at a given index in the database.
	 * @param i the index of the patron
	 */
	public Patron getPatronFromDB(int i) {
		return db.get(i);
	}
	
	/**
	 * This method returns an array of patrons whose
	 * last name matches the partial last name provided.
	 */
	public ArrayList<Patron> getMatching(String partialLast) {
		ArrayList<Patron> partialLastNamePatronList = db.getMatching(partialLast);
		return partialLastNamePatronList;
	}
	

	
	/**
	 * This method allows the user to add a patron to the database.
	 */
	public void addPatronToDB(String first, String last, String email, String dob) {
		Patron toAdd = new Patron(first, last, dob, email);
		db.add(toAdd);

	}
	
	/**
	 * This method gets a sorted copy of the patron db list.
	 */
	public ArrayList<Patron> getAlphabetic() {
		return db.getSortedByLast();
	}
	
	/**
	 * This method gets a sorted copy of the patron db list by email.
	 */
	public ArrayList<Patron> getSortedByEmails(String partialEmail) {
		ArrayList<Patron> sortedByEmail = db.getSortedByEmails(partialEmail);
		return sortedByEmail;
	}
}
