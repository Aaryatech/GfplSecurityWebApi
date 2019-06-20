package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_task_done_detail")
public class TaskDoneDetail {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskDoneDetailId;
	private int taskDoneHeaderId;
	private int dutyId;
	private int taskId;
	private String taskName;
	private String taskDesc;
	private String completedDate;
	private String photo1;
	private String photo2;
	private String photo3;
	private String photo4;
	private String photo5;
	private String remark;
	private int taskWeight;
	private int taskStatus;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	
}
