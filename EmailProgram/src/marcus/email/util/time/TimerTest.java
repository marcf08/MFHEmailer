package marcus.email.util.time;

import org.joda.time.DateTimeZone;

public class TimerTest {

	public static void main(String[] args) {
		System.out.println(DateTimeZone.getAvailableIDs());
		Timer t = new Timer();
		//System.out.println(t.getCurrentTimeAndDate());
		System.out.println(t.getCurrentTime());
		System.out.println(t.getCurrentTimeAndDate());
	}

}
