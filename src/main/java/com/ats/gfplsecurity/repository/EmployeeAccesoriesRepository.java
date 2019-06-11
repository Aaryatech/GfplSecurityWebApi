package com.ats.gfplsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.EmployeeAccesories;
@Repository
public interface EmployeeAccesoriesRepository extends JpaRepository<EmployeeAccesories, Integer>{

}
