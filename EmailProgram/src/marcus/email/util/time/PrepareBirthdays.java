package marcus.email.util.time;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.joda.time.LocalDate;
import org.quartz.Calendar;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.TimeOfDay;
import org.quartz.simpl.SimpleTimeBroker;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

import marcus.email.GUI.EmailerClientGUI;
import marcus.email.GUI.FileConstants;
import marcus.email.database.Patron;
import marcus.email.util.EmailTemplate;
/**
 * This class creates the birthday list.
 * @author Marcus
 *
 */
public class PrepareBirthdays implements Job{
	/**
	 * Required by the Job interface, this method creates the birthday list.
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//When the job executes, we need a means to determine the year and month
		//of the current day.
		int [] timeAndDay = getDayAndMonth();
		int currentDay =  timeAndDay[0];
		int currentMonth = timeAndDay[1];

		//Build the list of patrons
		ArrayList<Patron> patrons = new ArrayList<Patron>();
		for (int i = 0; i < EmailerClientGUI.dblogic.getSize(); i++) {
			int currentPatronMonth = getMonth(EmailerClientGUI.dblogic.getPatronFromDB(i).getDOB());
			int currentPatronDay = getDay(EmailerClientGUI.dblogic.getPatronFromDB(i).getDOB());
			if (currentPatronDay == currentDay && currentPatronMonth == currentMonth) {
				patrons.add(patrons.get(i));
			}
		}
		
		SendGrid send = getSend();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		EmailTemplate template = (EmailTemplate) dataMap.get(AutoSender.TEMP_HTML);
		dataMap.put("RESULTS", sendEmails(patrons, template, send));
	}

	/**
	 * This method sends the emails given a list of patrons and the template to be used by the auto
	 * sender.
	 */
	public String sendEmails(ArrayList<Patron> list, EmailTemplate templateToSend, SendGrid send) {
		StringBuilder result = new StringBuilder();
		for (int j = 0; j < list.size(); j++) {
			Email email = new Email();
			String personalizedHtmlEmail = templateToSend.addPatronInformation(list.get(j));
			email.setSubject(templateToSend.getFormattedSubject(list.get(j)));
			email.addTo(list.get(j).getPatronEmail());
			email.setHtml(personalizedHtmlEmail);
			try {
				SendGrid.Response response = send.send(email);
				result.append(response);
			} catch (SendGridException e) {
				//Move on if there's a failure, but record the message
				result.append(e.getMessage());
				j++;
			}

		}
		return result.toString();
	}



	/**
	 * This method gets the time and returns it in the form of an integer-based array.
	 */
	@SuppressWarnings("deprecation")
	public int [] getDayAndMonth() {
		int [] timeAndDate = new int[2];
		SimpleTimeBroker today = new SimpleTimeBroker();
		Date day = today.getCurrentTime();
		timeAndDate[0] = day.getDay();
		timeAndDate[1] = day.getMonth();
		return timeAndDate;
	}

	/**
	 * This method loads the properties file used to get the API Key and Send Grid instance.
	 * @return the send grid instance from the properties file
	 */
	private SendGrid getSend() {
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(new File(FileConstants.CONFIG_LOC));
			prop.load(fis);
			SendGrid send = new SendGrid(prop.getProperty(FileConstants.CONFIG_API));
			return send;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method gets the month from the current patron.
	 * @param dob the date of birth string
	 * @return the month or -1 if there's an error for number format exceptions
	 * or out of range dates
	 */
	private int getMonth(String date) {
		try {
			Integer month = Integer.parseInt(date.substring(5, 7));
			if (month < 1 || month > 12) {
				return -1;
			}
			return month;
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	/**
	 * This method retrieves the day of the current patron.
	 */
	private int getDay(String dob) {
		try {
			Integer day = Integer.parseInt(dob.substring(8, 10));
			if (day < 1 || day > 31) {
				return -1;
			}
			return day;
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}


}
