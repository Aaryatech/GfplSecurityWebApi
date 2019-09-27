package com.ats.gfplsecurity.model.checklist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ReportEmpWise {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	private String empName;
	private int totalAssign;
	private int totalTask;
	private int totalDetailTask;
	private int pendingCount;
	private int approvedCount;
	private int rejectedCount;

}
