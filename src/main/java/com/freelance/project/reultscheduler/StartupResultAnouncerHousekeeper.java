package com.freelance.project.reultscheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupResultAnouncerHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

  private static final Logger logger = LoggerFactory.getLogger(StartupResultAnouncerHousekeeper.class);
  @Autowired
  private ResultAnouncingTrigger minuteTrigger;
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
	  
	
	  try {
		  logger.debug("*************************** Trigger Scheduling starting *****************");
		  minuteTrigger.initTrigger();
		  logger.debug("*************************** Trigger Started for every minute *****************");
	} catch (SchedulerException e) {
		 
	}
  } 
}