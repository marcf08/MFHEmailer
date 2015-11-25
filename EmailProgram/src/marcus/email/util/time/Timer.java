package marcus.email.util.time;

import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * This static class is the main timer used by the GUI.
 * @author Marcus
 *
 */
public final class Timer {
	
	//These simple data members control the main features of the time class.
	private LocalTime localTime;
	private LocalDate localDate;
	private DateTimeZone timeZone;
	
	/**
	 * The null constructor simple sets the time zone and time to
	 * default values.
	 */
	public Timer() {
		localTime = new LocalTime();
		timeZone = DateTimeZone.getDefault();
		localDate = new LocalDate(timeZone);
	}
	
	/**
	 * This constructor allows the timer to be constructed given a time zone.
	 * @param timeZone
	 */
	public Timer(String id) {
		timeZone = DateTimeZone.forID(id);
		localTime = new LocalTime(timeZone);
	}
	
	/**
	 * This method gets a list of acceptable time zones.
	 * @return a set of the available IDs
	 */
	public  Set<String> getTimeZones() {
		return DateTimeZone.getAvailableIDs();
	}
	
	/**
	 * This methods gets the current time and date for use by the client
	 * (primarily the GUI.) 
	 */
	public String getCurrentTimeAndDate() {
		return localDate.toString() + "  " + localTime.toString();
	}
	
	/**
	 * This method returns the local time. If standard time is enabled,
	 * this method returns the standard time in traditional format. Otherwise,
	 * it returns it in 24-hour format.
	 * @return the local time
	 */
	public String getCurrentTime() {
		return localTime.toString();
	}
	
	/**
	 * This method returns the local date. 
	 * @return the local date in yyyy/mm/dd format.
	 */
	public String getLocalDate() {
		return localDate.toString();
	}

}
