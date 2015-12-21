package marcus.email.util.time;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class TestJobClass implements Job { 
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Beep");
	}

}
