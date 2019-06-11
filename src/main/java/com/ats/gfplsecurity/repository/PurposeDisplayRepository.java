package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.PurposeDisplay;
import com.ats.gfplsecurity.model.VisitorGatepassDisplay;

@Repository
public interface PurposeDisplayRepository extends JpaRepository<PurposeDisplay, Integer>{
	
	@Query(value = " SELECT\r\n" + 
			"    p.*,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            GROUP_CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname,\r\n" + 
			"                ' '\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            FIND_IN_SET(e.emp_id, p.emp_id)\r\n" + 
			"    ),\r\n" + 
			"    '-'\r\n" + 
			"    ) AS assign_emp_name\r\n" + 
			"FROM\r\n" + 
			"    m_purpose p\r\n" + 
			"WHERE\r\n" + 
			"    p.del_status = 1", nativeQuery = true)
	List<PurposeDisplay> getAllPurposeList(); 
	

}
