package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MatGatepassCount {
	
	@Id
	private String id;
	private int inwardCount;
	private int parcelCount;
	private int deptPendingCount;
	private int deptApprovedCount;
	private int deptRejectedCount;
	
	
}
