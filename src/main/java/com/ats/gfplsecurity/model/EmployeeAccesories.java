package com.ats.gfplsecurity.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "t_employee_accsories")
public class EmployeeAccesories implements Serializable {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empAccsoriesId;
	
    private int emp_id;
	private int productionAccessId;
	private String accomatationValue;
	private int quantity;
	private String consumableCode;
	private String uploadPhoto;
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
}
