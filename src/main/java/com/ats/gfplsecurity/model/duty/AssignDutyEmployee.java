package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class AssignDutyEmployee {
	
	@Id
	private int empId;
	private String empName;
	private boolean isAssigned;
	
	

}
