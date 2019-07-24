package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TaskDetailDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskId;
	private int dutyId;
	private String taskNameEng;
	private String taskNameMar;
	private String taskNameHin;
	private String taskDescEng;
	private String taskDescMar;
	private String taskDescHin;
	private int photoReq;
	private int remarkReq;
	private int taskWeight;
	private int createdBy;
	private String createdDate;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	private String dutyName;
	private String createdByName;
	
	
}
