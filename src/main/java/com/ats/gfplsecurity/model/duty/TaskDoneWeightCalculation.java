package com.ats.gfplsecurity.model.duty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="task_done_weight_calculation")
public class TaskDoneWeightCalculation {
	
	@Id
	private String id;
	private int total;
	private int done_wt;
	private String percentage;
	
	

}
