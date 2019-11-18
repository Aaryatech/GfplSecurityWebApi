package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.gfplsecurity.model.chat.AllDeviceTokens;

public interface AllDeviceTokensRepo extends JpaRepository<AllDeviceTokens, Integer>{

	@Query(value = " SELECT  DISTINCT ex_var3,UUID() as id from emp_info WHERE del_status=1  ", nativeQuery = true)
	List<AllDeviceTokens> getAllTokens();

	
}
