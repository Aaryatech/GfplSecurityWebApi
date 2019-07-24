package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DutyWiseCount {

	@Id	
	private String id;
	private int taskDoneHeaderId;
	private int dutyId;
	private String dutyName;
	private Integer total;
	private Integer completed;
}
