package marcus.email.util.time;

import java.util.ArrayList;

import marcus.email.GUI.EmailerClientGUI;
import marcus.email.database.Patron;

/**
 * Send model combines the 
 * @author Marcus
 *
 */
public class SendModel {
	//This is the main data member of the class
	private Timer mainTimer;
		
	/**
	 * The null constructor starts the timer.
	 */
	public SendModel() {
		mainTimer = new Timer();
	}
	
	/**
	 * This method gets the current time. It's mainly for use
	 * by the GUI.
	 * @return the current time 
	 */
	public String getCurrentTime() {
		return mainTimer.getCurrentTimeAndDate();
	}
	
	/**
	 * This method processes a list of patrons and returns a list of those that match a given
	 * year and month.
	 * @param year the year of the birthday
	 * @param month the month of the birthday
	 */
	public ArrayList<Patron> prepareBirthdayBatch(int year, int month) {
		ArrayList<Patron> patrons = new ArrayList<Patron>();
		for (int i = 0; i < EmailerClientGUI.dblogic.getSize(); i++) {
			int currentPatronYear = getYear(EmailerClientGUI.dblogic.getPatronFromDB(i).getDOB());
			int currentPatronMonth = getMonth(EmailerClientGUI.dblogic.getPatronFromDB(i).getDOB());
			if (currentPatronYear == year && currentPatronMonth == month) {
				patrons.add(patrons.get(i));
			}
		}
		return patrons;
	}
	
	/**
	 * This method gets the month from the current patron.
	 * @param dob the date of birth string
	 * @return the month or -1 if there's an error for number format exceptions
	 * or out of range dates
	 */
	private int getMonth(String dob) {
		try {
			Integer month = Integer.parseInt(dob.substring(5, 7));
			if (month < 1 || month > 12) {
				return -1;
			}
			return month;
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}
	
	/**
	 * This method gets the year from the current patron.
	 * @param dob the date of birth string
	 * @return the year or -1 if there's an error for number format exceptions
	 * or out of range dates
	 */
	private int getYear(String dob) {
		try {
			Integer year = Integer.parseInt(dob.substring(0, 4));
			if (year  < 1900 || year > 2015) {
				return -1;
			}
			return year;
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}


}
