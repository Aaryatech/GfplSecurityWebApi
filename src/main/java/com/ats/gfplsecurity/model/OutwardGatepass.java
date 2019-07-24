package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_outward_gatepass")
public class OutwardGatepass {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gpOutwardId;
	
	private String dateOut;
	private String dateInExpected;
	private String dateIn;
	private int empId;
	private String empName;
	private String outwardName;
	private String toName;
	private int secIdOut;
	private int secIdIn;
	private String outTime;
	private String inTime;
	private int status;
	private String outPhoto;
	private String inPhoto;
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;

}
