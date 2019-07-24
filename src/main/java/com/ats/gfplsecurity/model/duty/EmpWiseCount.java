package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmpWiseCount {

	@Id	
	private String id;
	private int empId;
	private String empName;
	private String desgName;
	private Integer total;
	private Integer completed;
	
}
