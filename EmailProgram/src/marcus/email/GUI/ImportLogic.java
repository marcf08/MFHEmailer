package marcus.email.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	
	//Null constructor
	public ImportLogic(File file) {
		theList = readFromFile(file);
		size = theList.length;
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
		try {
			scan = new Scanner(importFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Patron[] list = new Patron[count(scan)];
		
		int i = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			Scanner lineScanner = new Scanner(line);
			String lastName = lineScanner.next();
			String firstName = lineScanner.next();
			String email = lineScanner.next();
			String dob = lineScanner.next();
			
			Patron toAdd = new Patron(firstName, lastName, dob, email);
			list[i] = toAdd;
			i++;
		}
		
		return list;
	}
	
	
	/**
	 * This simple method counts the patrons first.
	 * @return the number of entries in the file
	 */
	private int count(Scanner scan) {
		int i = 0;
		while (scan.hasNextLine()) {
			i++;
		}
		return i;
	}
	
	/**This gets the size of the list.
	 * 
	 */
	public int size() {
		return this.size;
	}
}
