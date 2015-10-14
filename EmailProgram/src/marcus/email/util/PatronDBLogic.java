package marcus.email.util;

import java.io.File;

import marcus.email.database.Patron;
import marcus.email.database.PatronDB;

/**
 * This class manages the patron db and sends it to the GUI.
 * @author Marcus
 *
 */
public class PatronDBLogic {
	/**
	 * This is the main datamember of the class.
	 */
	private marcus.email.database.PatronDB db;
	/**
	 * This instantiates the patron db and adds the file.
	 */
	public PatronDBLogic() {
		db = new PatronDB();
		//For testing
		db.importFromFile(new File("C:/Users/Marcus/Desktop/patronTest.txt"));
	}
	
	/**
	 * This method sends the patron db list to the JList in the GUI.
	 */
	public String [] getList() {
		String [] memberList = new String[db.getSize()];
		for (int i = 0; i < db.getSize(); i++) {
			memberList[i] = db.get(i).toString();
		}
		return memberList;
	}
	
	/**
	 * Returns the size
	 */
	public int getSize() {
		return db.getSize();
	}
	
	/**
	 * The below methods send the patron information to the jtable in the GUI
	 */
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
	 * This method gets the list of last names
	 * @return the last of last names
	 */
	public String[] getListOfLastNames() {
		String [] lastNames = new String[db.getSize()];
		for (int i = 0; i < db.getSize(); i++) {
			lastNames[i] = db.get(i).getLast();
		}
		return lastNames;
	}
	
	/**
	 * This method returns the list of emails
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
}
