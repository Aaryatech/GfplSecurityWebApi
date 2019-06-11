package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.gfplsecurity.model.EmployeeCategoryDisplay;

public interface EmployeeCategoryDisplayRepo extends JpaRepository<EmployeeCategoryDisplay, Integer>{
	
	@Query(value = "SELECT e.*,c.company_name FROM m_emp_category e, m_company c WHERE e.company_id=c.company_id AND e.del_status=1", nativeQuery = true)
	List<EmployeeCategoryDisplay> getEmpCategorylist();   	

}