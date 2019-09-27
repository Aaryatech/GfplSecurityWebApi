package com.ats.gfplsecurity.model.checklist;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class OpenChecklist {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assignId;
	private int deptId;
	private int checklistHeaderId;
	private String assignEmpIds;
	private int assignedBy;
	private String assignedDate;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;
	
	private String deptName;
	private String checklistName;
	private String assignEmpName;
	private String assignByName;
	
	@Transient
	private List<ChecklistDetail> detailList;

}
