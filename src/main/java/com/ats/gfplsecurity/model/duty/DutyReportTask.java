package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DutyReportTask {

	@Id
	private int taskId;
	private String taskNameEng;
	private String taskDescEng;
	private int taskWeight;
	private int photoReq;
	private int remarkReq;
	private int timeReq;
	private String taskTime;
	
}
 