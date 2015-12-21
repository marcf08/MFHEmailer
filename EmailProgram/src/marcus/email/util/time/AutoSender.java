package marcus.email.util.time;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

import static org.quartz.SimpleScheduleBuilder.*;



/**
 * This class automatically sends the birthday emails.
 * @author Marcus
 */
public class AutoSender {
	 private Scheduler schedulerBirthdays;
	 private Scheduler schedulerAnniversaries;
	 private CronScheduleBuilder mainSchedule;
	 private CronTrigger dateTrigger;
	 private CronExpression daily;
	 private JobDetail testJob;

	/**
	 * The default constructor instantiates but does not start the
	 * scheduler.
	 */
	public AutoSender() {
		//Quick and dirty log4 configuration
		org.apache.log4j.BasicConfigurator.configure();
		try {
			daily = new CronExpression("* * * * * ?");
			//Valid expression every day at noon: "0 0 12 * * ?"
			mainSchedule = CronScheduleBuilder.cronSchedule(daily);
			schedulerBirthdays = StdSchedulerFactory.getDefaultScheduler();
			//schedulerAnniversaries = StdSchedulerFactory.getDefaultScheduler();
			dateTrigger = newTrigger().withSchedule(mainSchedule).build();
			testJob = newJob(TestJobClass.class).storeDurably(true).withIdentity("Test").build();
			schedulerBirthdays.addJob(testJob, true);
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * These methods stop and start the sender from sending birthday emails.
	 * @return true if the scheduler was paused and false otherwise
	 */
	public boolean stopSendingBirthdays() {
		try {
			schedulerBirthdays.pauseAll();
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public boolean resumeSendingBirthdays() {
		try {
			schedulerBirthdays.start();
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	

	/**
	 * These methods stop and start the sender from sending anniversary emails.
	 * @return true if the scheduler was paused and false otherwise
	 */
	public boolean stopSendingAnniv() {
		try {
			schedulerAnniversaries.pauseAll();
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public boolean resumeSendingAnniv() {
		try {
			schedulerAnniversaries.resumeAll();;
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
}
