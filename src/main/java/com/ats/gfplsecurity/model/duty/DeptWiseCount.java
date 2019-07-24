package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DeptWiseCount {

	@Id	
	private String id;
	private int deptId;
	private String deptName;
	private Integer total;
	private Integer completed;
	
}
