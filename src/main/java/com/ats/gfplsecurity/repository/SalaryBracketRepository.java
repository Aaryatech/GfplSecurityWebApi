package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.SalaryBracket;

@Repository
public interface SalaryBracketRepository extends JpaRepository<SalaryBracket, Integer> {

	List<SalaryBracket> findAllByDelStatus(int i);

	SalaryBracket findBySalaryId(int salaryId);
}
