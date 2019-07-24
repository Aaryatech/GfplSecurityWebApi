package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="task_done_detail_display")
public class TaskDoneDetailDisplay {

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
	
	private String taskNameEng;
	private String taskNameMar;
	private String taskNameHin;
	private String taskDescEng;
	private String taskDescMar;
	private String taskDescHin;
	private int photoReq;
	private int remarkReq;
	
	
}
