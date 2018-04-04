package com.freelance.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.freelance.Response;
import com.freelance.project.service.Project;
import com.freelance.project.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectRestController.class, secure = false)
public class ProjectRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService projectService;
	private Response<Project> newProject = new Response<>();
	private String jsonResponse  = "{\r\n\"response\": {\r\n\"id\": 1,\r\n\"title\": \"testing\",\r\n\"description\": \"testing description1\",\r\n\"budget\": 250.5,\r\n\"lastBidSubmissionDate\": \"03/29/2018 13:50\",\r\n\"userId\": \"sreehari\"\r\n},\r\n\"status\": \"SUCCESS\"\r\n}";
	@Before
	 public void setUp() throws Exception{
		Project project = new Project();
		project.setId(new Long(1));
		project.setBudget(250.5);
		project.setTitle("testing");
		project.setDescription("testing description1");
		project.setLastBidSubmissionDate("03/29/2018 13:50");
		project.setUserId("sreehari");
		newProject.setStatus("SUCCESS");
		newProject.setResponse(project);
	}
	
	@Test
	public void testGetProject(){
		Mockito.when(
				projectService.findProjectById(new Long(1))).thenReturn(newProject);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/projects/1").accept(MediaType.APPLICATION_JSON).content(jsonResponse)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.out.println("Json Response");
			System.out.println(result.getResponse().getContentAsString());
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			JSONAssert.assertEquals(jsonResponse, result.getResponse()
					.getContentAsString(), false);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	@Test
	public void testCreateNewProject(){
		Mockito.when(
				projectService.createProject(newProject.getResponse())).thenReturn(newProject);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/v1/projects").accept(MediaType.APPLICATION_JSON).content(jsonResponse)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.out.println(result.getResponse());
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
