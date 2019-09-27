package com.ats.gfplsecurity.model.checklist;

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
@Table(name="t_checklist_assign")
public class ChecklistAssign {
	
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

}
