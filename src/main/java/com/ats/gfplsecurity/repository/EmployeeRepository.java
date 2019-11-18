package com.ats.gfplsecurity.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.Purpose;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Optional<Employee> findByEmpDscAndDelStatus(String dscNumber,int del);

	List<Employee> findAllByDelStatusOrderByEmpFname(int i);
	
	List<Employee> findAllByDelStatusAndEmpCatIdOrderByEmpFname(int i,int catId);
	
	Employee findByEmpIdAndDelStatus(int empId,int i);
	
	Employee findByEmpCode(String code);
	
	Employee findByEmpDsc(String dsc);
	
	List<Employee> findAllByEmpCodeOrderByEmpFname(String code);
	List<Employee> findAllByEmpDscOrderByEmpFname(String dsc);
	

	@Transactional
	@Modifying
	@Query("update Employee set ex_var1=:token  WHERE emp_id=:empId")
	int updateUserToken(@Param("empId") int empId,@Param("token") String token);
	
	@Transactional
	@Modifying
	@Query("update Employee set ex_var3=:token  WHERE emp_id=:empId")
	int updateChatToken(@Param("empId") int empId,@Param("token") String token);
	

	@Query(value="SELECT COALESCE(( SELECT d.emp_dept_name FROM m_emp_department d, emp_info e "
			+ "WHERE e.emp_dept_id=d.emp_dept_id AND e.del_status=1 AND d.del_status=1 "
			+ "AND e.emp_id=:empId),'na') as dept_name",nativeQuery=true)
	String getDeptNameByEmpId(@Param("empId") int empId);
	
	
	@Transactional
	@Modifying
	@Query("update Employee set emp_dsc=:dsc  WHERE emp_id=:empId")
	int updateDsc(@Param("empId") int empId,@Param("dsc") String dsc);
	

	@Query(value="SELECT e.* FROM emp_info e, m_emp_department d, m_emp_category c, m_emp_type t, m_company cp WHERE "
			+ "e.emp_dept_id = d.emp_dept_id AND e.emp_cat_id = c.emp_cat_id AND e.emp_type_id = t.emp_type_id "
			+ "AND e.company_id = cp.company_id AND e.del_status = 1 AND e.emp_cat_id IN( SELECT s.setting_value "
			+ "FROM t_settings s WHERE s.setting_id IN(2, 3) ORDER BY e.emp_fname)",nativeQuery=true)
	List<Employee> getAllEmpByDesg();
	
	
	@Query(value="SELECT e.* FROM emp_info e, m_emp_department d, m_emp_category c, m_emp_type t, m_company cp "
			+ "WHERE e.emp_dept_id = d.emp_dept_id AND e.emp_cat_id = c.emp_cat_id AND e.emp_type_id = t.emp_type_id "
			+ "AND e.company_id = cp.company_id AND e.del_status = 1 ORDER BY e.emp_fname",nativeQuery=true)
	List<Employee> getAllEmp();

	@Query(value="SELECT e.* FROM emp_info e, m_emp_department d, m_emp_category c, m_emp_type t, m_company cp "
			+ "WHERE e.emp_dept_id = d.emp_dept_id AND e.emp_cat_id = c.emp_cat_id AND e.emp_type_id = t.emp_type_id "
			+ "AND e.company_id = cp.company_id AND e.del_status = 1 AND e.emp_dept_id IN(:deptId) ORDER BY e.emp_fname",nativeQuery=true)
	List<Employee> getAllEmpByDept(@Param("deptId") List<Integer> deptId);


	
	

	
}
