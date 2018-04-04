package com.freelance.project.service;

import com.freelance.Response;

public interface ProjectService {

	public Response<Project> createProject(Project project);
	public Response<Project> updateProject(Project project);
	public Response<Project> deleteProject(Long projectId);
	public Response<Project> findProjectById(Long projectId);
	public boolean isValiProjectAndDate(Long projectId);
	public boolean notifyAllBidWinners();
}
