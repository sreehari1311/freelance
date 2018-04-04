package com.freelance.project.notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.freelance.bid.service.Bid;
import com.freelance.project.service.Project;
@Service
public class MailNotificationServiceImpl implements NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(MailNotificationServiceImpl.class);
	@Override
	public boolean sendNotification(Project project,Bid lawestBid) {
		if(lawestBid == null){
			return this.sendMailToProjectOwner(project);
		}
		return this.sendMailToProjectOwnerandLawestBid(project, lawestBid);
	}
	
	private boolean sendMailToProjectOwnerandLawestBid(Project project,Bid lawestBid){
		boolean allOk = true;
		logger.debug("Sending mail to Project Owner and Bid winner");
		return allOk;
	}
	private boolean sendMailToProjectOwner(Project project){
		boolean allOk = true;
		logger.debug("Sending mail to Project Owner saying no one bid the project");
		return allOk;
	}
}
