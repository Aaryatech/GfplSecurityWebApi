package com.ats.gfplsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.VisitorMaster;

public interface VisitorMasterRepository extends JpaRepository<VisitorMaster, Integer>{

	List<VisitorMaster> findByDelStatus(int delStatus);

	@Transactional
	@Modifying
	@Query(value = "UPDATE VisitorMaster SET delStatus=0 WHERE visitorId=:visitorId", nativeQuery = true)
	int deleteVistor(@Param("visitorId") int visitorId);

	VisitorMaster findByVisitorIdAndDelStatus(int visitorId, int i);

}
