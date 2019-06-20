package com.ats.gfplsecurity.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.common.Firebase;
import com.ats.gfplsecurity.model.DashboardCount;
import com.ats.gfplsecurity.model.DocumentHandover;
import com.ats.gfplsecurity.model.EmpGatepass;
import com.ats.gfplsecurity.model.EmpGatepassCount;
import com.ats.gfplsecurity.model.EmpGatepassDisplay;
import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.EmployeeDepartment;
import com.ats.gfplsecurity.model.EmployeeDisplay;
import com.ats.gfplsecurity.model.Location;
import com.ats.gfplsecurity.model.MatGatepassCount;
import com.ats.gfplsecurity.model.MatGatepassEmpWiseCount;
import com.ats.gfplsecurity.model.MaterialGatepass;
import com.ats.gfplsecurity.model.MaterialGatepassDisplay;
import com.ats.gfplsecurity.model.Notification;
import com.ats.gfplsecurity.model.Purpose;
import com.ats.gfplsecurity.model.Settings;
import com.ats.gfplsecurity.model.SupGatepassCount;
import com.ats.gfplsecurity.model.VisAndMaintGatepassCount;
import com.ats.gfplsecurity.model.Visitor;
import com.ats.gfplsecurity.model.VisitorGatepassDisplay;
import com.ats.gfplsecurity.repository.DocumentHandoverRepository;
import com.ats.gfplsecurity.repository.EmpGatepassCountRepo;
import com.ats.gfplsecurity.repository.EmpGatepassDisplayRepo;
import com.ats.gfplsecurity.repository.EmpGatepassRepository;
import com.ats.gfplsecurity.repository.EmployeeDepartmentRepository;
import com.ats.gfplsecurity.repository.EmployeeDisplayRepository;
import com.ats.gfplsecurity.repository.EmployeeRepository;
import com.ats.gfplsecurity.repository.MatGatepassCountRepo;
import com.ats.gfplsecurity.repository.MatGatepassEmpWiseCountRepo;
import com.ats.gfplsecurity.repository.MaterialGatepassDisplayRepo;
import com.ats.gfplsecurity.repository.MaterialGatepassRepository;
import com.ats.gfplsecurity.repository.NotificationRepository;
import com.ats.gfplsecurity.repository.PurposeRepository;
import com.ats.gfplsecurity.repository.SettingsRepository;
import com.ats.gfplsecurity.repository.SupGatepassCountRepo;
import com.ats.gfplsecurity.repository.VisAndMaintGatepassCountRepo;
import com.ats.gfplsecurity.repository.VisitorGatepassDisplayRepository;
import com.ats.gfplsecurity.repository.VisitorRepository;
import com.ats.gfplsecurity.common.Info;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	VisitorGatepassDisplayRepository visitorGatepassDisplayRepository;

	@Autowired
	EmployeeDisplayRepository employeeDisplayRepository;

	@Autowired
	VisitorRepository visitorRepository;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	PurposeRepository purposeRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmpGatepassDisplayRepo empGatepassDisplayRepo;

	@Autowired
	MaterialGatepassRepository materialGatepassRepository;

	@Autowired
	MaterialGatepassDisplayRepo materialGatepassDisplayRepo;

	@Autowired
	EmployeeDepartmentRepository employeeDepartmentRepository;

	@Autowired
	DocumentHandoverRepository documentHandoverRepository;

	@Autowired
	SettingsRepository settingsRepository;

	@Autowired
	EmpGatepassRepository empGatepassRepository;

	@Autowired
	VisAndMaintGatepassCountRepo visAndMaintGatepassCountRepo;

	@Autowired
	EmpGatepassCountRepo empGatepassCountRepo;

	@Autowired
	SupGatepassCountRepo supGatepassCountRepo;

	@Autowired
	MatGatepassCountRepo matGatepassCountRepo;

	@Autowired
	MatGatepassEmpWiseCountRepo matGatepassEmpWiseCountRepo;

	@PostMapping("/getVisitorGatepassListInDate")
	public @ResponseBody List<VisitorGatepassDisplay> getVisitorGatepassListInDate(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "gatepassType") List<Integer> gatepassType,
			@RequestParam(value = "empIds") String empIds, @RequestParam(value = "status") List<Integer> status) {

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

			System.out.println("EMPIDS : --------------------------------- " + empIds);

			if (empIds.equalsIgnoreCase("-1")) {

				System.out.println(" EMPIDS -------------------- IF");

				resultList = visitorGatepassDisplayRepository.getVisitorGatepassListInDate(fromDate, toDate,
						gatepassType, status);

			} else {

				System.out.println(" EMPIDS -------------------- ELSE");

				resultList = visitorGatepassDisplayRepository.getVisitorGatepassListInDate(fromDate, toDate,
						gatepassType, empIds, status);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	@PostMapping("/getVisitorGatepassList")
	public @ResponseBody List<VisitorGatepassDisplay> getVisitorGatepassList(
			@RequestParam(value = "gatepassType") List<Integer> gatepassType,
			@RequestParam(value = "empIds") String empIds, @RequestParam(value = "status") List<Integer> status) {

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

			System.out.println("EMPIDS : --------------------------------- " + empIds);

			if (empIds.equalsIgnoreCase("-1")) {

				System.out.println(" EMPIDS -------------------- IF");

				resultList = visitorGatepassDisplayRepository.getVisitorGatepassList(gatepassType, status);

			} else {

				System.out.println(" EMPIDS -------------------- ELSE");

				resultList = visitorGatepassDisplayRepository.getVisitorGatepassList(gatepassType, empIds, status);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// -- Employee list by dept, category, type
	@PostMapping("/getEmployeeListWithFilter")
	public @ResponseBody List<EmployeeDisplay> getEmployeeListWithFilter(
			@RequestParam(value = "deptIds") List<Integer> deptIds,
			@RequestParam(value = "catIds") List<Integer> catIds,
			@RequestParam(value = "typeIds") List<Integer> typeIds) {

		List<EmployeeDisplay> resultList = null;

		try {

			if (deptIds.contains(-1) && catIds.contains(-1) && typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getAllEmpList();

			} else if (deptIds.contains(-1) && catIds.contains(-1) && !typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getEmpList_ByType(typeIds);

			} else if (deptIds.contains(-1) && !catIds.contains(-1) && typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getEmpList_ByCat(catIds);

			} else if (!deptIds.contains(-1) && catIds.contains(-1) && typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getEmpList_ByDept(deptIds);

			} else if (!deptIds.contains(-1) && !catIds.contains(-1) && typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getEmpList_ByDept_ByCat(deptIds, catIds);

			} else if (!deptIds.contains(-1) && catIds.contains(-1) && !typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getEmpList_ByDept_ByType(deptIds, typeIds);

			} else if (deptIds.contains(-1) && !catIds.contains(-1) && !typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getEmpList_ByType_ByCat(catIds, typeIds);

			} else if (!deptIds.contains(-1) && !catIds.contains(-1) && !typeIds.contains(-1)) {

				resultList = employeeDisplayRepository.getEmpList_ByDept_ByType_ByCat(deptIds, catIds, typeIds);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// --Save Gatepass Visitor With Notification--
	@PostMapping("/saveGatepassVisitor")
	public Visitor saveVisitor(@RequestBody Visitor visitor) {

		Visitor result = null;

		try {

			Settings settings = settingsRepository.findBySettingId(1);

			visitor.setExVar1(settings.getSettingKey() + "" + settings.getSettingValue());

			result = visitorRepository.save(visitor);

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Calendar cal = Calendar.getInstance();

			visitor.setInTime(sdf.format(cal.getTimeInMillis()));

			if (result != null) {

				int val = Integer.parseInt(settings.getSettingValue());
				int value = val + 1;
				int updateRes = settingsRepository.updateValue(1, "" + value);

				String empIds = result.getEmpIds();

				List<Integer> empIdList = Stream.of(empIds.split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				if (empIdList.size() > 0) {
					for (int i = 0; i < empIdList.size(); i++) {

						Employee emp = employeeRepository.findByEmpIdAndDelStatus(empIdList.get(i), 1);
						Purpose purpose = purposeRepository.findByPurposeId(result.getPurposeId());

						Notification notification = new Notification(0, result.getGatepassVisitorId(), empIdList.get(i),
								emp.getEmpFname() + " " + emp.getEmpMname() + " " + emp.getEmpSname(),
								result.getGatePasstype(), purpose.getPurposeType(), result.getPurposeId(), "", 0, 0, 1,
								1, 0, 0, 0, "na", "na", "na");

						Notification savedModel = notificationRepository.save(notification);

						try {

							if (savedModel != null) {

								String token = emp.getExVar1();

								String type = "";
								if (result.getGatePasstype() == 1) {
									type = "Visitor";
								} else {
									type = "Maintenance";
								}

								Firebase.sendPushNotifForCommunication(token, "" + type + " Gatepass",
										"" + result.getPersonName() + " wants to visit. Please take action on this.",
										"" + result.getGatePasstype());

							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// --Update gatepass status
	@PostMapping("/updateGatepassStatus")
	public @ResponseBody Info updateGatepassStatus(@RequestParam("gatepassVisitorId") int gatepassVisitorId,
			@RequestParam("empId") int empId, @RequestParam("status") int status, @RequestParam("gateId") int gateId) {

		Info info = new Info();

		try {

			if (status == 2) {

				int visitorUpdateStatus = visitorRepository.updateGatepassStatus(gatepassVisitorId, status, gateId);

				if (visitorUpdateStatus > 0) {

					int notifyUpdateStatus = notificationRepository.updateNotificationStatus(gatepassVisitorId, empId,
							status);

					if (notifyUpdateStatus > 0) {
						info.setError(false);
						info.setMessage("Updated Successfully");

					} else {
						info.setError(true);
						info.setMessage("failed");
					}

					try {

						Employee emp = employeeRepository.findByEmpIdAndDelStatus(empId, 1);
						Visitor visitor = visitorRepository.findByGatepassVisitorId(gatepassVisitorId);

						Employee securityEmp = employeeRepository.findByEmpIdAndDelStatus(visitor.getSecurityIdIn(), 1);

						String type = "";
						if (visitor.getGatePasstype() == 1) {
							type = "Visitor";
						} else {
							type = "Maintenance";
						}

						Firebase.sendPushNotifForCommunication(securityEmp.getExVar1(),
								"" + type + " Gatepass for " + visitor.getPersonName() + " from "
										+ visitor.getPersonCompany() + " has Rejected",
								"" + visitor.getPersonName() + " from " + visitor.getPersonCompany()
										+ " company, gatepass has Rejected by " + emp.getEmpFname() + " "
										+ emp.getEmpMname() + " " + emp.getEmpSname(),
								"" + visitor.getGatePasstype());

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					info.setError(true);
					info.setMessage("failed");
				}

			} else {

				int notifyUpdateStatus = notificationRepository.updateNotificationStatus(gatepassVisitorId, empId,
						status);

				if (notifyUpdateStatus > 0) {

					List<Notification> notifyList = notificationRepository
							.findByGatepassVisitorIdAndDelStatus(gatepassVisitorId, 1);

					if (notifyList != null) {

						int pendingCount = 0;

						for (int i = 0; i < notifyList.size(); i++) {

							if (notifyList.get(i).getStatus() == 0) {
								pendingCount++;
							}
						}

						if (pendingCount == 0) {

							int visitorUpdateStatus = visitorRepository.updateGatepassStatus(gatepassVisitorId, status,
									gateId);

							if (visitorUpdateStatus > 0) {

								info.setError(false);
								info.setMessage("Updated Successfully");

							} else {

								info.setError(true);
								info.setMessage("failed");

							}

						} else {

							info.setError(false);
							info.setMessage("Updated Successfully");

						}
					}

					try {

						Employee emp = employeeRepository.findByEmpIdAndDelStatus(empId, 1);
						Visitor visitor = visitorRepository.findByGatepassVisitorId(gatepassVisitorId);

						Employee securityEmp = employeeRepository.findByEmpIdAndDelStatus(visitor.getSecurityIdIn(), 1);

						String type = "";
						if (visitor.getGatePasstype() == 1) {
							type = "Visitor";
						} else {
							type = "Maintenance";
						}

						Firebase.sendPushNotifForCommunication(securityEmp.getExVar1(),
								"" + type + " Gatepass for " + visitor.getPersonName() + " from "
										+ visitor.getPersonCompany() + " has Approved",
								"" + visitor.getPersonName() + " from " + visitor.getPersonCompany()
										+ " company, gatepass has Approved by " + emp.getEmpFname() + " "
										+ emp.getEmpMname() + " " + emp.getEmpSname(),
								"" + visitor.getGatePasstype());

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("failed");
		}

		return info;

	}

	// --Update Visitor Status By Id--
	@PostMapping("/updateVisitorStatus")
	public Info updateVisitorStatus(@RequestParam(value = "gatepassVisitorId") int gatepassVisitorId,
			@RequestParam(value = "status") int status) {
		Info info = new Info();

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			Calendar cal1 = Calendar.getInstance();

			String outTime = sdf.format(cal1.getTimeInMillis());

			Visitor visitor = visitorRepository.findByGatepassVisitorId(gatepassVisitorId);

			String inTime = "00:00";
			if (visitor != null) {
				inTime = visitor.getInTime();
			}

			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

			String timeDiff = "0";
			String date = "";

			Date d1, d2;

			try {

				System.out.println("OUT TIME --------------------------------------- " + outTime);
				System.out.println("IN TIME --------------------------------------- " + inTime);

				d1 = sdfTime.parse(outTime);
				d2 = sdfTime.parse(inTime);

				long diff = d1.getTime() - d2.getTime();

				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);

				timeDiff = diffHours + "." + diffMinutes;

				System.out.println("TIME DIFF --------------------------------------- " + timeDiff);

			} catch (Exception e) {
				e.printStackTrace();
			}

			int updateStatus = visitorRepository.updateGatepassStatus(gatepassVisitorId, status, outTime, timeDiff);

			if (updateStatus > 0) {

				info.setError(false);
				info.setMessage("Updated Successfully");

			} else {

				info.setError(true);
				info.setMessage("failed");

			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("failed");
		}

		return info;
	}

	// -- Emp Gatepass List by Date, Dept, Employee, Status
	@PostMapping("/getEmpGatepassListWithDateFilter")
	public @ResponseBody List<EmpGatepassDisplay> getEmpGatepassListWithDateFilter(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "deptIds") List<Integer> deptIds, @RequestParam(value = "empIds") String empIds,
			@RequestParam(value = "status") List<Integer> status) {

		List<EmpGatepassDisplay> resultList = null;

		try {

			if (status.contains(-1)) {
				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);
			}

			if (deptIds.contains(-1) && empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByDateByStatus(fromDate, toDate, status);

			} else if (deptIds.contains(-1) && !empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByDateByEmpByStatus(fromDate, toDate, empIds, status);

			} else if (!deptIds.contains(-1) && empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByDateByDeptByStatus(fromDate, toDate, deptIds, status);

			} else if (!deptIds.contains(-1) && !empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByDateByDeptByEmpByStatus(fromDate, toDate, deptIds,
						empIds, status);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// -- Emp Gatepass List By type, Dept, Employee, Status
	@PostMapping("/getEmpGatepassListWithFilter")
	public @ResponseBody List<EmpGatepassDisplay> getEmpGatepassListWithFilter(
			@RequestParam(value = "typeIds") List<Integer> typeIds,
			@RequestParam(value = "deptIds") List<Integer> deptIds, @RequestParam(value = "empIds") String empIds,
			@RequestParam(value = "status") List<Integer> status) {

		List<EmpGatepassDisplay> resultList = null;

		try {

			if (status.contains(-1)) {
				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);
			}

			if (typeIds.contains(-1) && deptIds.contains(-1) && empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByStatus(status);

			} else if (typeIds.contains(-1) && deptIds.contains(-1) && !empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByEmpByStatus(empIds, status);

			} else if (typeIds.contains(-1) && !deptIds.contains(-1) && empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByDeptByStatus(deptIds, status);

			} else if (!typeIds.contains(-1) && deptIds.contains(-1) && empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByTypeByStatus(typeIds, status);

			} else if (!typeIds.contains(-1) && !deptIds.contains(-1) && empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByTypeByDeptByStatus(typeIds, deptIds, status);

			} else if (!typeIds.contains(-1) && deptIds.contains(-1) && !empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByTypeByEmpByStatus(typeIds, empIds, status);

			} else if (typeIds.contains(-1) && !deptIds.contains(-1) && !empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByDeptByEmpByStatus(deptIds, empIds, status);

			} else if (!typeIds.contains(-1) && !deptIds.contains(-1) && !empIds.equalsIgnoreCase("-1")) {

				resultList = empGatepassDisplayRepo.getEmpGPListByTypeByDeptByEmpByStatus(typeIds, deptIds, empIds,
						status);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// -- Material Gatepass List By Date, Supplier, Status
	@PostMapping("/getMaterialGatepassListWithDateFilter")
	public @ResponseBody List<MaterialGatepassDisplay> getMaterialGatepassListWithDateFilter(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "supIds") List<Integer> supIds,
			@RequestParam(value = "status") List<Integer> status) {

		List<MaterialGatepassDisplay> resultList = null;

		try {

			if (status.contains(-1)) {
				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);
			}

			if (supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatGPListByDateByStatus(fromDate, toDate, status);

			} else if (!supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatGPListByDateBySupByStatus(fromDate, toDate, supIds,
						status);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// -- Material Gatepass List By Supplier, Status
	@PostMapping("/getMaterialGatepassListWithFilter")
	public @ResponseBody List<MaterialGatepassDisplay> getMaterialGatepassListWithFilter(
			@RequestParam(value = "supIds") List<Integer> supIds,
			@RequestParam(value = "status") List<Integer> status) {

		List<MaterialGatepassDisplay> resultList = null;

		try {

			if (status.contains(-1)) {
				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);
			}

			if (supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatGPListByStatus(status);

			} else if (!supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatGPListBySupByStatus(supIds, status);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// --Save Material Gatepass Header and Detail--
	@PostMapping("/saveMaterialGatepass")
	public MaterialGatepass saveMaterialGatepass(@RequestBody MaterialGatepass materialGatepass) {

		MaterialGatepass result = null;

		try {

			Settings settings = settingsRepository.findBySettingId(1);

			materialGatepass.setExVar1(settings.getSettingKey() + "" + settings.getSettingValue());

			result = materialGatepassRepository.save(materialGatepass);

			if (result != null) {

				int val = Integer.parseInt(settings.getSettingValue());
				int value = val + 1;
				int updateRes = settingsRepository.updateValue(1, "" + value);

				String toDeptName = employeeRepository.getDeptNameByEmpId(result.getSecurityId());
				Employee emp = employeeRepository.findByEmpIdAndDelStatus(result.getSecurityId(), 1);

				EmployeeDepartment dept = employeeDepartmentRepository.findByEmpDeptIdAndDelStatus(1, 1);

				String supplierDeptName = "";

				if (dept != null) {
					supplierDeptName = dept.getEmpDeptName();
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String currDate = sdf.format(Calendar.getInstance().getTimeInMillis());

				DocumentHandover docHandover = new DocumentHandover(0, result.getGatepassInwardId(), currDate,
						result.getPartyId(), result.getSecurityId(), result.getPartyName(), result.getSecurityName(), 1,
						1, 1, supplierDeptName, emp.getEmpDeptId(), toDeptName, 0, 0, 0, "na", "na", "na");

				DocumentHandover doc = documentHandoverRepository.save(docHandover);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// -- Material Gatepass Tracking List By Dept, Emp , Status
	@PostMapping("/getMaterialTrackGatepassListWithFilter")
	public @ResponseBody List<MaterialGatepassDisplay> getMaterialTrackGatepassListWithFilter(
			@RequestParam(value = "deptIds") List<Integer> deptIds,
			@RequestParam(value = "empIds") List<Integer> empIds,
			@RequestParam(value = "status") List<Integer> status) {

		List<MaterialGatepassDisplay> resultList = null;

		try {

			if (status.contains(-1)) {
				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);
			}

			if (deptIds.contains(-1) && empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByStatus(status);

			} else if (!deptIds.contains(-1) && empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDeptByStatus(deptIds, status);

			} else if (deptIds.contains(-1) && !empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByEmpByStatus(empIds, status);

			} else if (!deptIds.contains(-1) && !empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDeptByEmpByStatus(deptIds, empIds, status);

			}

			if (resultList != null) {

				for (int i = 0; i < resultList.size(); i++) {

					MaterialGatepassDisplay disp = resultList.get(i);

					List<DocumentHandover> docList = documentHandoverRepository.findByDelStatusAndGatepassInwardId(1,
							disp.getGatepassInwardId());

					disp.setDocHandoverDetail(docList);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// -- Material Gatepass Tracking List By Date, Dept, Emp , Status
	@PostMapping("/getMaterialTrackGatepassListWithDateFilter")
	public @ResponseBody List<MaterialGatepassDisplay> getMaterialTrackGatepassListWithDateFilter(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "deptIds") List<Integer> deptIds,
			@RequestParam(value = "empIds") List<Integer> empIds,
			@RequestParam(value = "status") List<Integer> status) {

		List<MaterialGatepassDisplay> resultList = null;

		try {

			if (status.contains(-1)) {
				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);
			}

			if (deptIds.contains(-1) && empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateByStatus(fromDate, toDate, status);

			} else if (!deptIds.contains(-1) && empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateByDeptByStatus(fromDate, toDate,
						deptIds, status);

			} else if (deptIds.contains(-1) && !empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateByEmpByStatus(fromDate, toDate, empIds,
						status);

			} else if (!deptIds.contains(-1) && !empIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateByDeptByEmpByStatus(fromDate, toDate,
						deptIds, empIds, status);

			}

			if (resultList != null) {

				for (int i = 0; i < resultList.size(); i++) {

					MaterialGatepassDisplay disp = resultList.get(i);

					List<DocumentHandover> docList = documentHandoverRepository.findByDelStatusAndGatepassInwardId(1,
							disp.getGatepassInwardId());

					disp.setDocHandoverDetail(docList);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// --------------------------------------7-6-19----------------------------------------------------------

	// -- Material Gatepass Tracking List By Date, Dept, SUP , Status
	@PostMapping("/getMaterialTrackGPListWithSupFilter")
	public @ResponseBody List<MaterialGatepassDisplay> getMaterialTrackGPListWithSupFilter(
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "deptIds") List<Integer> deptIds,
			@RequestParam(value = "supIds") List<Integer> supIds,
			@RequestParam(value = "status") List<Integer> status) {

		List<MaterialGatepassDisplay> resultList = null;

		try {

			if (status.contains(-1)) {
				status.clear();
				status.add(0);
				status.add(1);
				status.add(2);
				status.add(3);
			}

			if (deptIds.contains(-1) && supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateByStatus(fromDate, toDate, status);

			} else if (!deptIds.contains(-1) && supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateByDeptByStatus(fromDate, toDate,
						deptIds, status);

			} else if (deptIds.contains(-1) && !supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateBySupByStatus(fromDate, toDate, supIds,
						status);

			} else if (!deptIds.contains(-1) && !supIds.contains(-1)) {

				resultList = materialGatepassDisplayRepo.getMatTrackingListByDateByDeptBySupByStatus(fromDate, toDate,
						deptIds, supIds, status);

			}

			if (resultList != null) {

				for (int i = 0; i < resultList.size(); i++) {

					MaterialGatepassDisplay disp = resultList.get(i);

					List<DocumentHandover> docList = documentHandoverRepository.findByDelStatusAndGatepassInwardId(1,
							disp.getGatepassInwardId());

					disp.setDocHandoverDetail(docList);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}

	// ------------------------------------------------------------------------------------------------------

	// --- Update material gatepass status
	@PostMapping("/updateMaterialGatepass")
	public @ResponseBody Info updateMaterialGatepass(@RequestParam(value = "headerList") List<Integer> ids,
			@RequestParam(value = "status") int status) {

		Info info = new Info();

		try {

			List<MaterialGatepass> matGpList = materialGatepassRepository.getMatGPList(ids);

			if (matGpList.size() > 0) {

				for (int i = 0; i < matGpList.size(); i++) {

					MaterialGatepass disp = matGpList.get(i);
					List<DocumentHandover> docList = documentHandoverRepository.findByDelStatusAndGatepassInwardId(1,
							disp.getGatepassInwardId());

					if (docList != null) {
						if (docList.size() > 0) {

							DocumentHandover doc = docList.get(docList.size() - 1);
							doc.setStatus(status);

							DocumentHandover saveDoc = documentHandoverRepository.save(doc);

							if (saveDoc != null) {
								disp.setToStatus(status);
								MaterialGatepass matGp = materialGatepassRepository.save(disp);

								if (matGp != null) {
									info.setError(false);
									info.setMessage("Status Updated Successfully");
								} else {
									info.setError(false);
									info.setMessage("Failed to Update Status ");
								}

							} else {
								info.setError(false);
								info.setMessage("Failed to Update Status ");
							}

						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			info.setError(false);
			info.setMessage("Failed to Update Status ");
		}

		return info;

	}

	// --- Material gatepass handover
	@PostMapping("/materialGatepassHandover")
	public @ResponseBody Info materialGatepassHandover(@RequestParam(value = "headerIdList") List<Integer> matGpIdList,
			@RequestParam(value = "fromEmpId") int fromEmpId, @RequestParam(value = "fromDeptId") int fromDeptId,
			@RequestParam(value = "toEmpId") int toEmpId, @RequestParam(value = "toDeptId") int toDeptId) {

		Info info = new Info();

		try {

			if (matGpIdList.size() > 0) {

				for (int i = 0; i < matGpIdList.size(); i++) {

					MaterialGatepass disp = materialGatepassRepository.findByGatepassInwardId(matGpIdList.get(i));

					if (disp != null) {

						Employee fromEmp = employeeRepository.findByEmpIdAndDelStatus(fromEmpId, 1);
						Employee toEmp = employeeRepository.findByEmpIdAndDelStatus(toEmpId, 1);
						EmployeeDepartment fromDept = employeeDepartmentRepository
								.findByEmpDeptIdAndDelStatus(fromDeptId, 1);
						EmployeeDepartment toDept = employeeDepartmentRepository.findByEmpDeptIdAndDelStatus(toDeptId,
								1);

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String currDate = sdf.format(Calendar.getInstance().getTimeInMillis());

						String fromEmpName = fromEmp.getEmpFname() + " " + fromEmp.getEmpMname()
								+ fromEmp.getEmpSname();
						String toEmpName = toEmp.getEmpFname() + " " + fromEmp.getEmpMname() + fromEmp.getEmpSname();

						DocumentHandover docHandover = new DocumentHandover(0, disp.getGatepassInwardId(), currDate,
								fromEmpId, toEmpId, fromEmpName, toEmpName, 0, 1, fromDeptId, fromDept.getEmpDeptName(),
								toDeptId, toDept.getEmpDeptName(), 0, 0, 0, "na", "na", "na");

						DocumentHandover doc = documentHandoverRepository.save(docHandover);

						if (doc != null) {

							disp.setToDeptId(toDeptId);
							disp.setToEmpId(toEmpId);
							disp.setToStatus(0);
							disp.setStatus(0);
							disp.setToDeptName(toDept.getEmpDeptName());
							disp.setToEmpName(toEmpName);

							MaterialGatepass matGp = materialGatepassRepository.save(disp);

							if (matGp != null) {
								info.setError(false);
								info.setMessage("Success");
							} else {
								info.setError(false);
								info.setMessage("Failed");
							}

						} else {
							info.setError(false);
							info.setMessage("Failed");
						}

					}

				}

				info.setError(false);
				info.setMessage("Success");

			}

		} catch (Exception e) {
			e.printStackTrace();
			info.setError(false);
			info.setMessage("Failed");
		}

		return info;

	}

	// --- Update Employee gatepass
	@PostMapping("/updateEmpGatepass")
	public @ResponseBody Info updateEmpGatepass(@RequestParam(value = "gatepassEmpId") int gatepassEmpId,
			@RequestParam(value = "securityId") int securityId, @RequestParam(value = "status") int status,
			@RequestParam(value = "type") int type) {

		Info info = new Info();

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

		String outTime = "";
		String inTime = "";
		String timeDiff = "";
		String date = "";

		Date d1, d2;

		try {

			Calendar cal = Calendar.getInstance();
			outTime = sdfTime.format(cal.getTimeInMillis());
			inTime = sdfTime.format(cal.getTimeInMillis());
			date = sdfDate.format(cal.getTimeInMillis());

			d1 = sdfTime.parse(outTime);
			d2 = sdfTime.parse(inTime);

			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			timeDiff = diffHours + ":" + diffMinutes;

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (type == 2) {

			int updateResult = empGatepassRepository.updateEmpGatepassStatusToClose(gatepassEmpId, securityId, 2, date,
					outTime, inTime, timeDiff);

			if (updateResult > 0) {

				info.setError(false);
				info.setMessage("Updated Successfully");

				try {

					EmpGatepass gp = empGatepassRepository.findByGatepassEmpId(gatepassEmpId);

					Employee emp = employeeRepository.findByEmpIdAndDelStatus(gp.getEmpId(), 1);

					
					Employee supEmp = employeeRepository.findByEmpIdAndDelStatus(gp.getUserId(), 1);
					
					SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm a");

					Firebase.sendPushNotifForCommunication(
							supEmp.getExVar1(), emp.getEmpFname()+" "+emp.getEmpMname()+" "+emp.getEmpSname()+" is Out of the factory",
							emp.getEmpFname()+" "+emp.getEmpMname()+" "+emp.getEmpSname()+" is Out of the factory on "+sdf.format(Calendar.getInstance().getTimeInMillis()),
							"3");

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

				info.setError(true);
				info.setMessage("failed");

			}

		} else {

			if (status == 1) {

				int result = empGatepassRepository.updateEmpGatepassStatusToOut(gatepassEmpId, securityId, status, date,
						outTime);

				if (result > 0) {

					info.setError(false);
					info.setMessage("Updated Successfully");
					
					try {

						EmpGatepass gp = empGatepassRepository.findByGatepassEmpId(gatepassEmpId);

						Employee emp = employeeRepository.findByEmpIdAndDelStatus(gp.getEmpId(), 1);

						
						Employee supEmp = employeeRepository.findByEmpIdAndDelStatus(gp.getUserId(), 1);
						
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm a");

						Firebase.sendPushNotifForCommunication(
								supEmp.getExVar1(), emp.getEmpFname()+" "+emp.getEmpMname()+" "+emp.getEmpSname()+" is Out of the factory",
								emp.getEmpFname()+" "+emp.getEmpMname()+" "+emp.getEmpSname()+" is Out of the factory on "+sdf.format(Calendar.getInstance().getTimeInMillis()),
								"3");

					} catch (Exception e) {
						e.printStackTrace();
					}


				} else {

					info.setError(true);
					info.setMessage("failed");

				}

			} else if (status == 2) {

				EmpGatepass empGatepass = empGatepassRepository.findByGatepassEmpId(gatepassEmpId);

				try {

					Calendar cal = Calendar.getInstance();
					// outTime=sdfTime.format(cal.getTimeInMillis());
					inTime = sdfTime.format(cal.getTimeInMillis());
					date = sdfDate.format(cal.getTimeInMillis());

					d1 = sdfTime.parse(empGatepass.getNewOutTime());
					d2 = sdfTime.parse(inTime);

					long diff = d2.getTime() - d1.getTime();

					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);

					timeDiff = diffHours + ":" + diffMinutes;

				} catch (Exception e) {
					e.printStackTrace();
				}

				int result = empGatepassRepository.updateEmpGatepassStatusToIn(gatepassEmpId, securityId, status, date,
						inTime, timeDiff);

				if (result > 0) {

					info.setError(false);
					info.setMessage("Updated Successfully");
					
					try {

						EmpGatepass gp = empGatepassRepository.findByGatepassEmpId(gatepassEmpId);

						Employee emp = employeeRepository.findByEmpIdAndDelStatus(gp.getEmpId(), 1);

						
						Employee supEmp = employeeRepository.findByEmpIdAndDelStatus(gp.getUserId(), 1);
						
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm a");

						Firebase.sendPushNotifForCommunication(
								supEmp.getExVar1(), emp.getEmpFname()+" "+emp.getEmpMname()+" "+emp.getEmpSname()+" is back to the factory",
								emp.getEmpFname()+" "+emp.getEmpMname()+" "+emp.getEmpSname()+" is back to the factory on "+sdf.format(Calendar.getInstance().getTimeInMillis()),
								"3");

					} catch (Exception e) {
						e.printStackTrace();
					}


				} else {

					info.setError(true);
					info.setMessage("failed");

				}

			}

		}

		return info;

	}

	// --Send Notification--
	@PostMapping("/sendNotification")
	public Info sendNotification(@RequestParam(value = "gatepassVisitorId") int gatepassVisitorId,
			@RequestParam(value = "empId") int empId) {
		Info info = new Info();

		try {

			Visitor visitor = visitorRepository.findByGatepassVisitorId(gatepassVisitorId);
			Employee emp = employeeRepository.findByEmpIdAndDelStatus(empId, 1);

			if (visitor != null) {

				String token = emp.getExVar1();

				String type = "";
				if (visitor.getGatePasstype() == 1) {
					type = "Visitor";
				} else {
					type = "Maintenance";
				}

				Firebase.sendPushNotifForCommunication(token, "" + type + " Gatepass",
						"" + visitor.getPersonName() + " wants to visit. Please take action on this.",
						"" + visitor.getGatePasstype());

				info.setError(false);
				info.setMessage("Notification Sent Successfully");

			} else {
				info.setError(true);
				info.setMessage("Failed to Send Notification");
			}

		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("Failed to Send Notification");
		}

		return info;
	}

	// ----------------------------DASHBOARD
	// COUNT-----------------------------------------------------

	@PostMapping("/dashboardCount")
	public @ResponseBody DashboardCount getDashboardCount(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate, @RequestParam(value = "empId") int empId) {

		DashboardCount dashResult = new DashboardCount();

		try {

			Employee emp = employeeRepository.findByEmpIdAndDelStatus(empId, 1);

			Settings settings = settingsRepository.findBySettingId(2);

			VisAndMaintGatepassCount visCount = visAndMaintGatepassCountRepo.getVisAndMaintCount(fromDate, toDate,
					empId);

			int inCompCount = 0, visTotal = 0, matTotal = 0, empVisTotal = 0;

			inCompCount = visCount.getVisitor_approved() + visCount.getVisitor_pending();
			visTotal = visCount.getVisitor_approved() + visCount.getVisitor_pending() + visCount.getVisitor_rejected()
					+ visCount.getVisitor_completed();
			matTotal = visCount.getMaint_approved() + visCount.getMaint_pending() + visCount.getMaint_rejected();
			empVisTotal = visCount.getEmp_visitor_approved() + visCount.getEmp_visitor_pending()
					+ visCount.getEmp_visitor_rejected() + visCount.getEmp_visitor_completed();

			visCount.setVisitor_in_comp(inCompCount);
			visCount.setVisitor_total(visTotal);
			visCount.setEmp_visitor_total(empVisTotal);
			visCount.setMaint_total(matTotal);

			dashResult.setVisAndMaintGatepassCount(visCount);

			// ----------------------EMP GATEPASS--------------------------------

			EmpGatepassCount empCount = empGatepassCountRepo.getEmpGatepassCount(fromDate, toDate);
			dashResult.setEmpGatepassCount(empCount);

			if (settings.getSettingValue().equalsIgnoreCase(String.valueOf(emp.getEmpCatId()))) {

				SupGatepassCount supCount = supGatepassCountRepo.getSupGatepassCount(fromDate, toDate, empId);
				dashResult.setSupGatepassCount(supCount);
			}

			// ------------------MATERIAL
			// GATEPASS--------------------------------------------

			MatGatepassCount matCount = matGatepassCountRepo.getMatGatepassCount(fromDate, toDate, emp.getEmpDeptId());
			dashResult.setMatGatepassCount(matCount);

			MatGatepassEmpWiseCount matEmpCount = matGatepassEmpWiseCountRepo.getMatGatepassCount(fromDate, toDate, 1);
			dashResult.setMatGatepassEmpWiseCount(matEmpCount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dashResult;
	}
	
	
	
	
	//---------------UNIQUE EMP CODE----------------
	
	@PostMapping("/checkUniqueEmpCode")
	public @ResponseBody Info checkUniqueEmpCode(
			@RequestParam(value = "code") String code) {

		String  result = null;
		Info info = new Info();

		try {

			Employee emp=employeeRepository.findByEmpCode(code);
			System.err.println("UNIQUE CODE -----------------------------------------------------  "+emp);
			
			if(emp!=null) {
				result="Yes";
				info.setError(true);
				info.setMessage("yes");
			}else {
				result="No";
				info.setError(false);
				info.setMessage("no");
			}
			
			
			System.err.println("UNIQUE CODE --------------------RESULT---------------------------------  "+result);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;

	}
	

}
