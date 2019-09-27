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
@Table(name="t_checklist_action_header")
public class ChecklistActionHeader {

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
	
	@Transient
	private List<ChecklistActionDetail> checklistActionDetail;
	
}
