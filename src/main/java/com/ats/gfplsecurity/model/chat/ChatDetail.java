package com.ats.gfplsecurity.model.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="t_chat_task_detail")
public class ChatDetail {

	/*public ChatDetail(int i, int headerId2, int j, String string, String string2, String string3, int createdUserId,
			String createdBy, int k, int l, int m, int n, int o, String string4, String string5, String string6) {
		// TODO Auto-generated constructor stub
	}*/
	
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

	
	@Transient
	private int replyToMsgType;

	@Transient
	private String replyToMsg;

	@Transient
	private String replyToName;
}
