package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class VisAndMaintGatepassCount {

	@Id
	private String id;
	private int visitor_pending;
	private int visitor_approved;
	private int visitor_rejected;
	private int visitor_completed;
	private int visitor_in_comp;
	private int visitor_total;

	private int emp_visitor_pending;
	private int emp_visitor_approved;
	private int emp_visitor_rejected;
	private int emp_visitor_completed;
	private int emp_visitor_total;

	private int maint_pending;
	private int maint_approved;
	private int maint_rejected;
	private int maint_completed;
	private int maint_total;


}
