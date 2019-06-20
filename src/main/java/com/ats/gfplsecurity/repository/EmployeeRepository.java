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

	Optional<Employee> findByEmpDsc(String dscNumber);

	List<Employee> findAllByDelStatus(int i);
	
	List<Employee> findAllByDelStatusAndEmpCatId(int i,int catId);
	
	Employee findByEmpIdAndDelStatus(int empId,int i);
	
	Employee findByEmpCode(String code);
	

	@Transactional
	@Modifying
	@Query("update Employee set ex_var1=:token  WHERE emp_id=:empId")
	int updateUserToken(@Param("empId") int empId,@Param("token") String token);
	

	@Query(value="SELECT COALESCE(( SELECT d.emp_dept_name FROM m_emp_department d, emp_info e "
			+ "WHERE e.emp_dept_id=d.emp_dept_id AND e.del_status=1 AND d.del_status=1 "
			+ "AND e.emp_id=:empId),'na') as dept_name",nativeQuery=true)
	String getDeptNameByEmpId(@Param("empId") int empId);
	

	
}
