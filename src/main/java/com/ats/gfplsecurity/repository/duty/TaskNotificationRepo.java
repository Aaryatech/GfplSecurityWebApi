package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.TaskNotification;

public interface TaskNotificationRepo extends JpaRepository<TaskNotification, Integer> {
	
	@Query(value = " select td.task_done_detail_id,td.duty_id,td.ex_var1 as time,th.emp_id,CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as name, td.task_name,e.ex_var3 as token FROM t_task_done_detail td,t_task_done_header th,emp_info e WHERE td.task_done_header_id=th.task_done_header_id AND th.del_status=1 AND e.emp_id=th.emp_id AND th.task_date=:date AND td.ex_int1=1 AND td.ex_int2=0  ", nativeQuery = true)
	List<TaskNotification> getTaskForNotify(@Param("date") String date);
}