package com.ats.gfplsecurity.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "m_purpose")
public class Purpose implements Serializable{

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int purposeId;

	private String purposeHeading;
	private int purposeType;//2 Purpose Types
    private String description;
	private String remark;
	private String empId;
	@Column(name="notification_l4")
	private String notificationL4;
	private String passDuration;
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	
	
}
