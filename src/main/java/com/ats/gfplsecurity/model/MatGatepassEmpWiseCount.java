package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MatGatepassEmpWiseCount {
	
	@Id
	private String id;
	private int empPendingCount;
	private int empApprovedCount;
	private int empRejectedCount;

}
