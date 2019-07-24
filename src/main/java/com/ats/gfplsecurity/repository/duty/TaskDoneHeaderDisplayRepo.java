package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.TaskDoneHeaderDisplay;

public interface TaskDoneHeaderDisplayRepo extends JpaRepository<TaskDoneHeaderDisplay, Integer> {

	@Query(value = "SELECT t.*,d.duty_name,s.shift_name,s.shift_from_time,s.shift_to_time,s.no_of_hr FROM "
			+ "t_task_done_header t, m_duty_header d,m_shift s WHERE t.del_status=1 AND t.duty_id=d.duty_id AND "
			+ "d.shift_id=s.shift_id AND t.emp_id=:empId AND t.task_date=:date ", nativeQuery = true)
	List<TaskDoneHeaderDisplay> getTaskDoneHeaderByEmp(@Param("empId") int empId, @Param("date") String date);

}