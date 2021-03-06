package com.ats.gfplsecurity.model.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ChatDetailIdsByReadStatus {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int chatTaskDetailId;
	
}
