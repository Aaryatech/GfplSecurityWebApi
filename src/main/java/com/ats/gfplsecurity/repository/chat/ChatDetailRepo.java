package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatDetail;

public interface ChatDetailRepo extends JpaRepository<ChatDetail, Integer> {

	List<ChatDetail> findAllByDelStatus(int i);

	ChatDetail findByChatTaskDetailId(int i);
	
	List<ChatDetail> findByHeaderId(int i);
	
	List<ChatDetail> findAllByDelStatusAndMarkAsRead(int del,int read);
	
	
	/*@Transactional
	@Modifying
	@Query("UPDATE ChatDetail SET exVar1=CONCAT(exVar1,',:userId') WHERE NOT FIND_IN_SET(3,exVar1) AND chatTaskDetailId=:detailId")
	int updateUserIdAfterChatRead(@Param("userId") int userId, @Param("detailId") int detailId);*/
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE t_chat_task_detail SET ex_var1=CONCAT(ex_var1,',',:userId) WHERE NOT FIND_IN_SET(:userId,ex_var1) AND chat_task_detail_id=:detailId", nativeQuery = true)
	int updateUserIdAfterChatRead(@Param("userId") int userId, @Param("detailId") int detailId);
	
	/*@Transactional
	@Modifying
	@Query("UPDATE ChatDetail INNER JOIN ChatHeader ON ChatDetail.headerId=ChatHeader.headerId SET ChatDetail.markAsRead=3 WHERE (CHAR_LENGTH(ChatHeader.assignUserIds) - CHAR_LENGTH( REPLACE (ChatHeader.assignUserIds,',', '') ) +1)=(CHAR_LENGTH(ChatDetail.exVar1) - CHAR_LENGTH( REPLACE (ChatDetail.exVar1,',', '') ) +1) AND ChatDetail.chatTaskDetailId=:detailId ")
	int updateChatReadStatus(@Param("detailId") int detailId);*/
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE t_chat_task_detail INNER JOIN t_chat_task_header ON t_chat_task_detail.header_id=t_chat_task_header.header_id SET t_chat_task_detail.mark_as_read=3 WHERE (CHAR_LENGTH(t_chat_task_header.assign_user_ids) - CHAR_LENGTH( REPLACE (t_chat_task_header.assign_user_ids,',', '') ) +1)=(CHAR_LENGTH(t_chat_task_detail.ex_var1) - CHAR_LENGTH( REPLACE (t_chat_task_detail.ex_var1,',', '') ) +1) AND t_chat_task_detail.chat_task_detail_id=:detailId ", 
			  nativeQuery = true)
	int updateChatReadStatus(@Param("detailId") int detailId);
	

}
