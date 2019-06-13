package com.ats.gfplsecurity.model;


import lombok.Data;

@Data
public class DashboardCount {

	private VisAndMaintGatepassCount visAndMaintGatepassCount;
	private EmpGatepassCount empGatepassCount; 
	private SupGatepassCount supGatepassCount; 
	private MatGatepassCount matGatepassCount;
	private MatGatepassEmpWiseCount matGatepassEmpWiseCount;
	

}
