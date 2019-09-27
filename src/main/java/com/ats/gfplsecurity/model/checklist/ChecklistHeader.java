package com.ats.gfplsecurity.model.checklist;

import java.util.List;

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
@Table(name="m_dept_checklist_header")
public class ChecklistHeader {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int checklistHeaderId;
	private int deptId;
	private String checklistName;
	private int isUsed;
	private int delStatus;
	private int createdBy;
	private String createdDate;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	@Transient
	List<ChecklistDetail> checklistDetail;

}
