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
public class ChatDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int chatTaskDetailId;
	private int headerId;
	private int typeOfText;
	private String textValue;
	private String dateTime;
	private int userId;
	private String userName;
	private int delStatus;
	private int markAsRead;
	
}
