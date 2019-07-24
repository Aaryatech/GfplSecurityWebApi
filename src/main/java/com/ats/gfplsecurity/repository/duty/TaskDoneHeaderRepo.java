package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.duty.TaskDoneHeader;

@Repository
public interface TaskDoneHeaderRepo extends JpaRepository<TaskDoneHeader, Integer> {

	List<TaskDoneHeader> findAllByDelStatus(int i);

	TaskDoneHeader findByTaskDoneHeaderId(int taskDoneHeaderId);

	TaskDoneHeader findByTaskDateAndDutyIdAndEmpId(String date, int dutyId, int empId);

	// List<TaskDoneHeader> findByEmpIdAndTaskDate(@Param("empId") int empId,
	// @Param("date") String date);

	@Transactional
	@Modifying
	@Query("update TaskDoneHeader set task_complete_wt=:weight, completion_percent=:percent  WHERE task_done_header_id=:headerId")
	int updateWtAndPercent(@Param("headerId") int headerId, @Param("weight") float weight, @Param("percent") String percent);

	
	List<TaskDoneHeader> findByTaskDateAndEmpIdAndDelStatus(String date,int empId,int i);
	
}