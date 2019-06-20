package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_assign_duty")
public class AssignDuty {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assignId;
	private String assignDate;
	private int dutyId;
	private String empIds;
	private String notifyTime;
	private int taskAssignUserId;
	private String lastEditDate;
	private int lastEditUserId;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;	
	
	
}
