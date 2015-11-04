package marcus.email.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

import marcus.email.database.Patron;
import marcus.email.database.PatronDB;

/**
 * This class manages the patron db and sends it to the GUI.
 * @author Marcus
 *
 */
public class PatronDBLogic implements Serializable {
	//Serial for saving
	private static final long serialVersionUID = -1945973919965220808L;
	//Properties file
	private Properties prop;
	
	//Main datamember
	private PatronDB db;

	public PatronDBLogic() {
		
		//db = load();
		//For testing
		
		if (db == null) {
			db = new PatronDB();
			db.importFromFile(new File("C:/Users/Marcus/Desktop/patronTest.txt"));
		}
	
		prop = new Properties();
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
	public void addPatronToDB(String first, String last, String email, String dob, String anniv) {
		Patron toAdd = new Patron(first, last, dob, email, anniv);
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
	
	/**
	 * This method removes the patron given the email.
	 * @param email
	 */
	public void remove(String email) {
		db.remove(email);
	}
	
	/**
	 * This method edits the selected patron.
	 * @param first the new first name
	 * @param last the new last name
	 * @param dob the new birthday
	 * @param email the email to find
	 */
	public void edit(String first, String last, String dob, String email) {
		db.edit(email, first, last, dob);
	}
	
	/**
	 * This method saves the state of the db logic.
	 */
	public void save() {
		FileOutputStream out = null;
		ObjectOutputStream ob = null;
		try {
			out = new FileOutputStream("C:/Users/Marcus/git/EmailProgram/EmailProgram/resources/databaseobject.txt");
			ob = new ObjectOutputStream(out);
			ob.writeObject(db);
			ob.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method loads the state of the db logic.
	 */
	public PatronDB load() {
		PatronDB toLoad = null;
		FileInputStream in = null;
		ObjectInputStream ob = null;
		try {
			in = new FileInputStream("C:/Users/Marcus/git/EmailProgram/EmailProgram/resources/databaseobject.txt");
			ob = new ObjectInputStream(in);
			toLoad = (PatronDB) ob.readObject();
			ob.close();
			return toLoad;
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}

	}
	
	/**
	 * This method creates a tab-delimited backup copy of the database
	 * as a file.
	 * @param fileName the name of the file for the backup
	 */
	public File createBackUp(String fileName) {
		String fileNameAndExtension = fileName + ".txt";
		File file = new File(fileNameAndExtension);
		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
			String toSave = createBackUpString();
			writer.println(toSave);
			writer.close();
			return file;
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * This method creates a backup string to be written to a file.
	 * @return backup the list of names written as a backup
	 */
	public String createBackUpString() {
		StringBuffer backup = new StringBuffer();
		for (int i = 0; i < db.getSize(); i++) {
			backup.append(db.get(i).getLast());
			backup.append("\t");
			backup.append(db.get(i).getFirst());
			backup.append("\t");
			backup.append(db.get(i).getPatronEmail());
			backup.append("\t");
			backup.append(db.get(i).getDOB().toString());
			backup.append("\t");
			backup.append(db.get(i).getPatronSince().toString());
			backup.append("\t");
			backup.append(db.get(i).getAnniv().toString());
			backup.append(System.getProperty("line.separator"));
		}
		return backup.toString();
	}

}
