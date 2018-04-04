package com.freelance.project.service;

import java.util.Date;
import java.util.List;

import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.freelance.Response;
import com.freelance.bid.service.Bid;
import com.freelance.bid.service.BidService;

import com.freelance.project.notification.NotificationService;
import com.freelance.project.service.dao.ProjectDAO;
import com.freelance.project.service.dao.ProjectEntity;
import com.freelance.util.ErrorCodes;
import com.freelance.util.FreelanceUtils;
import com.freelance.util.NotFoundException;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private BidService bidService;
	@Autowired
	private NotificationService notificationService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Response<Project> createProject(Project project) {
		return this.persistEntity(project);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Response<Project> updateProject(Project project) {
		if(projectDAO.findOne(project.getId()) == null){
			throw new NotFoundException();
		}
		return this.persistEntity(project);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Response<Project> deleteProject(Long projectId) {
		ProjectEntity projectEntity = projectDAO.findOne(projectId);
		if(projectEntity == null){
			throw new NotFoundException();
		}
	    projectDAO.delete(projectEntity);
		return new Response<>((Project)FreelanceUtils.copyProperties(projectEntity, new Project()));
	}
	private Response<Project> persistEntity(Project project){
		ProjectEntity projectEntity = new ProjectEntity();
		FreelanceUtils.copyProperties(project, projectEntity);
		Date lastDate = FreelanceUtils.getDateTime(project.getLastBidSubmissionDate());
		if (lastDate == null) {
			return new Response<Project>(ErrorCodes.ERROR, "Invalid Date format");
		}
		projectEntity.setLastBidDate(lastDate);
		try {
			projectEntity = projectDAO.save(projectEntity);
		} catch (Exception ex) {
			logger.error("Transaction failed while saving  New Project");
			logger.error(ex.getMessage());
			throw new TransactionException(ErrorCodes.PROJECT_NOT_SAVED);
		}
		return new Response<Project>((Project) FreelanceUtils.copyProperties(projectEntity, new Project()));
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Response<Project> findProjectById(Long projectId) {
		ProjectEntity projectEntity = this.getProjectById(projectId);
		if (projectEntity == null) {
			throw new NotFoundException("Project Id Not found");
		}
		Project projectResponse = new Project();
		FreelanceUtils.copyProperties(projectEntity, projectResponse);
		Response<Bid> minimumBidResponse = bidService.getMinimumBidByProject(projectId);
		if(minimumBidResponse != null){
			projectResponse.setMinimumBid(minimumBidResponse.getResponse());
		}
		
		return new Response<Project>(projectResponse);
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	private ProjectEntity getProjectById(Long projectId) {
		return projectDAO.findOne(projectId);

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public boolean isValiProjectAndDate(Long projectId) {
		ProjectEntity projectEntity = projectDAO.getValidProject(projectId);
		if (projectEntity == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public boolean notifyAllBidWinners() {
		try{
		List<ProjectEntity> projectEntityList = projectDAO.getAllExpiredBids();

		for (ProjectEntity projectEntity : projectEntityList) {
			Project project = new Project();
			FreelanceUtils.copyProperties(projectEntity, project);
			Response<Bid> minBidResponse = bidService.getMinimumBidByProject(project.getId());
			if (minBidResponse != null) {
				Bid minimumBid = minBidResponse.getResponse();
				if (notificationService.sendNotification(project, minimumBid)) {
					projectEntity.setIsOpen(new Short("1"));
					projectDAO.save(projectEntity);
				}

			} else {

			}

		}
		}catch(Exception ex){
			logger.debug("Exception while anouncing result");
			//TODO send email notification to team that notification has failed
			throw new RuntimeException("Error while anouncing result");
		}
		return true;
	}

	

}
