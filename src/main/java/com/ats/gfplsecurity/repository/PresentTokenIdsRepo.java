package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.PresentTokenIds;

public interface PresentTokenIdsRepo extends JpaRepository<PresentTokenIds, Integer> {
	
	@Query(value = "SELECT emp_id FROM emp_info WHERE ex_var1=':token'", nativeQuery = true)
	List<PresentTokenIds> getEmpIdsOfRegToken(@Param("token") String token);   

}
