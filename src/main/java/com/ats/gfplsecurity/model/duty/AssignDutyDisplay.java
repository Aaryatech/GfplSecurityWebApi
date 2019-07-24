package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class AssignDutyDisplay {

		@Id
		private int assignId;
		private String assignDate;
		private int dutyId;
		private String empIds;
		private String notifyTime;
		private int taskAssignUserId;
		private String lastEditDate;
		private int lastEditUserId;
		private int delStatus;
		private Integer exInt1;
		private Integer exInt2;
		private Integer exInt3;
		private String exVar1;
		private String exVar2;
		private String exVar3;	
		
		private String dutyName;	
		private String shiftName;	
		private String shiftFromTime;	
		private String shiftToTime;	
		private String noOfHr;	
		private String taskAssignUserName;
		private String lastEditUserName;
		
		@Transient
		List<AssignDutyEmployee> empList; 
		
}
