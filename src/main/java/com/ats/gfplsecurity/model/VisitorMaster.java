package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "m_visitor")
public class VisitorMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int visitorId;
 
	private String personName;
	private String personCompany;
	private String mobileNo;
	private int noOfPerson;
	private String purposeRemark; 
	private String visitPurposeText;
	private int purposeId;
	private String personToMeet;
	private int prsonId;  
	private String idProof;
	private String idProof1;
	private String otherPhoto; 
	private int gatePasstype;   
	private String takeMobile;    
	private int delStatus;
	private int isUsed;
	
	private int exInt1;
	private int exInt2;
	private int exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;

}
