package com.freelance.bid.service;

import com.freelance.Response;
/*
 * Bid Service
 */
public interface BidService {

	/*
	 * Method for creating bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#createBid(com.freelance.bid.service.Bid)
	 */
	public Response<Bid> createBid(Bid bid);
	/*
	 * method for update bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#updateBid(com.freelance.bid.service.Bid)
	 */
	public Response<Bid> updateBid(Bid bid);
	/*
	 * method for delete bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#deleteBid(java.lang.Long)
	 */
	public Response<Bid> deleteBid(Long bidId);
	/*
	 * get Minimum Bid
	 * (non-Javadoc)
	 * @see com.freelance.bid.service.BidService#getMinimumBidByProject(java.lang.Long)
	 */
	public Response<Bid> getMinimumBidByProject(Long projectId);
}
