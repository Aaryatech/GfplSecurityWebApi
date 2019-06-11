package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.Gate;

@Repository
public interface GateRepository extends JpaRepository<Gate, Integer> {

	List<Gate> findAllByDelStatus(int i);

	Gate findByGateId(int empId);

}
