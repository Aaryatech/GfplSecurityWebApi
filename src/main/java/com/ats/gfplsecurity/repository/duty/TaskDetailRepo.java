package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.MaterialGatepassDisplay;
import com.ats.gfplsecurity.model.duty.TaskDetail;

@Repository
public interface TaskDetailRepo extends JpaRepository<TaskDetail, Integer> {

	List<TaskDetail> findAllByDelStatus(int i);

	TaskDetail findByTaskId(int taskId);
	
	@Query(value = "SELECT COALESCE((SELECT SUM(t.task_weight) from m_task_detail t WHERE t.del_status=1 AND t.duty_id=:dutyId),0) as total ", nativeQuery = true)
	float getSumOfTaskWt(@Param("dutyId") int dutyId);   
	
	List<TaskDetail> findAllByDelStatusAndDutyId(int i,int dutyId);

}
