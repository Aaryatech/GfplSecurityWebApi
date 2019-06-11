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
@Table(name = "t_inward_gatepass")
public class MaterialGatepass implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gatepassInwardId;

	private String inwardDate;
	private int gatePassType;
	private int gatePassSubType;
	private String invoiceNumber;
	private String partyName;
	private int partyId;
	private int securityId;
	private String securityName;
	private String personPhoto;
	private String inwardPhoto;
	private String inTime;
	private float noOfNugs;
	private int itemType;
	private int delStatus;
	private int status;
	private int toEmpId;
	private int toDeptId;
	private int toStatus;
	private String toEmpName;
	private String toDeptName;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;

}
