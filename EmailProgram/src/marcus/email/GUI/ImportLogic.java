package marcus.email.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import marcus.email.database.Patron;

/**
 * This class manages reading from a specific file.
 * @author Marcus
 *
 */
public class ImportLogic {

	//This will be used for getting the provisional list of patrons
	private Patron[] theList;
	
	//This is the size of the main list
	private Integer size;
	
	//This is the status integer used to indicate the progress of the import
	private int status = 0;
	
	//Null constructor
	public ImportLogic() {
		
	}
	
	/**
	 * This is an optional constructor in the event client sets up
	 * the import logic with an existing file.
	 * @param file the file to import from.
	 */
	public ImportLogic(File file) {
		theList = readFromFile(file);

		
	}
	
	/**
	 * This method gets an array of last names.
	 * @return the array of last names
	 */
	public String[] getLastNames() {
		String[] lastNames = new String[theList.length];
		for (int i = 0; i < theList.length; i++) {
			lastNames[i] = theList[i].getLast();
		}
		return lastNames;
	}
	
	/**
	 * This method gets the array of first names
	 */
	public String[] getFirstNames() {
		String[] firstNames = new String[theList.length];
		for (int i = 0; i < theList.length; i++) {
			firstNames[i] = theList[i].getFirst();
		}
		return firstNames;
	}
	
	/**
	 * This method gets the array of birthdays.
	 */
	public String[] getBirthdays() {
		String [] birthdays = new String[theList.length];
		for (int i = 0; i < theList.length; i++) {
			birthdays[i] = theList[i].getPatronDOB().toString();
		}
		return birthdays;
	}
	
	/**
	 * This method gets the array of anniversaries.
	 */
	public String[] getAnniv() {
		String [] anniv = new String[theList.length];
		for (int i = 0; i < theList.length; i++) {
			anniv[i] = theList[i].getAnniv().toString();
		}
		return anniv;
	}
	
	/**
	 * This method gets the list of emails. 
	 */
	public String[] getEmails() {
		String[] emails = new String[theList.length];
		for (int i = 0; i < theList.length; i++) {
			emails[i] = theList[i].getPatronEmail();
		}
		return emails;
	}
	
	
	/**
	 * This method reads from a file to populate the arrays.
	 */
	public Patron[] readFromFile(File importFile) {
		Scanner scan = null;
		Scanner count = null;
		try {
			scan = new Scanner(importFile);
			count = new Scanner(importFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int x = 0;
		while (count.hasNextLine()) {
			count.nextLine();
			x++;
		}
	
		size = x;
		
		Patron [] list = new Patron[x];
		int i = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			Scanner lineScanner = new Scanner(line);
			String lastName = lineScanner.next();
			String firstName = lineScanner.next();
			String email = lineScanner.next();
			String dob = lineScanner.next();
			String anniv = lineScanner.next();
			lineScanner.close();	
			
			Patron toAdd = new Patron(firstName, lastName, dob, email, anniv);
			list[i] = toAdd;
			i++;
		}
		
		return list;
	}
	
	
	/**
	 * This gets the size of the list.
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * This adds the list of patrons to the database.
	 * @return the number of patrons not added
	 */
	public int addPatronList() {
		int notAdded = 0;
		for (int i = 0; i < theList.length; i++) {
			try {
				EmailerClientGUI.dblogic.addPatronObject(theList[i]);
				updateStatus(i);
			} catch (IllegalArgumentException iae) {
				i++; //Skip the patron that already exists
				notAdded++;
			}
		}
		return notAdded;
	}
	
	/**
	 * During additions, this method will update the status integer after
	 * each patron is added.
	 * @param n the amout to add to the bar
	 */
	public void updateStatus(int n) {
		ImportStatusGUI.progressBar.setValue(n);
	}
	
	/**
	 * This gets the status.
	 */
	public int getStatus() {
		return status;
	}

}
