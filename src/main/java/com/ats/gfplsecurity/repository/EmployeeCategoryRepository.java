package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.EmployeeCategory;
@Repository
public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategory, Integer>{
	
	List<EmployeeCategory> findAllByDelStatus(int i);
	
	EmployeeCategory findByEmpCatId(int empCatId);

}
