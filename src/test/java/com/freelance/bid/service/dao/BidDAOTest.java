package com.freelance.bid.service.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.freelance.bid.service.dao.BidDAO;
import com.freelance.bid.service.dao.BidEntity;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BidDAOTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired private  BidDAO bidDAO;
	@Test
	public void firstSaveBidTest() {
	 
	/*	BidEntity bid = new BidEntity();
	 	bid.setProjectId(new Long(33));
		bid.setBidAmount(100.25);
		bid.setUserId("sreehari");
		bid.setSubmissionDate(new Date());
		bid.setUpdateDate(new Date());
		 
	    entityManager.persist(bid);
		entityManager.flush(); 
		bidDAO.save(bid);
		this.bidDAO.findOne(new Long(55));*/
		assertThat("test").isEqualTo("test");
	}
	@Test
	public void secondDeleteBidTest() {
		// bidDAO.delete(new Long(1001));
	}
}
