package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.EmpType;
import com.ats.gfplsecurity.model.Employee;

@Repository
public interface EmpTypeRepository extends JpaRepository<EmpType, Integer> {

	List<EmpType> findAllByDelStatus(int i);

	EmpType findByEmpTypeId(int empTypeId);

}
