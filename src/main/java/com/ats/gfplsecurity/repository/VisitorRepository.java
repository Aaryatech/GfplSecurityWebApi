package com.ats.gfplsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer>{
	
	List<Visitor> findAllByDelStatus(int i);
	
	Visitor findByGatepassVisitorId(int gatepassVisitorId);

	@Transactional
	@Modifying
	@Query("update Visitor set visit_status=:status, gate_id=:gateId WHERE gatepass_visitor_id=:gatepassVisitorId ")
	int updateGatepassStatus(@Param("gatepassVisitorId") int gatepassVisitorId,@Param("status") int status,@Param("gateId") int gateId);
 
	@Transactional
	@Modifying
	@Query("update Visitor set visit_status=:status,visit_out_time=:outTime, total_time_difference=:timeDiff WHERE gatepass_visitor_id=:gatepassVisitorId")
	int updateGatepassStatus(@Param("gatepassVisitorId") int gatepassVisitorId,@Param("status") int status,@Param("outTime") String outTime,@Param("timeDiff") String timeDiff);
 

	
}
