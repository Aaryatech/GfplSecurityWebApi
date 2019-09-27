package com.ats.gfplsecurity.model.checklist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ReportEmpDetail {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int actionHeaderId;
	private String actionDate;
	private String actionDatetime;
	private String checklistName;
	private String closedDate;
	private String closedDatetime;
	private String closedByName;
	private int totalDetailTask;
	private int pendingCount;
	private int approvedCount;
	private int rejectedCount;

}
