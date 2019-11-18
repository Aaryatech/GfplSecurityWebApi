package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_album_enquiry")
public class AlbumEnquiry {

	private int enquiryNo;
	private int frId;
	private String custName;
	private String mobileNo;
	private String photo;
	private String enquiryDate;
	private String enquiryDateTime;
	private String approvedDateTime;
	private int approvedUserId;
	private String approvedUserName;	
	private int albumId;
	private int status;
	private int delStatus;
	private int noNotifictnFired;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	private int exInt1;
	private int exInt2;
	private int exInt3;
}
