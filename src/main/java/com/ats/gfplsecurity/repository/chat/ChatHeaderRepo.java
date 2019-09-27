package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.chat.ChatHeader;

public interface ChatHeaderRepo extends JpaRepository<ChatHeader, Integer> {

	List<ChatHeader> findAllByDelStatus(int i);

	List<ChatHeader> findAllByDelStatusAndIsActive(int i,int j);
	
	ChatHeader findByHeaderId(int i);

}
