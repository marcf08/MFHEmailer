package marcus.email.util.time;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;

import marcus.email.util.EmailTemplate;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
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
	 private JobDetail mainJob;
	 private JobDataMap dataMap;
	 private EmailTemplate template;
	 private String subject;
	 private String defaultSubject = "Happy Birthday from the Marshall Free House!";
			 
	public final static String SUBJ = "Subject";
	 public final static String TEMP_HTML = "The Template";
	 
	 
	 
	/**
	 * The default constructor instantiates but does not start the
	 * scheduler given a year and month as parameters.
	 */
	public AutoSender(EmailTemplate template) {
		//Quick and dirty log4j configuration
		org.apache.log4j.BasicConfigurator.configure();
		this.template = template;
		try {
			//Valid expression for every day at noon: "0 0 12 * * ?"
			dataMap = new JobDataMap();
			dataMap.put(TEMP_HTML, template);			
			
			schedulerBirthdays = StdSchedulerFactory.getDefaultScheduler();
			mainJob = JobBuilder.newJob(PrepareBirthdays.class).storeDurably(true).withIdentity("Test").usingJobData(dataMap).build();
			
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("crontrigger","crontriggergroup1")					
					                             .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
					                             .build();
			schedulerBirthdays.scheduleJob(mainJob, cronTrigger);
			
			
		} catch (SchedulerException e) {
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
			schedulerAnniversaries.resumeAll();
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
}
