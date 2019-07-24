package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.AssignDutyEmployee;

public interface AssignDutyEmployeeRepo extends JpaRepository<AssignDutyEmployee, Integer> {
	
	@Query(value = "SELECT e.emp_id,CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as emp_name,"
			+ "if( FIND_IN_SET(e.emp_id,(SELECT d.emp_ids from t_assign_duty d WHERE d.duty_id=:dutyId))>0,'true','false') "
			+ "as is_assigned FROM emp_info e WHERE e.del_status=1 "
			+ "AND e.emp_dept_id=(SELECT dept_id from m_duty_header WHERE duty_id=:dutyId)", nativeQuery = true)
	List<AssignDutyEmployee> getAssignEmpInDuty(@Param("dutyId") int dutyId);

	
	
}