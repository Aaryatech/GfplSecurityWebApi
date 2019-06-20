package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="m_duty_header")
public class DutyHeader {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dutyId;
	private String dutyName;
	private int deptId;
	private int desgId;
	private int type;
	private String typeDesc;
	private int shiftId;
	private int createdId;
	private String createdDate;
	private int totalTaskWt;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	
}
