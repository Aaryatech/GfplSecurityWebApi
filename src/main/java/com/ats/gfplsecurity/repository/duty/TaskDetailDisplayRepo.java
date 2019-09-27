package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.duty.TaskDetailDisplay;

@Repository
public interface TaskDetailDisplayRepo extends JpaRepository<TaskDetailDisplay, Integer> {

/*	@Query(value = " SELECT t.*, d.duty_name ,CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as "
			+ "created_by_name  FROM m_task_detail t, emp_info e, m_duty_header d WHERE "
			+ "t.del_status=1 AND t.duty_id=:dutyId AND e.emp_id=t.created_by AND d.duty_id=t.duty_id ", nativeQuery = true)
	List<TaskDetailDisplay> getAllTaskByDutyHeaderId(@Param("dutyId") int dutyId);*/

	
	@Query(value = " SELECT t.*, d.duty_name ,'' as created_by_name  FROM m_task_detail t, m_duty_header d WHERE t.del_status=1 AND t.duty_id=:dutyId AND d.duty_id=t.duty_id ", nativeQuery = true)
	List<TaskDetailDisplay> getAllTaskByDutyHeaderId(@Param("dutyId") int dutyId);

}
