package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.TaskDoneDetailDisplay;

public interface TaskDoneDetailDisplayRepo extends JpaRepository<TaskDoneDetailDisplay, Integer> {

	@Query(value = "SELECT td.*,t.task_name_eng,t.task_name_mar,t.task_name_hin,t.task_desc_eng,t.task_desc_mar,"
			+ "t.task_desc_hin,t.photo_req,t.remark_req FROM t_task_done_detail td,m_task_detail t "
			+ "WHERE t.del_status=1 AND t.task_id=td.task_id AND td.task_done_header_id=:headerId ", nativeQuery = true)
	List<TaskDoneDetailDisplay> getTaskDoneDetailById(@Param("headerId") int headerId);

}