package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatDetailIdsByReadStatus;


public interface ChatDetailIdsByReadStatusRepo extends JpaRepository<ChatDetailIdsByReadStatus, Integer> {
	
	
	@Query(value = " SELECT chat_task_detail_id FROM t_chat_task_detail WHERE mark_as_read=:readStatus AND del_status=1  ", nativeQuery = true)
	List<ChatDetailIdsByReadStatus> getChatDetailIdsByMarkStatus(@Param("readStatus") int readStatus);


}
