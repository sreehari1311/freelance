package com.freelance.project.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.freelance.bid.service.Bid;
@JsonInclude(Include.NON_NULL)
public class Project {

	private Long id;
	private String title;
	private String description;
	private Double budget;
	private String lastBidSubmissionDate;
	private String submissionDate;
	private String updateDate;
	private Bid minimumBid;
	private String userId;
	private Short isOpen;
	
	public Short getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Short isOpen) {
		this.isOpen = isOpen;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	 
	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public Bid getMinimumBid() {
		return minimumBid;
	}
	public void setMinimumBid(Bid minimumBid) {
		this.minimumBid = minimumBid;
	}
	public String getLastBidSubmissionDate() {
		return lastBidSubmissionDate;
	}
	public void setLastBidSubmissionDate(String lastBidSubmissionDate) {
		this.lastBidSubmissionDate = lastBidSubmissionDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	 
	
	
	
}
