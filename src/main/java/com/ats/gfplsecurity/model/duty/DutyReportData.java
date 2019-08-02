package com.ats.gfplsecurity.model.duty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class DutyReportData {
	
	@Id
	private int type;
	private String dutyType;
	
	@Transient
	private List<DutyReportDutyList> dutyList;

}
