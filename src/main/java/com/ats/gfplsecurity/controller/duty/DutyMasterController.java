package com.ats.gfplsecurity.controller.duty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.common.Firebase;
import com.ats.gfplsecurity.common.Info;
import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.duty.AssignDuty;
import com.ats.gfplsecurity.model.duty.AssignDutyDisplay;
import com.ats.gfplsecurity.model.duty.AssignDutyEmployee;
import com.ats.gfplsecurity.model.duty.DeptWiseCount;
import com.ats.gfplsecurity.model.duty.DutyHeader;
import com.ats.gfplsecurity.model.duty.DutyHeaderDetailDisplay;
import com.ats.gfplsecurity.model.duty.DutyReport;
import com.ats.gfplsecurity.model.duty.DutyReportData;
import com.ats.gfplsecurity.model.duty.DutyReportDutyList;
import com.ats.gfplsecurity.model.duty.DutyReportTask;
import com.ats.gfplsecurity.model.duty.DutyWiseCount;
import com.ats.gfplsecurity.model.duty.EmpWiseCount;
import com.ats.gfplsecurity.model.duty.SaveDutyAndTask;
import com.ats.gfplsecurity.model.duty.Shift;
import com.ats.gfplsecurity.model.duty.TaskDetail;
import com.ats.gfplsecurity.model.duty.TaskDetailDisplay;
import com.ats.gfplsecurity.model.duty.TaskDoneDetail;
import com.ats.gfplsecurity.model.duty.TaskDoneDetailDisplay;
import com.ats.gfplsecurity.model.duty.TaskDoneHeader;
import com.ats.gfplsecurity.model.duty.TaskDoneHeaderDisplay;
import com.ats.gfplsecurity.model.duty.TaskDoneWeightCalculation;
import com.ats.gfplsecurity.repository.EmpWiseCountRepo;
import com.ats.gfplsecurity.repository.EmployeeRepository;
import com.ats.gfplsecurity.repository.duty.AssignDutyDisplayRepo;
import com.ats.gfplsecurity.repository.duty.AssignDutyEmployeeRepo;
import com.ats.gfplsecurity.repository.duty.AssignDutyRepo;
import com.ats.gfplsecurity.repository.duty.DeptWiseCountRepo;
import com.ats.gfplsecurity.repository.duty.DutyHeaderDetailDisplayRepo;
import com.ats.gfplsecurity.repository.duty.DutyHeaderRepo;
import com.ats.gfplsecurity.repository.duty.DutyReportRepo;
import com.ats.gfplsecurity.repository.duty.DutyWiseCountRepo;
import com.ats.gfplsecurity.repository.duty.ShiftRepo;
import com.ats.gfplsecurity.repository.duty.TaskDetailDisplayRepo;
import com.ats.gfplsecurity.repository.duty.TaskDetailRepo;
import com.ats.gfplsecurity.repository.duty.TaskDoneDetailDisplayRepo;
import com.ats.gfplsecurity.repository.duty.TaskDoneDetailRepo;
import com.ats.gfplsecurity.repository.duty.TaskDoneHeaderDisplayRepo;
import com.ats.gfplsecurity.repository.duty.TaskDoneHeaderRepo;
import com.ats.gfplsecurity.repository.duty.TaskDoneWeightCalculationRepo;

@RestController
@RequestMapping("/duty/master")
public class DutyMasterController {

	@Autowired
	DutyHeaderRepo dutyHeaderRepo;

	@Autowired
	TaskDetailRepo taskDetailRepo;

	@Autowired
	ShiftRepo shiftRepo;

	@Autowired
	AssignDutyRepo assignDutyRepo;

	@Autowired
	TaskDoneHeaderRepo taskDoneHeaderRepo;

	@Autowired
	TaskDoneDetailRepo taskDoneDetailRepo;

	@Autowired
	DutyHeaderDetailDisplayRepo dutyHeaderDetailDisplayRepo;

	@Autowired
	TaskDetailDisplayRepo taskDetailDisplayRepo;

	@Autowired
	AssignDutyDisplayRepo assignDutyDisplayRepo;

	@Autowired
	AssignDutyEmployeeRepo assignDutyEmployeeRepo;

	@Autowired
	TaskDoneHeaderDisplayRepo taskDoneHeaderDisplayRepo;

	@Autowired
	TaskDoneDetailDisplayRepo taskDoneDetailDisplayRepo;

	@Autowired
	TaskDoneWeightCalculationRepo taskDoneWeightCalculationRepo;

	@Autowired
	DeptWiseCountRepo deptWiseCountRepo;

	@Autowired
	EmpWiseCountRepo empWiseCountRepo;

	@Autowired
	DutyWiseCountRepo dutyWiseCountRepo;

	@Autowired
	DutyReportRepo dutyReportRepo;
	
	@Autowired
	EmployeeRepository employeeRepository;

	// -----------------------DUTY CRUD------------------------------------

	// --Save new DUTY--
	@PostMapping("/saveDuty")
	public DutyHeader saveDuty(@RequestBody DutyHeader dutyHeader) {
		DutyHeader duty = null;

		duty = dutyHeaderRepo.save(dutyHeader);

		if (duty == null) {
			duty = new DutyHeader();
		}

		return duty;
	}

	// --Get Duty By Id--
	@PostMapping("/getDutyById")
	public DutyHeader getDutyById(@RequestParam(value = "dutyId") int dutyId) {
		DutyHeader duty = null;
		duty = dutyHeaderRepo.findByDutyId(dutyId);

		if (duty == null) {
			duty = new DutyHeader();
		}

		return duty;

	}

	// --Get all Duty List--
	@GetMapping("/allDuty")
	public List<DutyHeader> getAllDuty() {

		List<DutyHeader> dutyList = null;
		dutyList = dutyHeaderRepo.findAllByDelStatus(1);

		if (dutyList == null) {
			dutyList = new ArrayList<>();
		}

		return dutyList;
	}

	// --Delete Duty--
	@PostMapping("/deleteDuty")
	public Info deleteDuty(@RequestParam(value = "dutyId") int dutyId, @RequestParam(value = "status") int status) {
		Info info = null;

		DutyHeader dutyHeader = dutyHeaderRepo.findByDutyId(dutyId);

		if (dutyHeader != null) {
			dutyHeader.setDelStatus(status);
			DutyHeader updatedDuty = dutyHeaderRepo.save(dutyHeader);

			if (updatedDuty != null) {

				AssignDuty assignDuty = assignDutyRepo.findByDutyId(dutyId);
				if (assignDuty != null) {
					deleteAssignDuty(assignDuty.getAssignId(), status);
				}
				info = new Info();
				info.setError(false);
				info.setMessage("Success");
			} else {
				info = new Info();
				info.setError(true);
				info.setMessage("Failed");
			}

		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	// --Get all Duty List--
	@GetMapping("/allDutyHeaderDetail")
	public List<DutyHeaderDetailDisplay> getAllDutyHeaderDetail() {

		List<DutyHeaderDetailDisplay> result = null;

		try {

			result = dutyHeaderDetailDisplayRepo.getAllDutyHeader();

			if (result != null) {

				for (int i = 0; i < result.size(); i++) {

					List<TaskDetailDisplay> taskList = new ArrayList<>();

					taskList = taskDetailDisplayRepo.getAllTaskByDutyHeaderId(result.get(i).getDutyId());

					result.get(i).setTaskDetailDisplay(taskList);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// --Get all Duty List--
	@GetMapping("/allDutyHeaderDetailByDept")
	public List<DutyHeaderDetailDisplay> getAllDutyHeaderDetailByDept(
			@RequestParam(value = "deptId") List<Integer> deptId) {

		List<DutyHeaderDetailDisplay> result = null;

		try {

			if (deptId.contains(-1)) {
				result = dutyHeaderDetailDisplayRepo.getAllDutyHeader();
			} else {
				result = dutyHeaderDetailDisplayRepo.getAllDutyHeaderByDept(deptId);
			}

			if (result != null) {

				for (int i = 0; i < result.size(); i++) {

					List<TaskDetailDisplay> taskList = new ArrayList<>();

					taskList = taskDetailDisplayRepo.getAllTaskByDutyHeaderId(result.get(i).getDutyId());

					result.get(i).setTaskDetailDisplay(taskList);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// -----------SAVE DUTY AND TASK HEADER - DETAIL-----------------------------

	// --Save new DUTY--
	@PostMapping("/saveDutyAndTask")
	public Info saveDutyAndTaskData(@RequestBody SaveDutyAndTask saveDutyAndTask) {

		Info info = null;

		try {

			System.err.println("Parameter --------- --------------------- " + saveDutyAndTask);

			List<TaskDetail> taskDetailList = null;
			taskDetailList = saveDutyAndTask.getTaskDetailList();

			if (taskDetailList != null) {

				if (taskDetailList.size() > 0) {
					int totalWt = 0;
					for (int i = 0; i < taskDetailList.size(); i++) {
						totalWt = totalWt + taskDetailList.get(i).getTaskWeight();
					}

					saveDutyAndTask.getDutyHeader().setTotalTaskWt(totalWt);
				}
			}

			DutyHeader duty = null;
			duty = dutyHeaderRepo.save(saveDutyAndTask.getDutyHeader());

			if (duty != null) {

				if (saveDutyAndTask.getDutyHeader().getDutyId() > 0) {

					List<AssignDuty> aDutyList = null;
					aDutyList = assignDutyRepo.findAllByDelStatusAndDutyId(1, duty.getDutyId());

					if (aDutyList != null) {
						if (aDutyList.size() == 0) {
							AssignDuty aDuty = new AssignDuty(0, duty.getCreatedDate(), duty.getDutyId(), "", "",
									duty.getCreatedBy(), duty.getCreatedDate(), duty.getCreatedBy(), 1, 0, 0, 0, "na",
									"na", "na");
							AssignDuty assignDuty = assignDutyRepo.save(aDuty);

						}
					} else {
						AssignDuty aDuty = new AssignDuty(0, duty.getCreatedDate(), duty.getDutyId(), "", "",
								duty.getCreatedBy(), duty.getCreatedDate(), duty.getCreatedBy(), 1, 0, 0, 0, "na", "na",
								"na");
						AssignDuty assignDuty = assignDutyRepo.save(aDuty);
					}

				}

				if (saveDutyAndTask.getTaskDetailList().size() > 0) {
					for (int i = 0; i < saveDutyAndTask.getTaskDetailList().size(); i++) {

						// TaskDetail detail=saveDutyAndTask.getTaskDetailList().get(i);
						// detail.setDutyId(duty.getDutyId());

						saveDutyAndTask.getTaskDetailList().get(i).setDutyId(duty.getDutyId());

					}
					taskDetailRepo.saveAll(saveDutyAndTask.getTaskDetailList());
					info = new Info();
					info.setError(false);
					info.setMessage("Success");
				}

			} else {
				info = new Info();
				info.setError(false);
				info.setMessage("Success");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}

	// -----------------------TASK CRUD------------------------------------

	// --Save Task--
	@PostMapping("/saveTask")
	public TaskDetail saveTask(@RequestBody TaskDetail taskDetail) {

		TaskDetail result = taskDetailRepo.save(taskDetail);

		if (result != null) {
			float total = taskDetailRepo.getSumOfTaskWt(taskDetail.getDutyId());
			dutyHeaderRepo.updateTotalWt(taskDetail.getDutyId(), total);
		}

		return result;
	}

	// --Get Task By Id--
	@PostMapping("/getTaskById")
	public TaskDetail getTaskById(@RequestParam(value = "taskId") int taskId) {
		TaskDetail detail = null;
		detail = taskDetailRepo.findByTaskId(taskId);

		if (detail == null) {
			detail = new TaskDetail();
		}

		return detail;
	}

	// --Get all Duty List--
	@GetMapping("/allTask")
	public List<TaskDetail> getAllTask() {
		return taskDetailRepo.findAllByDelStatus(1);
	}

	// --Delete Duty--
	@PostMapping("/deleteTask")
	public Info deleteTask(@RequestParam(value = "taskId") int taskId) {
		Info info = null;

		TaskDetail taskDetail = taskDetailRepo.findByTaskId(taskId);

		if (taskDetail != null) {
			taskDetail.setDelStatus(0);
			TaskDetail updatedTask = taskDetailRepo.save(taskDetail);
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

	@PostMapping("/allTaskByDuty")
	public List<TaskDetail> getAllTask(@RequestParam(value = "dutyId") int dutyId) {

		List<TaskDetail> detailList = null;

		detailList = taskDetailRepo.findAllByDelStatusAndDutyIdOrderByTaskWeightDesc(1, dutyId);

		if (detailList == null) {
			detailList = new ArrayList();
		}

		return detailList;
	}

	// -----------------------SHIFT CRUD------------------------------------

	// --Save Shift--
	@PostMapping("/saveShift")
	public Shift saveShift(@RequestBody Shift shift) {
		System.err.println("PARAMETER ----------------------------------  " + shift);
		return shiftRepo.save(shift);
	}

	// --Get Shift By Id--
	@PostMapping("/getShiftById")
	public Shift getShiftById(@RequestParam(value = "shiftId") int shiftId) {
		return shiftRepo.findByShiftId(shiftId);
	}

	// --Get all Shift List--
	@GetMapping("/allShift")
	public List<Shift> getAllShift() {
		return shiftRepo.findAllByDelStatus(1);
	}

	// --Delete Shift--
	@PostMapping("/deleteShift")
	public Info deleteShift(@RequestParam(value = "shiftId") int shiftId) {
		Info info = null;

		Shift shift = shiftRepo.findByShiftId(shiftId);

		if (shift != null) {
			shift.setDelStatus(0);
			Shift updatedShift = shiftRepo.save(shift);
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

	// -----------------------ASSIGN DUTY CRUD------------------------------------

	// --Save Assign Duty--
	@PostMapping("/saveAssignDuty")
	public AssignDuty saveAssignDuty(@RequestBody AssignDuty assignDuty) {

		AssignDuty duty = null;
		duty = assignDutyRepo.save(assignDuty);
		if (duty == null) {
			duty = new AssignDuty();
		}

		return duty;
	}

	// --Get Assign Duty By Id--
	@PostMapping("/getAssignDutyById")
	public AssignDuty getAssignDutyById(@RequestParam(value = "assignId") int assignId) {
		return assignDutyRepo.findByAssignId(assignId);
	}

	// --Get all Assign Duty List--
	@GetMapping("/allAssignDuty")
	public List<AssignDuty> getAllAssignDuty(@RequestParam(value = "delStatus") int delStatus) {
		List<AssignDuty> result = null;
		result = assignDutyRepo.findAllByDelStatus(delStatus);
		return result;
	}

	// --Delete Assign Duty--
	@PostMapping("/deleteAssignDuty")
	public Info deleteAssignDuty(@RequestParam(value = "assignId") int assignId,
			@RequestParam(value = "status") int status) {
		Info info = null;

		AssignDuty assignDuty = assignDutyRepo.findByAssignId(assignId);

		if (assignDuty != null) {
			assignDuty.setDelStatus(status);
			AssignDuty updated = assignDutyRepo.save(assignDuty);
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

	// --Get Assign Duty By Duty Id--
	@PostMapping("/getAssignDutyByDutyId")
	public AssignDutyDisplay getAssignDutyByDutyId(@RequestParam(value = "dutyId") int dutyId) {

		AssignDutyDisplay result = null;

		try {

			result = assignDutyDisplayRepo.getAssignDutyByDutyId(dutyId);

			if (result != null) {

				List<AssignDutyEmployee> empList = new ArrayList();
				empList = assignDutyEmployeeRepo.getAssignEmpInDuty(dutyId);

				result.setEmpList(empList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	// --Update Assign Duty Time and Emp Ids--
	@PostMapping("/updateAssignDutyTimeAndEmpIds")
	public Info updateAssignDutyTimeAndEmpIds(@RequestParam(value = "assignId") int assignId,
			@RequestParam(value = "notifyTime") String notifyTime, @RequestParam(value = "empIds") String empIds) {

		Info result = new Info();

		try {

			int res = assignDutyRepo.updateTimeAndEmpIds(assignId, notifyTime, empIds);
			System.err.println("RESULT ---------------------------- " + res);
			if (res > 0) {
				result.setError(false);
				result.setMessage("Success");
			} else {
				result.setError(true);
				result.setMessage("Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setError(true);
			result.setMessage("Failed");
		}

		return result;

	}

	// --Update Assign Duty Schedule--
	@PostMapping("/updateAssignDutySchedule")
	public Info updateAssignDutySchedule(@RequestParam(value = "assignId") int assignId,
			@RequestParam(value = "status") int status) {
		Info info = null;

		AssignDuty assignDuty = assignDutyRepo.findByAssignId(assignId);

		if (assignDuty != null) {
			assignDuty.setExInt1(status);
			AssignDuty updated = assignDutyRepo.save(assignDuty);
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

	// -------------TASK DONE HEADER CRUD--------------------------

	// --Save Task Done Header--
	@PostMapping("/saveTaskDoneHeader")
	public TaskDoneHeader saveTaskDoneHeader(@RequestBody TaskDoneHeader taskDoneHeader) {
		return taskDoneHeaderRepo.save(taskDoneHeader);
	}
	
	@PostMapping("/saveTaskDoneHeaderWithNotify")
	public TaskDoneHeader saveTaskDoneHeaderWithNotify(@RequestBody TaskDoneHeader taskDoneHeader) {
		TaskDoneHeader res=taskDoneHeaderRepo.save(taskDoneHeader);
		
		if(res!=null) {
			
			try {
				Employee emp = employeeRepository
						.findByEmpIdAndDelStatus(taskDoneHeader.getEmpId(), 1);

				if (emp.getExVar1() != null) {
					Firebase.sendPushNotifForCommunication(emp.getExVar3(),
							"Duty Reminder",
							"Duty has been transfered to you, please check the duties.",
							"10");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return res;
	}

	// --Get Task Done Header By Id--
	@PostMapping("/getTaskDoneHeaderById")
	public TaskDoneHeader getTaskDoneHeaderById(@RequestParam(value = "taskDoneHeaderId") int taskDoneHeaderId) {
		return taskDoneHeaderRepo.findByTaskDoneHeaderId(taskDoneHeaderId);
	}

	// --Get all Task Done Header List--
	@GetMapping("/allTaskDoneHeader")
	public List<TaskDoneHeader> getAllTaskDoneHeader() {
		return taskDoneHeaderRepo.findAllByDelStatus(1);
	}

	// --Delete Task Done Header--
	@PostMapping("/deleteTaskDoneHeader")
	public Info deleteTaskDoneHeader(@RequestParam(value = "taskDoneHeaderId") int taskDoneHeaderId) {
		Info info = null;

		TaskDoneHeader taskDoneHeader = taskDoneHeaderRepo.findByTaskDoneHeaderId(taskDoneHeaderId);

		if (taskDoneHeader != null) {
			taskDoneHeader.setDelStatus(0);
			TaskDoneHeader updated = taskDoneHeaderRepo.save(taskDoneHeader);
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

	// -------------TASK DONE DETAIL CRUD--------------------------

	// --Save Task Done Detail--
	@PostMapping("/saveTaskDoneDetail")
	public TaskDoneDetail saveTaskDoneDetail(@RequestBody TaskDoneDetail taskDoneDetail) {
		TaskDoneDetail detail = null;
		detail = taskDoneDetailRepo.save(taskDoneDetail);

		if (detail == null) {
			detail = new TaskDoneDetail();
		}

		return detail;
	}

	// --Get Task Done Detail By Id--
	@PostMapping("/getTaskDoneDetailById")
	public TaskDoneDetail getTaskDoneDetailById(@RequestParam(value = "taskDoneDetailId") int taskDoneDetailId) {
		TaskDoneDetail detail = null;
		detail = taskDoneDetailRepo.findByTaskDoneDetailId(taskDoneDetailId);

		if (detail == null) {
			detail = new TaskDoneDetail();
		}

		return detail;
	}

	// --Get all Task Done Detail List--
	@GetMapping("/allTaskDoneDetail")
	public List<TaskDoneDetail> getAllTaskDoneDetail() {
		List<TaskDoneDetail> detail = null;
		detail = taskDoneDetailRepo.findAllByDelStatus(1);

		if (detail == null) {
			detail = new ArrayList();
		}

		return detail;
	}

	// --Delete Task Done Detail--
	@PostMapping("/deleteTaskDoneDetail")
	public Info deleteTaskDoneDetail(@RequestParam(value = "taskDoneDetailId") int taskDoneDetailId) {
		Info info = null;

		TaskDoneDetail taskDoneDetail = taskDoneDetailRepo.findByTaskDoneDetailId(taskDoneDetailId);

		if (taskDoneDetail != null) {
			taskDoneDetail.setDelStatus(0);
			TaskDoneDetail updated = taskDoneDetailRepo.save(taskDoneDetail);
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

	// ------------------TASK DONE HEADER AND DETAIL
	// LIST------------------------------------------

	// get task done header and detail by emp and date
	@PostMapping("/getTaskDoneDataByEmp")
	public List<TaskDoneHeaderDisplay> getTaskDoneDataByEmp(@RequestParam(value = "empId") int empId,
			@RequestParam(value = "date") String date) {
		List<TaskDoneHeaderDisplay> result = null;
		result = taskDoneHeaderDisplayRepo.getTaskDoneHeaderByEmp(empId, date);

		if (result == null) {
			result = new ArrayList();
		} else {

			for (int i = 0; i < result.size(); i++) {

				List<TaskDoneDetailDisplay> detailList = null;
				detailList = taskDoneDetailDisplayRepo.getTaskDoneDetailById(result.get(i).getTaskDoneHeaderId());

				result.get(i).setTaskDoneDetailDisplayList(detailList);

			}

		}

		return result;
	}

	// get task done header by emp and date
	@PostMapping("/getTaskDoneHeaderByEmpAndDate")
	public List<TaskDoneHeaderDisplay> getTaskDoneHeaderByEmpAndDate(@RequestParam(value = "empId") int empId,
			@RequestParam(value = "date") String date) {
		List<TaskDoneHeaderDisplay> result = null;
		result = taskDoneHeaderDisplayRepo.getTaskDoneHeaderByEmp(empId, date);

		if (result == null) {
			result = new ArrayList();
		}

		return result;
	}
	
	
	// get task done header by emp and from to date
		@PostMapping("/getTaskDoneHeaderByEmpAndFromToDate")
		public List<TaskDoneHeaderDisplay> getTaskDoneHeaderByEmpAndFromToDate(@RequestParam(value = "empId") int empId,
				@RequestParam(value = "fromDate") String fromDate,@RequestParam(value = "toDate") String toDate) {
			List<TaskDoneHeaderDisplay> result = null;
			result = taskDoneHeaderDisplayRepo.getTaskDoneHeaderByEmpBetDate(empId, fromDate,toDate);

			if (result == null) {
				result = new ArrayList();
			}

			return result;
		}
		

	// get task done Detail by header Id
	@PostMapping("/getTaskDoneDetailByHeaderId")
	public List<TaskDoneDetailDisplay> getTaskDoneDetailByHeaderId(@RequestParam(value = "headerId") int headerId) {
		List<TaskDoneDetailDisplay> result = null;
		result = taskDoneDetailDisplayRepo.getTaskDoneDetailById(headerId);

		if (result == null) {
			result = new ArrayList();
		}

		return result;
	}

	// ------------UPDATE TASK DONE HEADER AND DETAIL---------------------

	// Update task header and detail Status
	@PostMapping("/updateTaskStatus")
	public Info updateTaskWithPhoto(@RequestParam(value = "detailId") int detailId,
			@RequestParam(value = "headerId") int headerId, @RequestParam(value = "photoReq") int photoReq,
			@RequestParam(value = "remarkReq") int remarkReq, @RequestParam(value = "photo1") String photo1,
			@RequestParam(value = "photo2") String photo2, @RequestParam(value = "photo3") String photo3,
			@RequestParam(value = "photo4") String photo4, @RequestParam(value = "photo5") String photo5,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "status") int status) {

		Info info = new Info();

		int result = 0;

		try {

			if (photoReq == 1 && remarkReq == 1) {

				result = taskDoneDetailRepo.updateTaskWithPhotoAndRemark(detailId, status, photo1, photo2, photo3,
						photo4, photo5, remark);

			} else if (photoReq == 1) {

				result = taskDoneDetailRepo.updateTaskWithPhoto(detailId, status, photo1, photo2, photo3, photo4,
						photo5);

			} else if (remarkReq == 1) {

				result = taskDoneDetailRepo.updateTaskWithRemark(detailId, status, remark);

			} else {

				result = taskDoneDetailRepo.updateTask(detailId, status);

			}

			if (result > 0) {

				TaskDoneWeightCalculation taskCal = taskDoneWeightCalculationRepo.getTaskCalculation(headerId);
				if (taskCal != null) {

					int headerRes = taskDoneHeaderRepo.updateWtAndPercent(headerId, taskCal.getDone_wt(),
							taskCal.getPercentage());
					if (headerRes > 0) {
						info.setError(false);
						info.setMessage("Success");
					} else {
						info.setError(true);
						info.setMessage("Failed");
					}
				}

			} else {
				info.setError(true);
				info.setMessage("Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}

	// -----------------DASHBOARD----------------------------

	// get dept wise count
	@PostMapping("/getDeptWiseCount")
	public List<DeptWiseCount> getDeptWiseCount(@RequestParam(value = "deptId") List<Integer> deptId,
			@RequestParam(value = "date") String date) {
		List<DeptWiseCount> result = null;

		if (deptId.contains(-1)) {
			result = deptWiseCountRepo.getAllDeptWiseCount(date);
		} else {
			result = deptWiseCountRepo.getDeptWiseCount(deptId, date);
		}

		if (result == null) {
			result = new ArrayList();
		}

		return result;
	}

	// get emp wise count
	@PostMapping("/getEmpWiseCount")
	public List<EmpWiseCount> getEmpWiseCount(@RequestParam(value = "deptId") int deptId,
			@RequestParam(value = "empId") List<Integer> empId, @RequestParam(value = "date") String date) {
		List<EmpWiseCount> result = null;

		if (empId.contains(-1) && deptId == -1) {
			result = empWiseCountRepo.getAllCount(date);
		} else if (empId.contains(-1)) {
			result = empWiseCountRepo.getAllEmpWiseCount(deptId, date);
		} else {
			result = empWiseCountRepo.getEmpWiseCount(deptId, empId, date);
		}

		if (result == null) {
			result = new ArrayList();
		}

		return result;
	}

	// get duty wise count
	@PostMapping("/getDutyWiseCount")
	public List<DutyWiseCount> getDutyWiseCount(@RequestParam(value = "empId") int empId,
			@RequestParam(value = "date") String date) {
		List<DutyWiseCount> result = null;

		result = dutyWiseCountRepo.getDutyWiseCount(empId, date);

		if (result == null) {
			result = new ArrayList();
		}

		return result;
	}

	// get duty Report
	@PostMapping("/getDutyReportByEmp")
	public List<DutyReportData> getDutyReportByEmp(@RequestParam(value = "empId") int empId,
			@RequestParam(value = "langId") int langId) {
		List<DutyReportData> result = new ArrayList<>();

		List<DutyReport> reportData = new ArrayList<>();

		if (langId == 1) {
			reportData = dutyReportRepo.getDutyReportByEmpIdEng(empId);
		} else if (langId == 2) {
			reportData = dutyReportRepo.getDutyReportByEmpIdMar(empId);
		} else if (langId == 3) {
			reportData = dutyReportRepo.getDutyReportByEmpIdHin(empId);
		} else {
			reportData = dutyReportRepo.getDutyReportByEmpIdEng(empId);
		}

		// List<DutyReport> reportData = dutyReportRepo.getDutyReportByEmpId(empId);
		System.err.println("REPORT ------------------ " + reportData);

		if (reportData != null) {

			DutyReportData dailyData = new DutyReportData();
			dailyData.setType(1);
			dailyData.setDutyType("Daily Basis");

			DutyReportData dayData = new DutyReportData();
			dayData.setType(2);
			dayData.setDutyType("Day Basis");

			DutyReportData dateData = new DutyReportData();
			dateData.setType(3);
			dateData.setDutyType("Date Basis");

			ArrayList<Integer> dailyDutyId = new ArrayList<>();
			ArrayList<Integer> dayDutyId = new ArrayList<>();
			ArrayList<Integer> dateDutyId = new ArrayList<>();

			ArrayList<DutyReportDutyList> dailyDutyList = new ArrayList<DutyReportDutyList>();
			ArrayList<DutyReportDutyList> dayDutyList = new ArrayList<DutyReportDutyList>();
			ArrayList<DutyReportDutyList> dateDutyList = new ArrayList<DutyReportDutyList>();

			for (int i = 0; i < reportData.size(); i++) {

				if (reportData.get(i).getType() == 1) {

					if (!dailyDutyId.contains(reportData.get(i).getDutyId())) {
						dailyDutyId.add(reportData.get(i).getDutyId());
					}

				} else if (reportData.get(i).getType() == 2) {

					if (!dayDutyId.contains(reportData.get(i).getDutyId())) {
						dayDutyId.add(reportData.get(i).getDutyId());
					}

				} else if (reportData.get(i).getType() == 3) {

					if (!dateDutyId.contains(reportData.get(i).getDutyId())) {
						dateDutyId.add(reportData.get(i).getDutyId());
					}

				}

			}

			for (int j = 0; j < dailyDutyId.size(); j++) {

				for (int i = 0; i < reportData.size(); i++) {

					if (reportData.get(i).getType() == 1 && reportData.get(i).getDutyId() == dailyDutyId.get(j)) {

						DutyReportDutyList duty = new DutyReportDutyList();
						duty.setDutyId(reportData.get(i).getDutyId());
						duty.setDutyName(reportData.get(i).getDutyName());
						duty.setType(reportData.get(i).getType());
						duty.setTypeDesc(reportData.get(i).getTypeDesc());
						duty.setTotalTaskWt(reportData.get(i).getTotalTaskWt());
						duty.setShiftId(reportData.get(i).getShiftId());
						duty.setShiftName(reportData.get(i).getShiftName());
						duty.setShiftFromTime(reportData.get(i).getShiftFromTime());
						duty.setShiftToTime(reportData.get(i).getShiftToTime());

						ArrayList<Integer> taskIds = new ArrayList<>();
						List<DutyReportTask> taskList = new ArrayList<>();

						for (int k = 0; k < reportData.size(); k++) {

							if (reportData.get(k).getType() == 1
									&& reportData.get(k).getDutyId() == dailyDutyId.get(j)) {

								if (!taskIds.contains(reportData.get(k).getTaskId())) {

									taskIds.add(reportData.get(k).getTaskId());

									DutyReportTask task = new DutyReportTask();
									task.setTaskId(reportData.get(k).getTaskId());
									task.setTaskNameEng(reportData.get(k).getTaskNameEng());
									task.setTaskDescEng(reportData.get(k).getTaskDescEng());
									task.setTaskWeight(reportData.get(k).getTaskWeight());
									task.setPhotoReq(reportData.get(k).getPhotoReq());
									task.setRemarkReq(reportData.get(k).getRemarkReq());
									task.setTimeReq(reportData.get(k).getTimeReq());
									task.setTaskTime(reportData.get(k).getTaskTime());

									taskList.add(task);

								}

							}

						}

						duty.setTaskList(taskList);

						dailyDutyList.add(duty);

						break;

					}

				}

			}

			dailyData.setDutyList(dailyDutyList);
			result.add(dailyData);

			// -------------DAY BASIS-----------------------

			for (int j = 0; j < dayDutyId.size(); j++) {

				for (int i = 0; i < reportData.size(); i++) {

					if (reportData.get(i).getType() == 2 && reportData.get(i).getDutyId() == dayDutyId.get(j)) {

						DutyReportDutyList duty = new DutyReportDutyList();
						duty.setDutyId(reportData.get(i).getDutyId());
						duty.setDutyName(reportData.get(i).getDutyName());
						duty.setType(reportData.get(i).getType());
						duty.setTypeDesc(reportData.get(i).getTypeDesc());
						duty.setTotalTaskWt(reportData.get(i).getTotalTaskWt());
						duty.setShiftId(reportData.get(i).getShiftId());
						duty.setShiftName(reportData.get(i).getShiftName());
						duty.setShiftFromTime(reportData.get(i).getShiftFromTime());
						duty.setShiftToTime(reportData.get(i).getShiftToTime());

						ArrayList<Integer> taskIds = new ArrayList<>();
						List<DutyReportTask> taskList = new ArrayList<>();

						for (int k = 0; k < reportData.size(); k++) {

							if (reportData.get(k).getType() == 2 && reportData.get(k).getDutyId() == dayDutyId.get(j)) {

								if (!taskIds.contains(reportData.get(k).getTaskId())) {

									taskIds.add(reportData.get(k).getTaskId());

									DutyReportTask task = new DutyReportTask();
									task.setTaskId(reportData.get(k).getTaskId());
									task.setTaskNameEng(reportData.get(k).getTaskNameEng());
									task.setTaskDescEng(reportData.get(k).getTaskDescEng());
									task.setTaskWeight(reportData.get(k).getTaskWeight());
									task.setPhotoReq(reportData.get(k).getPhotoReq());
									task.setRemarkReq(reportData.get(k).getRemarkReq());
									task.setTimeReq(reportData.get(k).getTimeReq());
									task.setTaskTime(reportData.get(k).getTaskTime());

									taskList.add(task);

								}

							}

						}

						duty.setTaskList(taskList);

						dayDutyList.add(duty);

						break;

					}

				}

			}

			dayData.setDutyList(dayDutyList);
			result.add(dayData);

			// --------------DATE BASIS---------------------------------

			for (int j = 0; j < dateDutyId.size(); j++) {

				for (int i = 0; i < reportData.size(); i++) {

					if (reportData.get(i).getType() == 3 && reportData.get(i).getDutyId() == dateDutyId.get(j)) {

						DutyReportDutyList duty = new DutyReportDutyList();
						duty.setDutyId(reportData.get(i).getDutyId());
						duty.setDutyName(reportData.get(i).getDutyName());
						duty.setType(reportData.get(i).getType());
						duty.setTypeDesc(reportData.get(i).getTypeDesc());
						duty.setTotalTaskWt(reportData.get(i).getTotalTaskWt());
						duty.setShiftId(reportData.get(i).getShiftId());
						duty.setShiftName(reportData.get(i).getShiftName());
						duty.setShiftFromTime(reportData.get(i).getShiftFromTime());
						duty.setShiftToTime(reportData.get(i).getShiftToTime());

						ArrayList<Integer> taskIds = new ArrayList<>();
						List<DutyReportTask> taskList = new ArrayList<>();

						for (int k = 0; k < reportData.size(); k++) {

							if (reportData.get(k).getType() == 3
									&& reportData.get(k).getDutyId() == dateDutyId.get(j)) {

								if (!taskIds.contains(reportData.get(k).getTaskId())) {

									taskIds.add(reportData.get(k).getTaskId());

									DutyReportTask task = new DutyReportTask();
									task.setTaskId(reportData.get(k).getTaskId());
									task.setTaskNameEng(reportData.get(k).getTaskNameEng());
									task.setTaskDescEng(reportData.get(k).getTaskDescEng());
									task.setTaskWeight(reportData.get(k).getTaskWeight());
									task.setPhotoReq(reportData.get(k).getPhotoReq());
									task.setRemarkReq(reportData.get(k).getRemarkReq());
									task.setTimeReq(reportData.get(k).getTimeReq());
									task.setTaskTime(reportData.get(k).getTaskTime());

									taskList.add(task);

								}

							}

						}

						duty.setTaskList(taskList);

						dateDutyList.add(duty);

						break;

					}

				}

			}

			dateData.setDutyList(dateDutyList);
			result.add(dateData);

		}

		return result;
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * 
	 * Firebase.sendPushNotifForCommunication(
	 * "fjYvTMg4WRo:APA91bEgYHR1C9DvcBpAqD0QA_uc22o2WnQp8qa1Zdz3J3MxU3nbebsglTOQO3jMoiDuS7WjLTyucIQKnxZ1qg4tOPtxxJFpX2X2hkgusLnG4bW3it9WWuVL2vpp7u5LoBtNLLYIYwj1",
	 * "TEST", "AAAAAA", "1");
	 * 
	 * 
	 * }
	 */

}
