package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class EmployeeCategoryDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empCatId ;
	
	private int companyId ;
	private String empCatName;
	private String empCatShortName;
	private String empCatRemarks;
	private int delStatus;
	private int isActive;
	private int makerUserId ;
	private String makerEnterDatetime;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1; 
	private String exVar2; 
	private String exVar3;
	
	private String companyName;
	
	
}
