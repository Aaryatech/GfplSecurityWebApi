package com.ats.gfplsecurity.model.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MemoGenerated {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memoId;
	
	private int userId;
	private String userName;
	private int generatedUserId;
	private String generatedUserName;
	private String memoDesc;
	private String memoDate;
	private int taskHeaderId;
	private String headerName;
	
	
	
}
