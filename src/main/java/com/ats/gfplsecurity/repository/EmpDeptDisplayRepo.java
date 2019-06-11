package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.EmpDeptDisplay;

@Repository
public interface EmpDeptDisplayRepo extends JpaRepository<EmpDeptDisplay, Integer> {

	@Query(value = "SELECT d.*,c.company_name FROM m_emp_department d, m_company c WHERE d.company_id=c.company_id AND d.del_status=1", nativeQuery = true)
	List<EmpDeptDisplay> getEmpDeptlist();   	
}

