package com.ats.gfplsecurity.model.duty;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DutyReport {
	
	@Id
	private String id;
	private int dutyId;
	private String dutyName;
	private int type;
	private String typeDesc;
	private int totalTaskWt;
	private int shiftId;
	private String shiftName;
	private String shiftFromTime;
	private String shiftToTime;
	private int taskId;
	private String taskNameEng;
	private String taskDescEng;
	private int taskWeight;
	private int photoReq;
	private int remarkReq;
	private int timeReq;
	private String taskTime;



}
