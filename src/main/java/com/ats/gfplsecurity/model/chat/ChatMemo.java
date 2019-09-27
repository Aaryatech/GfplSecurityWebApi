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
@Table(name="t_chat_memo_generated")
public class ChatMemo {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memoId;
	private int userId;
	private int taskHeaderId;
	private int generatedUserId;
	private String memoDesc;
	private String memoDate;
	private int status;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;

}
