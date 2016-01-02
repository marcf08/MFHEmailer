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
	private JobDetail jobBirthdays;
	private JobDetail jobAnniv;
	private JobDataMap dataMap;
	private EmailTemplate birthdayTemplate;
	private EmailTemplate annivTemplate;
	
	private String theExpression = "0 * 14 * * ?";

	public final String SUBJ = "Subject";
	public final String TEMP_BIRTH = "Birthday Template";
	public final String TEMP_ANNIV = "Anniversary Template";
	public final String resultsBirth = "RESULTS_BIRTH";
	public final String resultsAnniv = "RESULTS_ANNIV";



	/**
	 * The default constructor instantiates but does not start the
	 * scheduler given a year and month as parameters.
	 */
	public AutoSender() {
		//Quick and dirty log4j configuration
		org.apache.log4j.BasicConfigurator.configure();
		//this.birthdayTemplate = birthdayTemplate;
		//this.annivTemplate = annivTemplate;
		//Valid expression for every day at noon: "0 0 12 * * ?"
		dataMap = new JobDataMap();

	}
	
	/**
	 * This method instantiates the data members and readies the sender.
	 */
	public void runBirthdaySetUp() {
		try {
			schedulerBirthdays = StdSchedulerFactory.getDefaultScheduler();
			jobBirthdays = JobBuilder.newJob(PrepareBirthdays.class).storeDurably(true).withIdentity("Birthday").usingJobData(dataMap).build();
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("crontrigger","crontriggergroup1")					
					.withSchedule(CronScheduleBuilder.cronSchedule(theExpression))
					.build();
			schedulerBirthdays.scheduleJob(jobBirthdays, cronTrigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method instantiates data members and readies the sender.
	 */
	public void runAnnivSetUp() {
		try {
			schedulerAnniversaries = StdSchedulerFactory.getDefaultScheduler();
			jobAnniv = JobBuilder.newJob(PrepareAnniv.class).storeDurably(true).withIdentity("Anniversary").usingJobData(dataMap).build();
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("crontrigger","crontriggergroup1")					
					.withSchedule(CronScheduleBuilder.cronSchedule("theExpression"))
					.build();
			schedulerAnniversaries.scheduleJob(jobAnniv, cronTrigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method sets the birthday template.
	 * @param template the chosen birthday template
	 */
	public void setBirthdayTemplate(EmailTemplate birthdayTemplate) {
		this.birthdayTemplate = birthdayTemplate;
		dataMap.put(TEMP_BIRTH, birthdayTemplate);	
	}
	
	/**
	 * This method sets the anniversary template.
	 */
	public void setAnnivTemplate(EmailTemplate annivTemplate) {
		dataMap.put(TEMP_ANNIV, annivTemplate);
		this.annivTemplate = annivTemplate;
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
			schedulerAnniversaries.start();
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	/**
	 * This method gets the results of sending birthday emails.
	 * @return the results of the birthday send operation.
	 */
	public String getBirthdayResults() {
		return (String) dataMap.get("RESULTS_BIRTH");
	}
	
	/**
	 * This method gets the results of sending anniversary emails.
	 * @return the results of the anniversary send operation.
	 */
	public String getAnnivResults() {
		return (String) dataMap.getString("RESULTS_ANNIV");
	}

}
