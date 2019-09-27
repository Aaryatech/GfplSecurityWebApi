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
@Table(name="t_chat_task_detail")
public class ChatDetail {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int chatTaskDetailId;
	private int headerId;
	private int typeOfText;
	private String textValue;
	private String localDate;
	private String serverDate;
	private int userId;
	private String userName;
	private int delStatus;
	private int markAsRead;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
}
