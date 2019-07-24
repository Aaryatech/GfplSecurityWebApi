package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity(name="task_done_header_display")
public class TaskDoneHeaderDisplay {
	
	@Id
	private int taskDoneHeaderId;
	private String taskDate;
	private int dutyId;
	private int empId;
	private int empIdOld;
	private String taskShiftRemark;
	private int taskShiftUserId;
	private int taskAssignUserId;
	private int delStatus;
	private int status;
	private int dutyWeight;
	private int taskCompleteWt;
	private String completionPercent;
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

	
	@Transient
	List<TaskDoneDetailDisplay> taskDoneDetailDisplayList;

}
