package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SupGatepassCount {
	
	@Id
	private String id;
	private int supTempCount;
	private int supDayCount;
	private int supOutEmpCount;
	
	
}
