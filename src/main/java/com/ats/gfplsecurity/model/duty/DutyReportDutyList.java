package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class DutyReportDutyList {
	
	@Id
	private int dutyId;
	private String dutyName;
	private int type;
	private String typeDesc;
	private int totalTaskWt;
	private int shiftId;
	private String shiftName;
	private String shiftFromTime;
	private String shiftToTime;
	
	@Transient
	private List<DutyReportTask> taskList;

}
