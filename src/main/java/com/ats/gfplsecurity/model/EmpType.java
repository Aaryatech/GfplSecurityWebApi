package com.ats.gfplsecurity.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="m_emp_type")
public class EmpType implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empTypeId;
	
	private int companyId;
	private String empTypeName;
	private String empTypeShortName;
	private int compOffRequestAllowed;
	private int setting1;
	private int setting2;
	private String empTypeRemarks;
	private String empTypeAccess;
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
