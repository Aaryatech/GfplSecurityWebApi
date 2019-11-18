package com.ats.gfplsecurity.model.chat;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ChatHeaderEmpList {
	
	@Id	
	private int empId;
	private String name;
	private String photo;
	private int userType;

	

}
