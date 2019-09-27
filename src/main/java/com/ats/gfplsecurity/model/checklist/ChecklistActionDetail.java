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
@Table(name="t_checklist_action_detail")
public class ChecklistActionDetail {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int actionDetailId;
	private int actionHeaderId;
	private int checklistMasterHeaderId;
	private int checklistMasterDetailId;
	private String checklist_desc;
	private int isPhoto;
	private int checkStatus;
	private String actionPhoto;
	private String closedPhoto;
	private String checkDate;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;

}
