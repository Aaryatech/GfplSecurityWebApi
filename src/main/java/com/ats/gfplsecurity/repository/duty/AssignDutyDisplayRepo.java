package com.ats.gfplsecurity.repository.duty;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.duty.AssignDutyDisplay;

@Repository
public interface AssignDutyDisplayRepo extends JpaRepository<AssignDutyDisplay, Integer> {
	
	@Query(value = " SELECT a.*,d.duty_name, s.shift_name,s.shift_from_time,s.shift_to_time,s.no_of_hr, "
			+ "COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) from emp_info e "
			+ "WHERE e.del_status=1 AND e.emp_id=a.task_assign_user_id),'')as task_assign_user_name, "
			+ "COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) from emp_info e "
			+ "WHERE e.del_status=1 AND e.emp_id=a.last_edit_user_id),'')as last_edit_user_name "
			+ "FROM t_assign_duty a, m_duty_header d, m_shift s WHERE a.del_status=1 AND d.del_status=1 "
			+ "AND s.del_status=1 AND a.duty_id=d.duty_id AND d.shift_id=s.shift_id AND a.duty_id=:dutyId ", nativeQuery = true)
	AssignDutyDisplay getAssignDutyByDutyId(@Param("dutyId") int dutyId);

	
	
}
