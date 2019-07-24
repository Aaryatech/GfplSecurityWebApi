package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.duty.DutyHeaderDetailDisplay;

@Repository
public interface DutyHeaderDetailDisplayRepo extends JpaRepository<DutyHeaderDetailDisplay, Integer> {

	/*@Query(value = " SELECT d.*,dp.emp_dept_name as dept_name, c.emp_cat_name as desg_name,s.shift_name, s.shift_from_time,s.shift_to_time,s.no_of_hr ,"
			+ "CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as created_by_name  FROM m_duty_header d,"
			+ "m_emp_department dp,m_emp_category c,m_shift s, emp_info e WHERE d.del_status=1 AND "
			+ "d.dept_id=dp.emp_dept_id AND d.desg_id=c.emp_cat_id AND d.shift_id=s.shift_id "
			+ "AND e.emp_id=d.created_by ", nativeQuery = true)
	List<DutyHeaderDetailDisplay> getAllDutyHeader();
	*/
	
	@Query(value = "SELECT d.*, dp.emp_dept_name AS dept_name, c.emp_cat_name AS desg_name, s.shift_name, s.shift_from_time, "
			+ "s.shift_to_time, s.no_of_hr,null AS created_by_name FROM m_duty_header d, m_emp_department dp, m_emp_category c, "
			+ "m_shift s WHERE d.del_status = 1 AND d.dept_id = dp.emp_dept_id AND d.desg_id = c.emp_cat_id AND "
			+ "d.shift_id = s.shift_id Order By d.duty_id desc ", nativeQuery = true)
	List<DutyHeaderDetailDisplay> getAllDutyHeader();
	
	
	/*@Query(value = "SELECT d.*,dp.emp_dept_name as dept_name, c.emp_cat_name as desg_name,s.shift_name, "
			+ "s.shift_from_time,s.shift_to_time,s.no_of_hr ,CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) "
			+ "as created_by_name  FROM m_duty_header d,m_emp_department dp,m_emp_category c,"
			+ "m_shift s, emp_info e WHERE d.del_status=1 AND d.dept_id=dp.emp_dept_id AND "
			+ "d.desg_id=c.emp_cat_id AND d.shift_id=s.shift_id AND "
			+ "e.emp_id=d.created_by AND d.dept_id IN (:deptId)", nativeQuery = true)
	List<DutyHeaderDetailDisplay> getAllDutyHeaderByDept(@Param("deptId") List<Integer> deptId);*/
	
	@Query(value = "SELECT d.*, dp.emp_dept_name AS dept_name, c.emp_cat_name AS desg_name, s.shift_name, s.shift_from_time, "
			+ "s.shift_to_time, s.no_of_hr, null AS created_by_name FROM m_duty_header d, m_emp_department dp, "
			+ "m_emp_category c, m_shift s WHERE d.del_status = 1 AND d.dept_id = dp.emp_dept_id AND "
			+ "d.desg_id = c.emp_cat_id AND d.shift_id = s.shift_id AND d.dept_id IN (:deptId)", nativeQuery = true)
	List<DutyHeaderDetailDisplay> getAllDutyHeaderByDept(@Param("deptId") List<Integer> deptId);
	

}