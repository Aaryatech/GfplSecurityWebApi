package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.chat.ChatGroup;


public interface ChatGroupRepo extends JpaRepository<ChatGroup, Integer> {

	List<ChatGroup> findAllByDelStatus(int i);

	List<ChatGroup> findAllByDelStatusAndIsActive(int i,int j);
	
	ChatGroup findByGroupId(int i);

}
