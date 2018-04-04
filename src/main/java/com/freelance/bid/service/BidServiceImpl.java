package com.freelance.bid.service;
 
import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.freelance.Response;
import com.freelance.bid.service.dao.BidDAO;
import com.freelance.bid.service.dao.BidEntity;
import com.freelance.project.service.ProjectService;
import com.freelance.util.BadRequestException;
import com.freelance.util.ErrorCodes;
import com.freelance.util.FreelanceUtils;
import com.freelance.util.NotFoundException;
/*
 * Bid Service Implimentation
 */
@Service
@Transactional
public  class BidServiceImpl implements BidService {

	private static final Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);
	@Autowired
	private BidDAO bidDAO;
	@Autowired
	private ProjectService projectService;
	
	/*
	 * Method for creating bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#createBid(com.freelance.bid.service.Bid)
	 */
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED) 
	public Response<Bid> createBid(Bid bid) {
		return this.persist(bid);
	}
	
	/*
	 * method for update bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#updateBid(com.freelance.bid.service.Bid)
	 */
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED) 
	public Response<Bid> updateBid(Bid bid) {
		 if( bidDAO.findOne(bid.getId()) == null){
			 throw new NotFoundException();
		 }
		 return this.persist(bid);
	}
	
	
	/*
	 * method for delete bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#deleteBid(java.lang.Long)
	 */
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED) 
	public Response<Bid> deleteBid(Long bidId){
		BidEntity bidEntity = bidDAO.findOne(bidId);
		if(bidEntity == null){
			logger.debug("Bid not found");
			throw new NotFoundException();
		}
		try{
			bidDAO.delete(bidEntity);
		}catch(Exception ex){
			logger.error("Transaction failed while udpating  Bid");
			logger.error(ex.getMessage());
			throw new TransactionException(ErrorCodes.BID_NOT_SAVED);
		}
		return new Response<>((Bid)FreelanceUtils.copyProperties(bidEntity, new Bid()));
	}
	
	/*
	 * get Minimum Bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#getMinimumBidByProject(java.lang.Long)
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public Response<Bid> getMinimumBidByProject(Long projectId) {
		Bid bid = new Bid();
		BidEntity bidEntity = bidDAO.findByMinBid(projectId);
		if(bidEntity == null){
			return null;
		}else{
			FreelanceUtils.copyProperties(bidEntity ,bid);
			return new Response<Bid>(bid);
		}
		
	}
	/**
	 * common persist for create and update
	 * @param bid
	 * @return
	 */
	private Response<Bid> persist(Bid bid){
		if(projectService.isValiProjectAndDate(bid.getProjectId())){
			throw new BadRequestException("Project is invalid");
		}
		BidEntity bidEntity = new BidEntity();
		FreelanceUtils.copyProperties(bid, bidEntity);
		try{
			bidEntity = bidDAO.save(bidEntity);
		}catch(Exception ex){
			logger.error("Transaction failed while saving  Bid");
			logger.error(ex.getMessage());
			throw new TransactionException(ErrorCodes.BID_NOT_SAVED);
		}
		
		FreelanceUtils.copyProperties(bidEntity, bid);
		return new Response<Bid>(bid);
	}
	
	 

}
