package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.DutyWiseCount;

public interface DutyWiseCountRepo extends JpaRepository<DutyWiseCount, Integer> {

	@Query(value = "SELECT UUID() as id,t_task_done_header.task_done_header_id, m_duty_header.duty_id, "
			+ "m_duty_header.duty_name, SUM(t_task_done_header.duty_weight) as total, "
			+ "SUM(t_task_done_header.task_complete_wt) as completed FROM t_task_done_header,m_duty_header "
			+ "WHERE m_duty_header.duty_id=t_task_done_header.duty_id AND t_task_done_header.emp_id=:empId "
			+ "AND t_task_done_header.task_date=:date GROUP BY t_task_done_header.duty_id  ", nativeQuery = true)
	List<DutyWiseCount> getDutyWiseCount(@Param("empId") int empId,@Param("date") String date);


}