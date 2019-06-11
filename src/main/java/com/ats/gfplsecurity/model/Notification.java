package com.ats.gfplsecurity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_notification")
public class Notification implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int notificationId;

	private int gatepassVisitorId;
	private int empId;
	private String empName;
	private int gatepassType;
	private int purposeType;
	private int purposeId;
	private String requestAcceptedTime;
	private int timeDifferenceRequest;
	private int status;	
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
}
