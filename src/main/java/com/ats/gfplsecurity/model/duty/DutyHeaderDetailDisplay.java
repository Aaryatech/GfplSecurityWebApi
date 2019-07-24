package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class DutyHeaderDetailDisplay {

	@Id
	private int dutyId;
	private String dutyCode;
	private String dutyName;
	private int deptId;
	private int desgId;
	private int type;
	private String typeDesc;
	private int shiftId;
	private int createdBy;
	private String createdDate;
	private int totalTaskWt;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;

	private String deptName;
	private String desgName;
	private String shiftName;
	private String shiftFromTime;
	private String shiftToTime;
	private String noOfHr;
	private String createdByName;

	@Transient
	List<TaskDetailDisplay> taskDetailDisplay;

	

}
