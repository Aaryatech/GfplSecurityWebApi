package com.ats.gfplsecurity.model.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ChatHeaderDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int headerId;
	private String createdDate;
	private String headerName;
	private int createdUserId;
	private String adminUserIds;
	private String assignUserIds;
	private String taskDesc;
	private String image;
	private int status;
	private int requestUserId;
	private int taskCloseUserId;
	private String taskCompleteRemark;
	private int isReminderRequired;
	private int reminderFrequency;
	private int lastDate;
	private int isActive;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	private String createdByName;
	private String adminUserNames;
	private String assignUserNames;
	private String requestUserName;
	private String taskCloseUserName;
	
	private Integer privilege;
	
}
