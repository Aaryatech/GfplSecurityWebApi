package com.ats.gfplsecurity.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.common.Firebase;
import com.ats.gfplsecurity.common.Info;
import com.ats.gfplsecurity.model.EmpGatepass;
import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.Purpose;
import com.ats.gfplsecurity.model.checklist.ChecklistActionDetail;
import com.ats.gfplsecurity.model.checklist.ChecklistActionHeader;
import com.ats.gfplsecurity.model.checklist.ChecklistAssign;
import com.ats.gfplsecurity.model.checklist.ChecklistAssignDisplay;
import com.ats.gfplsecurity.model.checklist.ChecklistCountReport;
import com.ats.gfplsecurity.model.checklist.ChecklistDetail;
import com.ats.gfplsecurity.model.checklist.ChecklistHeader;
import com.ats.gfplsecurity.model.checklist.ClosedChecklist;
import com.ats.gfplsecurity.model.checklist.OpenChecklist;
import com.ats.gfplsecurity.model.checklist.ReportEmpDetail;
import com.ats.gfplsecurity.model.checklist.ReportEmpWise;
import com.ats.gfplsecurity.repository.EmployeeRepository;
import com.ats.gfplsecurity.repository.checklist.ChecklistActionDetailRepo;
import com.ats.gfplsecurity.repository.checklist.ChecklistActionHeaderRepo;
import com.ats.gfplsecurity.repository.checklist.ChecklistAssignDisplayRepo;
import com.ats.gfplsecurity.repository.checklist.ChecklistAssignRepo;
import com.ats.gfplsecurity.repository.checklist.ChecklistCountReportRepo;
import com.ats.gfplsecurity.repository.checklist.ChecklistDetailRepo;
import com.ats.gfplsecurity.repository.checklist.ChecklistHeaderRepo;
import com.ats.gfplsecurity.repository.checklist.ClosedChecklistRepo;
import com.ats.gfplsecurity.repository.checklist.OpenChecklistRepo;
import com.ats.gfplsecurity.repository.checklist.ReportEmpDetailRepo;
import com.ats.gfplsecurity.repository.checklist.ReportEmpWiseRepo;

@RestController
@RequestMapping("/checklist")
public class ChecklistController {

	@Autowired
	ChecklistHeaderRepo checklistHeaderRepo;

	@Autowired
	ChecklistDetailRepo checklistDetailRepo;

	@Autowired
	ChecklistAssignRepo checklistAssignRepo;

	@Autowired
	ChecklistActionHeaderRepo checklistActionHeaderRepo;

	@Autowired
	ChecklistActionDetailRepo checklistActionDetailRepo;

	@Autowired
	ChecklistAssignDisplayRepo checklistAssignDisplayRepo;

	@Autowired
	OpenChecklistRepo openChecklistRepo;

	@Autowired
	ClosedChecklistRepo closedChecklistRepo;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ChecklistCountReportRepo checklistCountReportRepo;

	@Autowired
	ReportEmpWiseRepo reportEmpWiseRepo;

	@Autowired
	ReportEmpDetailRepo reportEmpDetailRepo;

	// ------------CHECKLIST HEADER CRUD------------------

	@GetMapping("/allChecklistHeader")
	public List<ChecklistHeader> getAllChecklistHeader() {
		return checklistHeaderRepo.findAllByDelStatus(1);
	}

	@GetMapping("/allChecklistHeaderByIsUsed")
	public List<ChecklistHeader> getAllChecklistHeaderByIsUsed() {
		return checklistHeaderRepo.findAllByDelStatusAndIsUsed(1, 1);
	}

	@PostMapping("/saveChecklistHeader")
	public ChecklistHeader saveChecklistHeader(@RequestBody ChecklistHeader checklistHeader) {
		return checklistHeaderRepo.save(checklistHeader);
	}

	@PostMapping("/deleteChecklistHeader")
	public Info deleteChecklistHeader(@RequestParam(value = "checklistHeaderId") int checklistHeaderId) {
		Info info = null;

		ChecklistHeader checklistHeader = checklistHeaderRepo.findByChecklistHeaderId(checklistHeaderId);

		if (checklistHeader != null) {
			checklistHeader.setDelStatus(0);
			checklistHeaderRepo.save(checklistHeader);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	@PostMapping("/updateIsUsedChecklistHeader")
	public Info updateIsUsedChecklistHeader(@RequestParam(value = "checklistHeaderId") int checklistHeaderId,
			@RequestParam(value = "isUsed") int isUsed) {
		Info info = null;

		ChecklistHeader checklistHeader = checklistHeaderRepo.findByChecklistHeaderId(checklistHeaderId);

		if (checklistHeader != null) {
			checklistHeader.setIsUsed(isUsed);
			checklistHeaderRepo.save(checklistHeader);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	@PostMapping("/getAllChecklistHeaderAndDetail")
	public List<ChecklistHeader> getAllChecklistHeaderAndDetail() {

		List<ChecklistHeader> headerList = new ArrayList<>();

		headerList = checklistHeaderRepo.findAllByDelStatus(1);

		if (headerList != null) {
			if (headerList.size() > 0) {
				for (int i = 0; i < headerList.size(); i++) {

					List<ChecklistDetail> detailList = checklistDetailRepo.findAllByDelStatusAndChecklistHeaderId(1,
							headerList.get(i).getChecklistHeaderId());

					headerList.get(i).setChecklistDetail(detailList);
				}
			}

		}

		return headerList;
	}

	@PostMapping("/getAllChecklistByDept")
	public List<ChecklistHeader> getAllChecklistByDept(@RequestParam(value = "deptId") int deptId) {

		List<ChecklistHeader> headerList = new ArrayList<>();

		headerList = checklistHeaderRepo.findAllByDelStatusAndIsUsedAndDeptId(1, 1, deptId);

		if (headerList != null) {
			if (headerList.size() > 0) {
				for (int i = 0; i < headerList.size(); i++) {

					List<ChecklistDetail> detailList = checklistDetailRepo.findAllByDelStatusAndChecklistHeaderId(1,
							headerList.get(i).getChecklistHeaderId());

					headerList.get(i).setChecklistDetail(detailList);
				}
			}

		}

		return headerList;
	}
	
	
	@PostMapping("/getAllChecklistByDeptAndUnAssigned")
	public List<ChecklistHeader> getAllChecklistByDeptAndUnAssigned(@RequestParam(value = "deptId") int deptId) {

		List<ChecklistHeader> headerList = new ArrayList<>();

		headerList = checklistHeaderRepo.getUnAssignedChecklistByDept(deptId);

		if (headerList != null) {
			if (headerList.size() > 0) {
				for (int i = 0; i < headerList.size(); i++) {

					List<ChecklistDetail> detailList = checklistDetailRepo.findAllByDelStatusAndChecklistHeaderId(1,
							headerList.get(i).getChecklistHeaderId());

					headerList.get(i).setChecklistDetail(detailList);
				}
			}

		}

		return headerList;
	}

	
	

	// ------------CHECKLIST DETAIL CRUD------------------

	@GetMapping("/allChecklistDetail")
	public List<ChecklistDetail> getAllChecklistDetail() {
		return checklistDetailRepo.findAllByDelStatus(1);
	}

	@GetMapping("/allChecklistDetailByIsUsed")
	public List<ChecklistDetail> getAllChecklistDetailByIsUsed() {
		return checklistDetailRepo.findAllByDelStatusAndIsUsed(1, 1);
	}

	@PostMapping("/saveChecklistDetail")
	public ChecklistDetail saveChecklistDetail(@RequestBody ChecklistDetail checklistDetail) {
		return checklistDetailRepo.save(checklistDetail);
	}

	@PostMapping("/getAllChecklistDetailByHeaderId")
	public List<ChecklistDetail> getAllChecklistDetailByHeaderId(
			@RequestParam(value = "checklistHeaderId") int checklistHeaderId) {
		return checklistDetailRepo.findAllByDelStatusAndChecklistHeaderId(1, checklistHeaderId);
	}

	@PostMapping("/deleteChecklistDetail")
	public Info deleteChecklistDetail(@RequestParam(value = "checklistDetailId") int checklistDetailId) {
		Info info = null;

		ChecklistDetail checklistDetail = checklistDetailRepo.findByChecklistDetailId(checklistDetailId);

		if (checklistDetail != null) {
			checklistDetail.setDelStatus(0);
			checklistDetailRepo.save(checklistDetail);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	@PostMapping("/updateIsUsedChecklistDetail")
	public Info updateIsUsedChecklistDetail(@RequestParam(value = "checklistDetailId") int checklistDetailId,
			@RequestParam(value = "isUsed") int isUsed) {
		Info info = null;

		ChecklistDetail checklistDetail = checklistDetailRepo.findByChecklistDetailId(checklistDetailId);

		if (checklistDetail != null) {
			checklistDetail.setIsUsed(isUsed);
			checklistDetailRepo.save(checklistDetail);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	// ------------CHECKLIST HEADER & DETAIL SAVE------------------

	@PostMapping("/saveChecklistHeaderAndDetail")
	public ChecklistHeader saveChecklistHeaderAndDetail(@RequestBody ChecklistHeader checklistHeader) {

		ChecklistHeader header = checklistHeaderRepo.save(checklistHeader);
		if (header != null) {

			if (checklistHeader.getChecklistDetail() != null) {
				if (checklistHeader.getChecklistDetail().size() > 0) {
					for (int i = 0; i < checklistHeader.getChecklistDetail().size(); i++) {
						checklistHeader.getChecklistDetail().get(i).setChecklistHeaderId(header.getChecklistHeaderId());
					}
					checklistDetailRepo.saveAll(checklistHeader.getChecklistDetail());
				}
			}

		}
		return header;
	}

	// ------------CHECKLIST ASSIGN CRUD------------------

	@GetMapping("/allAssignedChecklist")
	public List<ChecklistAssign> getAllAssignedChecklist() {
		return checklistAssignRepo.findAllByDelStatus(1);
	}

	@PostMapping("/saveChecklistAssign")
	public ChecklistAssign saveChecklistAssign(@RequestBody ChecklistAssign checklistAssign) {
		return checklistAssignRepo.save(checklistAssign);
	}

	@GetMapping("/getAllAssignedChecklistDisplay")
	public List<ChecklistAssignDisplay> getAllAssignedChecklistDisplay() {
		return checklistAssignDisplayRepo.getChecklistAssignDisplaylist();
	}

	@PostMapping("/deleteChecklistAssign")
	public Info deleteChecklistAssign(@RequestParam(value = "assignId") int assignId) {
		Info info = null;

		ChecklistAssign checklistAssign = checklistAssignRepo.findByAssignId(assignId);

		if (checklistAssign != null) {
			checklistAssign.setDelStatus(0);
			checklistAssignRepo.save(checklistAssign);
			info = new Info();
			info.setError(false);
			info.setMessage("Success");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	// ------------CHECKLIST ACTION HEADER CRUD------------------

	@PostMapping("/saveChecklistActionHeader")
	public ChecklistActionHeader saveChecklistActionHeader(@RequestBody ChecklistActionHeader checklistActionHeader) {
		return checklistActionHeaderRepo.save(checklistActionHeader);
	}

	// ------------CHECKLIST ACTION DETAIL CRUD------------------

	@PostMapping("/saveChecklistActionDetail")
	public ChecklistActionDetail saveChecklistActionDetail(@RequestBody ChecklistActionDetail checklistActionDetail) {
		return checklistActionDetailRepo.save(checklistActionDetail);
	}
	
	@PostMapping("/getChecklistActionDetail")
	public List<ChecklistActionDetail> getChecklistActionDetail(@RequestParam(value = "headerId") int headerId) {
		return checklistActionDetailRepo.findAllByDelStatusAndActionHeaderId(1,headerId);
	}

	// ------------CHECKLIST ACTION HEADER & DETAIL SAVE------------------

	@PostMapping("/saveChecklistActionHeaderAndDetail")
	public ChecklistActionHeader saveChecklistActionHeaderAndDetail(
			@RequestBody ChecklistActionHeader checklistActionHeader) {

		ChecklistActionHeader header = checklistActionHeaderRepo.save(checklistActionHeader);
		if (header != null) {

			if (checklistActionHeader.getChecklistActionDetail() != null) {
				if (checklistActionHeader.getChecklistActionDetail().size() > 0) {
					for (int i = 0; i < checklistActionHeader.getChecklistActionDetail().size(); i++) {
						checklistActionHeader.getChecklistActionDetail().get(i)
								.setActionHeaderId(header.getActionHeaderId());
					}
					checklistActionDetailRepo.saveAll(checklistActionHeader.getChecklistActionDetail());
				}
			}

			ChecklistAssign chkAssign = checklistAssignRepo.findByAssignId(header.getAssignId());
			if (chkAssign != null) {

				String empIds = chkAssign.getAssignEmpIds();

				List<String> empIdList = Stream.of(empIds.split(",", -1)).collect(Collectors.toList());

				if (empIdList != null) {
					if (empIdList.size() > 0) {
						for (int i = 0; i < empIdList.size(); i++) {

							if (!empIdList.get(i).equalsIgnoreCase(String.valueOf(header.getActionBy()))) {

								try {

									Employee emp = employeeRepository
											.findByEmpIdAndDelStatus(Integer.parseInt(empIdList.get(i)), 1);

									if (emp.getExVar1() != null) {

										Firebase.sendPushNotifForCommunication(emp.getExVar1(), "Checklist Task Open",
												"" + emp.getEmpFname() + " " + emp.getEmpSname()
														+ " has opened the task on " + header.getActionDate(),
												"100");
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}
					}
				}

			}

		}
		return header;
	}

	// ------------OPEN CHECKLIST BY EMP ID------------------

	@PostMapping("/getOpenChecklistByEmp")
	public List<OpenChecklist> getOpenChecklistByEmp(@RequestParam(value = "empId") int empId) {

		List<OpenChecklist> openList = new ArrayList<>();
		openList = openChecklistRepo.getOpenChecklist(empId);

		if (openList != null) {
			if (openList.size() > 0) {
				for (int i = 0; i < openList.size(); i++) {

					List<ChecklistDetail> detailArray = checklistDetailRepo
							.findAllByDelStatusAndIsUsedAndChecklistHeaderId(1, 1,
									openList.get(i).getChecklistHeaderId());
					openList.get(i).setDetailList(detailArray);
				}
			}
		}

		return openList;

	}

	// ------------CLOSED CHECKLIST BY EMP ID------------------

	@PostMapping("/getClosedChecklistByEmp")
	public List<ClosedChecklist> getClosedChecklistByEmp(@RequestParam(value = "empId") int empId) {

		List<ClosedChecklist> closedList = new ArrayList<>();
		closedList = closedChecklistRepo.getClosedChecklist(empId);

		if (closedList != null) {
			if (closedList.size() > 0) {
				for (int i = 0; i < closedList.size(); i++) {

					List<ChecklistActionDetail> detailArray = checklistActionDetailRepo
							.findAllByDelStatusAndActionHeaderId(1, closedList.get(i).getActionHeaderId());
					closedList.get(i).setActionDetailList(detailArray);
				}
			}
		}

		return closedList;

	}

	// ------------UPDATE CLOSED CHECKLIST DETAIL------------------

	@PostMapping("/updateClosedChecklistDetailStatus")
	public Info updateClosedChecklistDetailStatus(@RequestParam(value = "actionDetailId") int actionDetailId,
			@RequestParam(value = "status") int status, @RequestParam(value = "photo") String photo) {

		Info info = new Info();

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cal = Calendar.getInstance();

			String date = sdf.format(cal.getTimeInMillis());

			int res = checklistActionDetailRepo.updateClosedDetailStatus(actionDetailId, status, photo, date);
			if (res > 0) {

				info.setError(false);
				info.setMessage("success");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("Failed");
		}

		return info;

	}

	// ------------UPDATE CLOSED CHECKLIST HEADER------------------

	@PostMapping("/updateClosedChecklistHeaderStatus")
	public Info updateClosedChecklistHeaderStatus(@RequestParam(value = "actionHeaderId") int actionHeaderId,
			@RequestParam(value = "status") int status, @RequestParam(value = "empId") int empId) {

		Info info = new Info();

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar cal = Calendar.getInstance();

			String date = sdf.format(cal.getTimeInMillis());
			String datetime = sdf1.format(cal.getTimeInMillis());

			int res = checklistActionHeaderRepo.updateClosedHeaderStatus(actionHeaderId, status, empId, date, datetime);
			if (res > 0) {

				info.setError(false);
				info.setMessage("success");

				ChecklistActionHeader header = checklistActionHeaderRepo.findByActionHeaderId(actionHeaderId);

				try {

					Employee empActionBy = employeeRepository.findByEmpIdAndDelStatus(header.getActionBy(), 1);

					Employee empClosedBy = employeeRepository.findByEmpIdAndDelStatus(header.getClosedBy(), 1);

					if (empActionBy.getExVar1() != null) {

						Firebase.sendPushNotifForCommunication(empActionBy.getExVar1(), "Checklist Task Closed",
								"" + empClosedBy.getEmpFname() + " " + empClosedBy.getEmpSname()
										+ " has closed the task on " + header.getClosedDate(),
								"100");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				info.setError(true);
				info.setMessage("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("Failed");
		}

		return info;

	}
	
	
	// ------------UPDATE CLOSED CHECKLIST DETAIL PHOTO------------------

		@PostMapping("/updateClosedChecklistDetailPhoto")
		public Info updateClosedChecklistDetailPhoto(@RequestParam(value = "actionDetailId") int actionDetailId,
				 @RequestParam(value = "photo") String photo) {

			Info info = new Info();

			try {


				int res = checklistActionDetailRepo.updateClosedDetailPhoto(actionDetailId, photo);
				if (res > 0) {

					info.setError(false);
					info.setMessage("success");
				} else {
					info.setError(true);
					info.setMessage("Failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("Failed");
			}

			return info;

		}
		
		// ------------UPDATE CLOSED CHECKLIST DETAIL MULTIPLE IDS------------------

		@PostMapping("/updateClosedChecklistDetailStatusMultiple")
		public Info updateClosedChecklistDetailStatusMultiple(@RequestParam(value = "actionDetailId") List<Integer> actionDetailId,
				@RequestParam(value = "status") int status) {

			Info info = new Info();

			try {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Calendar cal = Calendar.getInstance();

				String date = sdf.format(cal.getTimeInMillis());

				int res = checklistActionDetailRepo.updateClosedDetailStatusMultiple(actionDetailId, status, date);
				if (res > 0) {

					info.setError(false);
					info.setMessage("success");
				} else {
					info.setError(true);
					info.setMessage("Failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("Failed");
			}

			return info;

		}
	

	// ------------REPORT CHECKLIST ALL DEPT------------------

	@PostMapping("/getReportByDeptGroupBy")
	public List<ChecklistCountReport> getReportByDeptGroupBy(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) {

		List<ChecklistCountReport> reportList = new ArrayList<>();
		reportList = checklistCountReportRepo.getReportByDeptGroupBy(fromDate, toDate);

		return reportList;

	}

	// ------------REPORT CHECKLIST HEADER GROUP BY------------------

	@PostMapping("/getReportByChkHeaderGroupBy")
	public List<ChecklistCountReport> getReportByChkHeaderGroupBy(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate, @RequestParam(value = "headerId") List<Integer> headerId) {

		List<ChecklistCountReport> reportList = new ArrayList<>();
		reportList = checklistCountReportRepo.getReportByChecklistHeaderGroupBy(fromDate, toDate, headerId);

		return reportList;

	}

	// ------------REPORT CHECKLIST HEADER ------------------

	@PostMapping("/getReportByChkHeader")
	public List<ChecklistCountReport> getReportByChkHeader(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate, @RequestParam(value = "headerId") int headerId) {

		List<ChecklistCountReport> reportList = new ArrayList<>();
		reportList = checklistCountReportRepo.getReportByHeader(fromDate, toDate, headerId);

		return reportList;

	}

	// ------------REPORT CHECKLIST EMP WISE ------------------

	@PostMapping("/getReportEmpWise")
	public List<ReportEmpWise> getReportEmpWise(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate, @RequestParam(value = "deptId") List<Integer> deptId) {

		List<ReportEmpWise> reportList = new ArrayList<>();
		reportList = reportEmpWiseRepo.getReportEmpWise(fromDate, toDate, deptId);

		return reportList;

	}

	
	// ------------REPORT CHECKLIST EMP WISE ------------------

	@PostMapping("/getReportEmpDetail")
	public List<ReportEmpDetail> getReportEmpDetail(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate, @RequestParam(value = "empId") int empId) {

		List<ReportEmpDetail> reportList = new ArrayList<>();
		reportList = reportEmpDetailRepo.getReportEmpDetail(fromDate, toDate, empId);

		return reportList;

	}

}
