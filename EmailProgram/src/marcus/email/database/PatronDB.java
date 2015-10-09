package marcus.email.database;

import java.util.ArrayList;
import java.util.Date;

/**
 * The patron database is the list of patrons. It's primary data member
 * is an array list. The patron data base can also generate a basic list
 * of it's members.
 * @author Marcus
 *
 */

public class PatronDB {

	//This is the main data member
	private ArrayList<Patron> database;
	
	/**
	 * The constructor creates the array list referred to as database.
	 */
	public PatronDB() {
		database = new ArrayList<>();
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
			//Ignoring case, if a last name matches the target last name,
			//then the method will add it to the array list to return.
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
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Patron> getPatronListByDOB(Date dob) {
		ArrayList<Patron> listByDOB = new ArrayList<>();
		
		//The date method is very precise. For that reason, we'll just
		//try to match the day and month.
		int monthToMatch = dob.getMonth();
		int dayToMatch = dob.getDay();

		for (int i = 0; i < database.size(); i++) {
			//Break up the if statements--if the month matches, check the day.
			//This ensures both match before adding the patron to the database.
			if (database.get(i).getPatronDOB().getMonth() == monthToMatch) {
				if (database.get(i).getPatronDOB().getDay() == dayToMatch) {
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
			if (database.get(i).getPatronSince().getMonth() == monthToMatch) {
				if (database.get(i).getPatronSince().getDay() == dayToMatch) {
					listBySince.add(database.get(i));
				}
			}
		}
		return listBySince;
	}
	
	
	/**
	 * This method sorts the list by last name. It does not alter the main data member,
	 * but rather, it returns a sorted list.
	 */
	public ArrayList<Patron> getSortedByLast() {
		ArrayList<Patron> copyOfMaster = new ArrayList<Patron>(database);
		return copyOfMaster.sort(c.);;
		
	}
	
}
