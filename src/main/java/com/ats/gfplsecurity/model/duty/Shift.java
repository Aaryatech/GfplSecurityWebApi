package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="m_shift")
public class Shift {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shiftId;
	private String shiftName;
	private String shiftFromTime;
	private String shiftToTime;
	private String noOfHr;
	private int isActive;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;


}
