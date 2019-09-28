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
	@Query(value = "UPDATE m_visitor SET del_status=0 WHERE visitor_id=:visitorId", nativeQuery = true)
	int deleteVistor(@Param("visitorId") int visitorId);

	VisitorMaster findByVisitorIdAndDelStatus(int visitorId, int i);

}
