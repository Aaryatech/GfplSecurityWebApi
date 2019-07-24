package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.DeptWiseCount;

public interface DeptWiseCountRepo extends JpaRepository<DeptWiseCount, Integer> {

	@Query(value = "SELECT UUID() as id, m_emp_department.emp_dept_id as dept_id,m_emp_department.emp_dept_name as dept_name, "
			+ "SUM(t_task_done_header.duty_weight) as total, SUM(t_task_done_header.task_complete_wt) as completed "
			+ "FROM t_task_done_header,m_emp_department,m_duty_header WHERE m_emp_department.emp_dept_id=m_duty_header.dept_id "
			+ "AND m_duty_header.duty_id=t_task_done_header.duty_id AND m_emp_department.emp_dept_id IN (:deptId) AND "
			+ "t_task_done_header.task_date=:date GROUP BY m_emp_department.emp_dept_id ", nativeQuery = true)
	List<DeptWiseCount> getDeptWiseCount(@Param("deptId") List<Integer> deptId,@Param("date") String date);

	@Query(value = "SELECT UUID() as id, m_emp_department.emp_dept_id as dept_id,m_emp_department.emp_dept_name as dept_name, "
			+ "SUM(t_task_done_header.duty_weight) as total, SUM(t_task_done_header.task_complete_wt) as completed "
			+ "FROM t_task_done_header,m_emp_department,m_duty_header WHERE m_emp_department.emp_dept_id=m_duty_header.dept_id "
			+ "AND m_duty_header.duty_id=t_task_done_header.duty_id AND t_task_done_header.task_date=:date GROUP BY m_emp_department.emp_dept_id ", nativeQuery = true)
	List<DeptWiseCount> getAllDeptWiseCount(@Param("date") String date);

}
