package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.EmpWiseCount;

public interface EmpWiseCountRepo extends JpaRepository<EmpWiseCount, Integer> {

	@Query(value = "SELECT UUID() as id, emp_info.emp_id as emp_id,CONCAT(emp_info.emp_fname,' ',emp_info.emp_mname,' ',"
			+ "emp_info.emp_sname) as emp_name,m_emp_category.emp_cat_name as desg_name, SUM(t_task_done_header.duty_weight) "
			+ "as total, SUM(t_task_done_header.task_complete_wt) as completed FROM t_task_done_header,m_emp_department,"
			+ "m_duty_header,emp_info,m_emp_category WHERE m_emp_department.emp_dept_id=m_duty_header.dept_id AND "
			+ "m_duty_header.duty_id=t_task_done_header.duty_id AND t_task_done_header.emp_id=emp_info.emp_id AND "
			+ "emp_info.emp_cat_id=m_emp_category.emp_cat_id AND m_emp_department.emp_dept_id=:deptId "
			+ "AND t_task_done_header.task_date=:date GROUP BY emp_info.emp_id  ", nativeQuery = true)
	List<EmpWiseCount> getAllEmpWiseCount(@Param("deptId") int deptId, @Param("date") String date);

	@Query(value = "SELECT UUID() as id, emp_info.emp_id as emp_id,CONCAT(emp_info.emp_fname,' ',emp_info.emp_mname,' ',"
			+ "emp_info.emp_sname) as emp_name,m_emp_category.emp_cat_name as desg_name, SUM(t_task_done_header.duty_weight) "
			+ "as total, SUM(t_task_done_header.task_complete_wt) as completed FROM t_task_done_header,m_emp_department,"
			+ "m_duty_header,emp_info,m_emp_category WHERE m_emp_department.emp_dept_id=m_duty_header.dept_id AND "
			+ "m_duty_header.duty_id=t_task_done_header.duty_id AND t_task_done_header.emp_id=emp_info.emp_id "
			+ "AND emp_info.emp_cat_id=m_emp_category.emp_cat_id AND m_emp_department.emp_dept_id=:deptId AND "
			+ "emp_info.emp_id IN (:empId) AND t_task_done_header.task_date=:date GROUP BY emp_info.emp_id ", nativeQuery = true)
	List<EmpWiseCount> getEmpWiseCount(@Param("deptId") int deptId, @Param("empId") List<Integer> empId,
			@Param("date") String date);

}