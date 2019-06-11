package com.ats.gfplsecurity.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="m_gate")
public class Gate implements Serializable{

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gateId;

	private String gateName;
	private String gateDesc;
	private String gateNo;
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
}
