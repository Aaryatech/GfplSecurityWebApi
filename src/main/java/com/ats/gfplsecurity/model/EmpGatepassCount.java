package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmpGatepassCount {
	
	@Id
	private String id;
	private int tempGpCount;
	private int dayGpCount;
	private int outEmpCount;

	
	
}
