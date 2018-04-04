package com.freelance.project.notification;

import com.freelance.bid.service.Bid;
import com.freelance.project.service.Project;

public interface NotificationService {

	public boolean sendNotification(Project Project,Bid lawestBid);
}
