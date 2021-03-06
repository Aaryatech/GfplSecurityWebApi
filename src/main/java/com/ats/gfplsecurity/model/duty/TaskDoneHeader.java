package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_task_done_header")
public class TaskDoneHeader {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	
}
