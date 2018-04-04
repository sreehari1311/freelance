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
import com.freelance.bid.service.Bid;
import com.freelance.bid.service.BidService;
import com.freelance.project.service.Project;
import com.freelance.project.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BidRestController.class, secure = false)
public class ProjectBidControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BidService bidService;
	private Bid bid = new Bid();
	private String bidJson="{\r\n\t\"userId\":\"sreehari\",\r\n    \"projectId\":1,\r\n    \"bidAmount\":100.50 \r\n  \r\n}";
 	@Before
	 public void setUp() throws Exception{
		bid.setProjectId(new Long(1));
		bid.setBidAmount(100.3);
		bid.setUserId("sreehari");
		
	}
	
	 
	@Test
	public void testCreateNewBid(){
		Mockito.when(
				bidService.createBid(bid)).thenReturn(new Response<>());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/v1/bids").accept(MediaType.APPLICATION_JSON).content(bidJson)
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
