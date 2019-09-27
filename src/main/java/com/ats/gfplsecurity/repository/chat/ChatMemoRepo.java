package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.chat.ChatMemo;

public interface ChatMemoRepo extends JpaRepository<ChatMemo, Integer> {

	List<ChatMemo> findAllByDelStatus(int i);

	ChatMemo findByMemoId(int i);
	
	

}