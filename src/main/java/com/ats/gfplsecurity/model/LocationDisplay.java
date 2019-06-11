package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class LocationDisplay {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int locId;

	private String locName;
	private String locNameShort;
	private String locShortAddress;
	private String locHrContactPerson;
	private String locHrContactNumber;
	private String locHrContactEmail;
	private String locRemarks;
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
	private int compId;
	private String companyName;
	
}
