package com.ats.gfplsecurity.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	
	private String empDsc;
	private String empCode;
	private int companyId;
	private int empCatId;
	private int empTypeId;
	private int empDeptId;
	private int locId ;
	private String empFname;
	private String empMname;
	private String empSname;
	private String empPhoto;
	private String empMobile1;
	private String empMobile2;
	private String empEmail;
	private String empAddressTemp;
	private String empAddressPerm;
	private String empBloodgrp;
	private String empEmergencyPerson1;
	private String empEmergencyNo1;
	private String empEmergencyPerson2;
	private String empEmergencyNo2;
	private float empRatePerhr;
	private String empJoiningDate;
	private float empPrevExpYrs;
	private float empPrevExpMonths;
	private String empLeavingDate;
	private String empLeavingReason;
	private String lockPeriod;
	private String termConditions;
	private int salaryId;
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

	private String deptName;
	private String catName;
	private String typeName;
	private String companyName;
	
	private float grossSalary;
	private float noOfHrs;
	private String gender;
	private String dob;
	private String scanCopy1;
	private String scanCopy2;
	private Integer pf;
	private Integer esic;
	private Integer bonus;
	private Integer cl;
	private Integer sl;
	private Integer pl;
	
	

}
