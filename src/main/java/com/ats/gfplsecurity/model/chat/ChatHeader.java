package com.ats.gfplsecurity.model.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="t_chat_task_header")
public class ChatHeader {

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
	private String reminderFrequency;
	private String lastDate;
	private int isActive;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
}
