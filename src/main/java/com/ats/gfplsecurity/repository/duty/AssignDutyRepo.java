package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.duty.AssignDuty;

@Repository
public interface AssignDutyRepo extends JpaRepository<AssignDuty, Integer> {

	List<AssignDuty> findAllByDelStatus(int i);

	AssignDuty findByAssignId(int assignId);
	
	AssignDuty findByDutyId(int dutyId);
	
	List<AssignDuty> findAllByDelStatusAndExInt1(int i,int isSchedule);
	
	List<AssignDuty> findAllByDelStatusAndDutyId(int i,int dutyId);
	

	@Transactional
	@Modifying
	@Query("update AssignDuty set notify_time=:notifyTime , emp_ids=:empIds WHERE assign_id=:assignId")
	int updateTimeAndEmpIds(@Param("assignId") int assignId,@Param("notifyTime") String notifyTime,@Param("empIds") String empIds);
	

	

}
