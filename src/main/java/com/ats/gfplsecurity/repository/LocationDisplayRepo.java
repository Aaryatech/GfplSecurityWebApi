package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.LocationDisplay;

@Repository
public interface LocationDisplayRepo extends JpaRepository<LocationDisplay, Integer> {

	@Query(value = "SELECT l.*,c.company_name FROM m_location l,m_company c WHERE l.comp_id=c.company_id AND l.del_status=1 ", nativeQuery = true)
	List<LocationDisplay> getAllLocList();  

}