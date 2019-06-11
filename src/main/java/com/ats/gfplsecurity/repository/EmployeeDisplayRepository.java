package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.EmployeeDisplay;

@Repository
public interface EmployeeDisplayRepository extends JpaRepository<EmployeeDisplay, Integer>{
	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id AND e.emp_dept_id IN (:deptIds) AND e.emp_cat_id IN (:catIds) AND e.emp_type_id IN (:typeIds) "
			+ "AND e.del_status=1", nativeQuery = true)
	List<EmployeeDisplay> getEmpList_ByDept_ByType_ByCat(
			@Param("deptIds") List<Integer> deptIds,
			@Param("catIds") List<Integer> catIds,
			@Param("typeIds") List<Integer> typeIds);   

	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id AND e.emp_cat_id IN (:catIds) AND e.emp_type_id IN (:typeIds) "
			+ "AND e.del_status=1", nativeQuery = true)
	List<EmployeeDisplay> getEmpList_ByType_ByCat(
			@Param("catIds") List<Integer> catIds,
			@Param("typeIds") List<Integer> typeIds);  
	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id AND e.emp_dept_id IN (:deptIds) AND e.emp_cat_id IN (:catIds) "
			+ "AND e.del_status=1", nativeQuery = true)
	List<EmployeeDisplay> getEmpList_ByDept_ByCat(
			@Param("deptIds") List<Integer> deptIds,
			@Param("catIds") List<Integer> catIds);   
	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id AND e.emp_dept_id IN (:deptIds) AND e.emp_type_id IN (:typeIds) "
			+ "AND e.del_status=1 ORDER BY e.emp_id", nativeQuery = true)
	List<EmployeeDisplay> getEmpList_ByDept_ByType(
			@Param("deptIds") List<Integer> deptIds,
			@Param("typeIds") List<Integer> typeIds);   
	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id "
			+ "AND e.del_status=1 ORDER BY e.emp_id", nativeQuery = true)
	List<EmployeeDisplay> getAllEmpList();  
	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id AND e.emp_dept_id IN (:deptIds) "
			+ "AND e.del_status=1 ORDER BY e.emp_id", nativeQuery = true)
	List<EmployeeDisplay> getEmpList_ByDept(@Param("deptIds") List<Integer> deptIds);   
	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id AND e.emp_cat_id IN (:catIds) "
			+ "AND e.del_status=1 ORDER BY e.emp_id", nativeQuery = true)
	List<EmployeeDisplay> getEmpList_ByCat(@Param("catIds") List<Integer> catIds);   
	
	@Query(value = " SELECT e.*, d.emp_dept_name as dept_name, c.emp_cat_name as cat_name, t.emp_type_name as type_name,"
			+ " cp.company_name as company_name from emp_info e, m_emp_department d,m_emp_category c, m_emp_type t, "
			+ "m_company cp WHERE e.emp_dept_id=d.emp_dept_id AND e.emp_cat_id=c.emp_cat_id AND e.emp_type_id=t.emp_type_id "
			+ "AND e.company_id=cp.company_id AND e.emp_type_id IN (:typeIds) "
			+ "AND e.del_status=1 ORDER BY e.emp_id", nativeQuery = true)
	List<EmployeeDisplay> getEmpList_ByType(@Param("typeIds") List<Integer> typeIds);   
	
}
