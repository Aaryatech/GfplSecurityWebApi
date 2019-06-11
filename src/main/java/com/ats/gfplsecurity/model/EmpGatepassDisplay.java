package com.ats.gfplsecurity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class EmpGatepassDisplay implements Serializable{
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gatepassEmpId;
	
	private String empDateOut;
	private String empDateIn;
	private int userId;
	private String userName;
	private int empId;
	private String empName;
	private int gatePassType;
	private int gatePassSubType;
	private int purposeId;
	private String purposeText;
	private String purposeRemark;
	private int securityIdOut;
	private int securityIdIn;
	private float noOfHr;
	private String outTime;
	private String inTime;
	private String newOutTime;
	private String newInTime;
	private String actualTimeDifference;
	private int gatePassStatus;
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	private int empDeptId;
	private String empDeptName;
	private int empTypeId;
	private String empTypeName;
	
}