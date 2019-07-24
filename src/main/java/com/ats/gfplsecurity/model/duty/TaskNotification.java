package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TaskNotification {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskDoneDetailId;
	private int dutyId;
	private String time;
	private int empId;
	private String name;
	private String taskName;
	private String token;

}
