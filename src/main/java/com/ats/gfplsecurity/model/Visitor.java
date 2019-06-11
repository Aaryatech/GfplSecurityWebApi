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
@Table(name = "t_gatepass_visitor")
public class Visitor implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gatepassVisitorId;

	private String visitDateIn;
	private int securityIdIn;
	private String personName;
	private String personCompany;
	private String personPhoto;
	private String mobileNo;
	private String idProof;
	private String idProof1;
	private String otherPhoto;
	private int purposeId;
	private String visitPurposeText;
	private String purposeRemark;
	private String empIds;
	private String empName;
	private int gateId;
	private int gatePasstype;
	private int visitStatus ;
	private int visitType;
	private String inTime;
	private int visitCardId;
	private String visitCardNo;
	private int takeMobile;
	private String meetingDiscussion;
	private String uploadPhoto;
	private String visitOutTime;
	private float totalTimeDifference;
	private int securityIdOut;
	private String visitDateOut;
	private String userSignImage;
	private int delStatus;
	private int isUsed;
	
	private int exInt1;
	private int exInt2;
	private int exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
}
