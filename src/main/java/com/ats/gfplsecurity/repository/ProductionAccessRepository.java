package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.ProductionAccess;

@Repository
public interface ProductionAccessRepository extends JpaRepository<ProductionAccess, Integer>{

List<ProductionAccess> findAllByDelStatus(int i);
	
ProductionAccess findByProductionAccessId(int empId);
	
}
