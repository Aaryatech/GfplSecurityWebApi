package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class SaveDutyAndTask {
	
	private DutyHeader dutyHeader;
	private List<TaskDetail> taskDetailList;
	
	
}
