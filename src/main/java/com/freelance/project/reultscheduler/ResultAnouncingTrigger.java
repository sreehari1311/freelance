package com.freelance.project.reultscheduler;
import java.util.Calendar;
import java.util.Date;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freelance.project.service.ProjectService;
import com.freelance.util.Freelance;
/**
 * Scheduler Trigger which runs every minutes check any result is need to be anounced
 * @author sreehari
 *
 */
@Component
public class ResultAnouncingTrigger {
	@Autowired
	private ProjectService projectService;
	
	public void initTrigger() throws SchedulerException{
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(ResultAnouncer.class).withIdentity("resultAnouncer", "freelance").build();
		SimpleScheduleBuilder triggerBuilder = SimpleScheduleBuilder.simpleSchedule();
		triggerBuilder.withIntervalInMinutes(1).repeatForever();
		 
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("resultTrigger", "freelanceGroup")
			    .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 3000))
			    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(Freelance.RESULT_SCHEDULER_INTERVAL)
			    		.repeatForever()).build();
		 
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.getContext().putIfAbsent("projectService", projectService);
		scheduler.start();
	}

	 
} 