package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmpDeptDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empDeptId;
	
	private int companyId;
	private String empDeptName;
	private String empDeptShortName;
	private String empDeptRemarks;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerEnterDatetime;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1; 
	private String exVar2; 
	private String exVar3;
	
	private String companyName;
	
}
