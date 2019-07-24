package com.ats.gfplsecurity.repository.duty;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.TaskDoneWeightCalculation;

public interface TaskDoneWeightCalculationRepo extends JpaRepository<TaskDoneWeightCalculation, Integer> {
	
	@Query(value = "SELECT UUID() as id,h.duty_weight AS total, COALESCE( ( SELECT SUM(d.task_weight) FROM t_task_done_detail d "
			+ "WHERE d.del_status = 1 AND d.task_status = 1 AND d.task_done_header_id = :headerId ), 0 ) AS done_wt, "
			+ "concat(round(( COALESCE( ( SELECT SUM(d.task_weight) FROM t_task_done_detail d WHERE d.del_status = 1 AND "
			+ "d.task_status = 1 AND d.task_done_header_id = :headerId ), 0 )/h.duty_weight * 100 ),0),'%') "
			+ "AS percentage FROM t_task_done_header h WHERE h.del_status = 1 AND h.task_done_header_id = :headerId ", nativeQuery = true)
	TaskDoneWeightCalculation getTaskCalculation(@Param("headerId") int headerId);

	

}
