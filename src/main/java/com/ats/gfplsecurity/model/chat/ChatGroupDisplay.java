package com.ats.gfplsecurity.model.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ChatGroupDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int groupId;
	private String groupName;
	private String groupDesc;
	private String userIds;
	private int groupCreatedUserId;
	private String groupCreatedDate;
	private int isActive;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;
	
	private String createdByName;
	private String userNames;

	
}
