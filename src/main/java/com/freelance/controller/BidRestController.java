package com.freelance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.Response;
import com.freelance.bid.service.Bid;
import com.freelance.bid.service.BidService;
import com.freelance.util.Freelance;

@RestController
@RequestMapping(Freelance.API_VERSION)
public class BidRestController {

	@Autowired
	private BidService bidService;
	
	@RequestMapping(method = RequestMethod.POST, value = { "/bids" }, produces = "application/json")
	public Response<Bid> createBid(@Valid @RequestBody Bid bid) {
 		return bidService.createBid(bid);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = { "/bids/{bidId}" }, produces = "application/json")
	public Response<Bid> updateBid(@Valid @RequestBody Bid bid,@PathVariable(value = "bidId") Long bidId) {
		bid.setId(bidId);
 		return bidService.updateBid(bid);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = { "/bids/{bidId}" }, produces = "application/json")
	public Response<Bid> updateBid(@PathVariable(value = "bidId") Long bidId) {
		 
 		return bidService.deleteBid(bidId);
	}
}
