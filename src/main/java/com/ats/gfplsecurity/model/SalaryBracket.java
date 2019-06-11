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
@Table(name = "m_salary_bracket")
public class SalaryBracket implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int salaryId;

	private String salaryBracket;
	private String salaryDescription;
	private int salaryType;
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
}