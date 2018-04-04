package com.freelance.bid.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BidDAO extends JpaRepository<BidEntity, Long> {

	@Query("SELECT v FROM " + "    BidEntity v "
			+ "where v.projectId=?1 and v.bidAmount = ( SELECT MIN(b.bidAmount) FROM BidEntity b  where b.projectId=?1)")
	public BidEntity findByMinBid(Long projectId);
}
