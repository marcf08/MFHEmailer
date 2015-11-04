package marcus.email.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.lang.model.element.Parameterizable;

/**
 * The patron database is the list of patrons. It's primary data member
 * is an array list. The patron data base can also generate a basic list
 * of it's members given a last or first name.
 * @author Marcus
 *
 */
 public class PatronDB implements Serializable {
	//Serial id for changes
	private static final long serialVersionUID = 1262692812915379833L;
	//This is the main data member
	private ArrayList<Patron> database;
	//This is for deleted patrons
	private ArrayList<Patron> deleted;
	
	/**
	 * The constructor creates the array list referred to as database.
	 */
	public PatronDB() {
		database = new ArrayList<>();
		deleted = new ArrayList<>();
	}

	/**
	 * This method returns an array list of the patrons by a certain name.
	 * For example, if we search for "Smith", this method pulls all the 
	 * Smiths from the data base for display. It uses a for-loop to search
	 * for matches in the main list and add them to the list it will
	 * return.
	 * @param lastName the last name to search for
	 */
	public ArrayList<Patron> getPatronListByLastName(String lastName) {
		ArrayList<Patron> listByLastName = new ArrayList<>();
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getLastName().equalsIgnoreCase(lastName)) {
				listByLastName.add(database.get(i));
			}
		}
		return listByLastName;
	}
	
	/**
	 * This method returns an array list of the patrons by a certain name.
	 * For example, if we search for "John", this method pulls all the 
	 * Johns from the database for display. It uses a for-loop to search
	 * for matches in the main list and add them to the list it will
	 * return.
	 * @param firstName the first name to search for
	 */
	public ArrayList<Patron> getPatronListByFirstName(String firstName) {
		ArrayList<Patron> listByFirstName = new ArrayList<>();
		
		for (int i = 0; i < database.size(); i++) {
			//Ignoring case, if a last name matches the target last name,
			//then the method will add it to the array list to return.
			if (database.get(i).getFirstName().equalsIgnoreCase(firstName)) {
				listByFirstName.add(database.get(i));
			}
		}
		return listByFirstName;
	}
	
	/**
	 * This method returns an array list of the patrons by a certain date of
	 * birth. For example, if we search for 1900/10/10, this method pulls
	 * all the users from the database for display. It uses a for-loop to search
	 * for matches in the main list and add them to the list it will
	 * return.
	 * @param dob the date of birth to search for
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Patron> getPatronListByDOB(Date dob) {
		ArrayList<Patron> listByDOB = new ArrayList<>();
	
		int monthToMatch = dob.getMonth();
		int dayToMatch = dob.getDay();

		for (int i = 0; i < database.size(); i++) {
			//Break up the if statements--if the month matches, check the day.
			//This ensures both match before adding the patron to the database.
			if (database.get(i).getPatronDOB().getMonthOfYear() == monthToMatch) {
				if (database.get(i).getPatronDOB().getDayOfYear() == dayToMatch) {
					listByDOB.add(database.get(i));
				}
			}
		}
		return listByDOB;
	}
	
	/**
	 * This method returns an array list of the patrons by registration date.
	 * For example, if we search for 1900/10/10, this method pulls
	 * all the users from the database for display. It uses a for-loop to search
	 * for matches in the main list and add them to the list it will
	 * return.
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Patron> getPatronListBySince(Date since) {
		ArrayList<Patron> listBySince = new ArrayList<>();
		
		//The date method is very precise. For that reason, we'll just
		//try to match the day and month.
		int monthToMatch = since.getMonth();
		int dayToMatch = since.getDay();

		for (int i = 0; i < database.size(); i++) {
			//Break up the if statements--if the month matches, check the day.
			//This ensures both match before adding the patron to the database.
			if (database.get(i).getPatronSince().getMonthOfYear() == monthToMatch) {
				if (database.get(i).getPatronSince().getDayOfYear() == dayToMatch) {
					listBySince.add(database.get(i));
				}
			}
		}
		return listBySince;
	}
	
	
	/**
	 * This method sorts the list by emails. It does not alter the main data member.
	 * @param partialEmail a partial email string
	 */
	public ArrayList<Patron> getSortedByEmails(String partialEmail) {
		ArrayList<Patron> sortedByEmails = new ArrayList<Patron>();
		
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getPatronEmail().contains(partialEmail)) {
				sortedByEmails.add(database.get(i));
			}
		}
		
		//Define a simple email comparator
		Comparator<Patron> emailOrder =  new Comparator<Patron>() {
	        public int compare(Patron patron, Patron otherPatron) {
	            return patron.getPatronEmail().compareTo(otherPatron.getPatronEmail());
	        }
	    };
	    
	    Collections.sort(sortedByEmails, emailOrder);
	    return sortedByEmails;
	}
	
	
	/**
	 * This method sorts the list by last name. It does not alter the main data member,
	 * but rather, it returns a sorted list.
	 */
	public ArrayList<Patron> getSortedByLast() {
		ArrayList<Patron> sortedCopyOfMaster = new ArrayList<Patron>(database);
		
		Collections.sort(sortedCopyOfMaster);
		
		return sortedCopyOfMaster;
	}
	
	
	/**
	 * The import method imports a simple text file. It assumes the file is
	 * tab delimited. It uses separate helper methods to acquire the last name,
	 * the first name, and the date.
	 * @param userFile a user supplied text file
	 */
	public void importFromFile(File userFile) {
		Scanner fileScanner = null;
		Scanner lineScanner = null;
		String lineOfInput = null;
	
		try {
			fileScanner = new Scanner(userFile);
			fileScanner.useDelimiter("\t");
			while (fileScanner.hasNextLine()) {
				
				lineOfInput = fileScanner.nextLine();
				lineScanner = new Scanner(lineOfInput);
				String tempLast = lineScanner.next();
				String tempFirst = lineScanner.next();
				String email = lineScanner.next();
				String dob = lineScanner.next();
				String anniv = lineScanner.next();
			
				//Configure the new patron, add it to the list
				Patron toAdd = new Patron(email);
				toAdd.setLastName(tempLast);
				toAdd.setFirstName(tempFirst);
				toAdd.checkAndSetDate(dob);
				toAdd.checkAndSetAnniv(anniv);
				database.add(toAdd);
				
			}
		} catch (FileNotFoundException e) {
			//Handle outside of this method
		}
	}
	
	/**
	 * This method simply prints the list for testing.
	 * @return a string representation of the database
	 */
	public String toString() {
		StringBuffer toReturn = new StringBuffer();

		for (int i = 0; i < database.size(); i++) {
			toReturn.append(database.get(i).toString());
			toReturn.append("\n");
		}
		
		return toReturn.toString();
	}
	
	/**
	 * This method gets the list of first names
	 * @return the list of first names
	 */
	public String[] getListOfFirstNames() {
		String [] firstNames = new String[database.size()];
		for (int i = 0; i < database.size(); i++) {
			firstNames[i] = database.get(i).getFirst();
		}
		return firstNames;
	}
	
	/**
	 * This method gets the list of last names
	 * @return the last of last names
	 */
	public String[] getListOfLastNames() {
		String [] lastNames = new String[database.size()];
		for (int i = 0; i < database.size(); i++) {
			lastNames[i] = database.get(i).getLast();
		}
		return lastNames;
	}
	
	/**
	 * This method returns the list of emails
	 * @return the list of emails
	 */
	public String[] getListOfEmails() {
		String [] emails = new String[database.size()];
		for (int i = 0; i < database.size(); i++) {
			emails[i] = database.get(i).getPatronEmail();
		}
		return emails;
	}
	
	/**
	 * This method returns the list of date of births
	 * @return the date of birth list
	 */
	public String[] getListOfBirthdays() {
		String [] birthdays = new String[database.size()];
		for (int i = 0; i < database.size(); i++) {
			birthdays[i] = database.get(i).getDOB();
		}
		return birthdays;
	}
	
	/**
	 * This method returns the list of dates of patron since
	 * @return the patron since list
	 */
	public String[] getListOfPatronSince() {
		String [] since = new String[database.size()];
		for (int i = 0; i < database.size(); i++) {
			since[i] = database.get(i).getPatronSinceString();
		}
		return since;
	}
	
	/**
	 * This method returns the list of dates of patron anniversaries.
	 * @return the list of anniversary dates
	 */
	public String[] getListOfPatronAnniv() {
		String [] anniv = new String[database.size()];
		for (int i = 0; i < database.size(); i++) {
			anniv[i] = database.get(i).getAnniv().toString();
		}
		return anniv;
	}
	
	/**
	 * This method returns an array of patrons whose
	 * last name matches the partial last name provided.
	 */
	public ArrayList<Patron> getMatching(String partialLast) {
		ArrayList<Patron> partialLastNamePatronList = new ArrayList<Patron>();
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getLast().contains(partialLast)) {
				partialLastNamePatronList.add(database.get(i));
			}
		}
		return partialLastNamePatronList;
	}
	
	/**
	 * This method allows the method to search for the unique patron.
	 * @param email the patron's email
	 */
	public void edit(String email, String newFirstName, String newLastName,
					String dob) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getPatronEmail().equals(email)) {
					database.get(i).setFirstName(newFirstName);
					database.get(i).setLastName(newLastName);
					database.get(i).checkAndSetDate(dob);
				}
			}
	}
	
	/**
	 * Simple getter via the indexes.
	 * @param i the index of the patron
	 */
	public Patron get(int i) {
		return database.get(i);
	}
	/**
	 * This method returns the size of the list.
	 * @return the size of the list
	 */
	public int getSize() {
		return database.size();
	}
		
	/**
	 * This method adds a patron to the database.
	 * @param fromGUI a patron from the GUI
	 * @throws illegal argument exception if the patron already exists
	 */
	public void add(Patron fromGUI) {
		if (isValidPatron(fromGUI)) {
			database.add(fromGUI);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
		
	
	
	/**
	 * This method ensures the patron does not exist in the database. 
	 * The database does not permit multiple instances of the same
	 * email address.
	 * @param patron the patron to check
	 */
	public boolean isValidPatron(Patron patron) {
		for (int i = 0; i < getSize(); i++) {
			if (database.get(i).getPatronEmail().equals(patron.getPatronEmail())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method removes a patron from the database. It also
	 * adds the patron to the deleted database.
	 * @return the removed patron
	 */
	public void remove(String emailToRemove) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).getPatronEmail().equals(emailToRemove)) {
				deleted.add(database.remove(i));
			}
		}
	}
	
}
