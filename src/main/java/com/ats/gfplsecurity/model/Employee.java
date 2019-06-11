package com.ats.gfplsecurity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "emp_info")
public class Employee  implements Serializable{

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
}
