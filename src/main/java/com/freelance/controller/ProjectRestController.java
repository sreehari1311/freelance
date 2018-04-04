package com.freelance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.Response;
import com.freelance.project.service.Project;
import com.freelance.project.service.ProjectService;
import com.freelance.util.Freelance;

@RestController
@RequestMapping(Freelance.API_VERSION)
public class ProjectRestController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(method = RequestMethod.POST, value = { "/projects" }, produces = "application/json")
	public Response<Project> createProject(@Valid @RequestBody Project project) {
 		return projectService.createProject(project);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = { "/projects/{projectId}" }, produces = "application/json")
	public Response<Project> updateProject(@Valid @RequestBody Project project,@PathVariable(value = "projectId") Long projectId) {
		project.setId(projectId);
 		return projectService.updateProject(project);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = { "/projects/{projectId}" }, produces = "application/json")
	public Response<Project> deleteProject(@PathVariable(value = "projectId") Long projectId) {
 		return projectService.deleteProject(projectId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/projects/{projectId}" }, produces = "application/json")
	public Response<Project> getProject(@PathVariable(value = "projectId") Long projectId) {
 		return projectService.findProjectById(projectId);
	}
}
