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
@Table(name="m_dept_checklist_detail")
public class ChecklistDetail {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int checklistDetailId;
	private int checklistHeaderId;
	private String checklist_desc;
	private int isPhoto;
	private int isUsed;
	private int delStatus;
	private int createdBy;
	private String createdDate;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;
	
}
