package com.ats.gfplsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.model.EmpGatepassDisplay;
import com.ats.gfplsecurity.model.EmpWiseVisitorReport;
import com.ats.gfplsecurity.model.MaterialGatepassDisplay;
import com.ats.gfplsecurity.model.VisitorGatepassDisplay;
import com.ats.gfplsecurity.repository.EmpGatepassDisplayRepo;
import com.ats.gfplsecurity.repository.EmpWiseVisitorReportRepo;
import com.ats.gfplsecurity.repository.MaterialGatepassDisplayRepo;
import com.ats.gfplsecurity.repository.VisitorGatepassDisplayRepository;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	EmpWiseVisitorReportRepo empWiseVisitorReportRepo;

	@Autowired
	VisitorGatepassDisplayRepository visitorGatepassDisplayRepository;

	@Autowired
	EmpGatepassDisplayRepo empGatepassDisplayRepo;

	@Autowired
	MaterialGatepassDisplayRepo materialGatepassDisplayRepo;

	
	@PostMapping("/getEmpWiseVisitorReport")
	public @ResponseBody List<EmpWiseVisitorReport> getEmpWiseVisitorReport(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "type") int type, @RequestParam(value = "empIds") String empIds,
			@RequestParam(value = "status") int status) {

		List<EmpWiseVisitorReport> resultList = null;

		try {

			if (empIds.equalsIgnoreCase("-1")) {

				System.out.println(" EMPIDS -------------------- IF");

				resultList = empWiseVisitorReportRepo.getEmpWiseReportAllEmp(fromDate, toDate, type, status);

			} else {

				System.out.println(" EMPIDS -------------------- ELSE"+empIds);

				resultList = empWiseVisitorReportRepo.getEmpWiseReport(fromDate, toDate, type, status, empIds);

			}
			System.err.println("emp ***********************"+resultList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	@PostMapping("/getVisitorGatepassReport")
	public @ResponseBody List<VisitorGatepassDisplay> getVisitorGatepassReport(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "gatepassType") List<Integer> gatepassType, @RequestParam(value = "empId") int empId,
			@RequestParam(value = "status") List<Integer> status) {

		List<VisitorGatepassDisplay> resultList = null;

		try {

			if (gatepassType.contains(-1)) {

				gatepassType.clear();
				gatepassType.add(1);
				gatepassType.add(2);

			}

			if (status.contains(-1)) {

				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);

			}

			resultList = visitorGatepassDisplayRepository.getVisitorGatepassReport(fromDate,toDate,gatepassType, empId, status);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	@PostMapping("/getEmpGatepassCountForSupervisorForReport")
	public @ResponseBody List<EmpWiseVisitorReport> getEmpGatepassCountForSupervisorForReport(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
		 @RequestParam(value = "deptIds") List<Integer> deptIds) {

		List<EmpWiseVisitorReport> resultList = null;

		try {

			if (deptIds.contains(-1)) {

				resultList = empWiseVisitorReportRepo.getEmpGatepassReportForAllSupervisor(fromDate, toDate);

			} else {

				resultList = empWiseVisitorReportRepo.getEmpGatepassReportForSupervisor(fromDate, toDate, deptIds);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// -- Emp Gatepass Report  By User
	@PostMapping("/getEmpGatepassReportByUser")
	public @ResponseBody List<EmpGatepassDisplay> getEmpGatepassReportByUser(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "userId") int userId) {

		List<EmpGatepassDisplay> resultList = null;

		resultList = empGatepassDisplayRepo.getEmpGPListByUserId(fromDate,toDate,userId);

		return resultList;

	}
	
	// -- Emp Gatepass Report  By Dept
		@PostMapping("/getEmpGatepassReportByDept")
		public @ResponseBody List<EmpGatepassDisplay> getEmpGatepassReportByDept(
				@RequestParam(value = "fromDate") String fromDate,
				@RequestParam(value = "toDate") String toDate,
				@RequestParam(value = "deptIds") List<Integer> deptIds,
				@RequestParam(value = "empIds") List<Integer> empIds) {

			List<EmpGatepassDisplay> resultList = null;
			
			if(deptIds.contains(-1) && empIds.contains(-1)) {
				
				resultList = empGatepassDisplayRepo.getEmpGPListByAllDeptAndAllEmp(fromDate,toDate);	
			
			}else if(!deptIds.contains(-1) && empIds.contains(-1)) {
				
				resultList = empGatepassDisplayRepo.getEmpGPListByDeptByAllEmp(fromDate,toDate,deptIds);	
				
			}else if(deptIds.contains(-1) && !empIds.contains(-1)) {
				
				resultList = empGatepassDisplayRepo.getEmpGPListByAllDeptAndByEmp(fromDate,toDate,empIds);	
				
			}else if(!deptIds.contains(-1) && !empIds.contains(-1)) {
				
				resultList = empGatepassDisplayRepo.getEmpGPListByDeptByEmp(fromDate,toDate,deptIds,empIds);	
				
			}

			

			return resultList;

		}
		

		// -- Material Gatepass Report
		@PostMapping("/getMaterialGatepassReport")
		public @ResponseBody List<MaterialGatepassDisplay> getMaterialGatepassReport(
				@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
				@RequestParam(value = "supIds") List<Integer> supIds) {

			List<MaterialGatepassDisplay> resultList = null;

			try {
				
				if (supIds.contains(-1)) {

					resultList = materialGatepassDisplayRepo.getMatTrackingReport(fromDate, toDate);

				} else if (!supIds.contains(-1)) {

					resultList = materialGatepassDisplayRepo.getMatTrackingReport(fromDate, toDate, supIds);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return resultList;

		}
		
		
	
		
		
		
		
		
		
		
}
