package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Company;
import com.ats.gfplsecurity.model.Employee;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	List<Company> findAllByDelStatus(int i);

	Company findByCompanyId(int companyId);

}
