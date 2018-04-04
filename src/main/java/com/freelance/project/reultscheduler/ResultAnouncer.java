package com.freelance.project.reultscheduler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.freelance.project.service.ProjectService;
/**
 * Quarts Job executes and check every minute any result anouncement is there
 * @author sreehari
 *
 */
public class ResultAnouncer implements Job {
	
	private static final Logger logger = LoggerFactory.getLogger(ResultAnouncer.class);
	/*
	 * å(non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
	
		SchedulerContext schedulerContext = null;
        try {
            schedulerContext = context.getScheduler().getContext();
            ProjectService projectService = (ProjectService)schedulerContext.get("projectService");
            projectService.notifyAllBidWinners();
        } catch (SchedulerException e1) {
        	logger.debug(" Scheduler job is failing"+e1);
             
        }
        
	}
} 