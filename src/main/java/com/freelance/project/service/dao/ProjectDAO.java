package com.freelance.project.service.dao;
 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDAO extends JpaRepository<ProjectEntity, Long> {

	@Query("SELECT v FROM " +
		       "    ProjectEntity v " +
		       "where v.id=?1 and v.lastBidDate<CURRENT_TIMESTAMP")
	public  ProjectEntity getValidProject(Long projectId);
	
	@Query("SELECT v FROM " +
		       "    ProjectEntity v " +
		       "where v.lastBidDate<=CURRENT_TIMESTAMP and (v.isOpen=0 or v.isOpen is NULL) ")
	public List<ProjectEntity> getAllExpiredBids();

	
}
