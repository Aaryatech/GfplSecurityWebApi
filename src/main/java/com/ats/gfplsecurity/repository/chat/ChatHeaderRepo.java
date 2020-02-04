package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatHeader;

public interface ChatHeaderRepo extends JpaRepository<ChatHeader, Integer> {

	List<ChatHeader> findAllByDelStatus(int i);

	//21/11/2019
	List<ChatHeader> findByDelStatusAndStatusIn(int i,List<Integer> status);

	
	List<ChatHeader> findAllByDelStatusAndIsActive(int i,int j);
	
	ChatHeader findByHeaderId(int i);
	
	@Transactional
	@Modifying
	@Query("update ChatHeader set status=:status, request_user_id=:empId  WHERE header_id=:headerId")
	int updateCloseRequest(@Param("headerId") int headerId,@Param("status") int status,@Param("empId") int empId);

	@Transactional
	@Modifying
	@Query("update ChatHeader set status=:status, task_close_user_id=:empId, task_complete_remark=:remark, ex_var2=:date  WHERE header_id=:headerId")
	int updateChatClose(@Param("headerId") int headerId,@Param("status") int status,@Param("empId") int empId,@Param("remark") String remark,@Param("date") String date);

}
