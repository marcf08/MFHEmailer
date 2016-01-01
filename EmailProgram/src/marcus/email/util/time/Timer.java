package marcus.email.util.time;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * This static class is the main timer used by the GUI.
 * @author Marcus
 *
 */
public class Timer {
	
	//These simple data members control the main features of the time class.
	private LocalTime localTime;
	private LocalDate localDate;
	private DateTimeZone timeZone;
	private Date localCurrent;
	private ScheduledExecutorService scheduler;
	
	
	//By default use the system time.
	public Timer() {
		localCurrent = new Date(System.currentTimeMillis());
		scheduler = Executors.newSingleThreadScheduledExecutor();
	}
	
	//Starts this timer
	public void start() {
		Runnable timerUpdate = new Runnable() {
			public void run() {
				localCurrent.setTime(System.currentTimeMillis());
			}
		};
	scheduler.scheduleAtFixedRate(timerUpdate, 0, 100, TimeUnit.MILLISECONDS);
	}
	
	//Retrieves the current time--will appear to be "frozen" if the timer is not started
	public String getTime() {
		return localCurrent.toString();
	}
	
	
	

	
	
	
	
	
	/**
	 * This constructor allows the timer to be constructed given a time zone.
	 * @param timeZone
	 */
	public Timer(String id) {
		timeZone = DateTimeZone.forID(id);
		localTime = new LocalTime(timeZone);
	}

	
	//Returns the current time in String form
	public String getCurrentTime() {
		return localCurrent.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localDate == null) ? 0 : localDate.hashCode());
		result = prime * result + ((localTime == null) ? 0 : localTime.hashCode());
		result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
		return result;
	}

	/**
	 * The equals method compares this timer to another timer.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timer other = (Timer) obj;
		if (localDate == null) {
			if (other.localDate != null)
				return false;
		} else if (!localDate.equals(other.localDate))
			return false;
		if (localTime == null) {
			if (other.localTime != null)
				return false;
		} else if (!localTime.equals(other.localTime))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		return true;
	}
	
	
	

}
