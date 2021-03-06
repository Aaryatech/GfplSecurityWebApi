package com.ats.gfplsecurity.model.checklist;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ChecklistCountReport {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int actionHeaderId;
	private int assignId;
	private int deptId;
	private int checklistHeaderId;
	private String checklistName;
	private int status;
	private int actionBy;
	private String actionDate;
	private String actionDatetime;
	private int closedBy;
	private String closedDate;
	private String closedDatetime;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;

	private String deptName;
	private String actionByName;
	private String closedByName;
	private int pendingCount;
	private int approvedCount;
	private int rejectedCount;
	
	

	

}
