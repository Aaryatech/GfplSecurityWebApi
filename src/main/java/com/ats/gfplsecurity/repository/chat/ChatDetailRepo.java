package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.chat.ChatDetail;

public interface ChatDetailRepo extends JpaRepository<ChatDetail, Integer> {

	List<ChatDetail> findAllByDelStatus(int i);

	ChatDetail findByChatTaskDetailId(int i);
	
	List<ChatDetail> findByHeaderId(int i);

}
