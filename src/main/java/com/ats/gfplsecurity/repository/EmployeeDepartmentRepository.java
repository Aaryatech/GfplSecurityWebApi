package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.EmployeeDepartment;

@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Integer> {

	List<EmployeeDepartment> findAllByDelStatus(int i);

	EmployeeDepartment findByEmpDeptIdAndDelStatus(int empDeptId,int i);

}
