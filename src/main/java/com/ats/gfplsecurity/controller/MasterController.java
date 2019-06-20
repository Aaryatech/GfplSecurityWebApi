package com.ats.gfplsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.gfplsecurity.common.Info;
import com.ats.gfplsecurity.common.ResourceNotFoundException;
import com.ats.gfplsecurity.model.Company;
import com.ats.gfplsecurity.model.DocumentHandover;
import com.ats.gfplsecurity.model.EmpDeptDisplay;
import com.ats.gfplsecurity.model.EmpGatepass;
import com.ats.gfplsecurity.model.EmpType;
import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.EmployeeCategory;
import com.ats.gfplsecurity.model.EmployeeCategoryDisplay;
import com.ats.gfplsecurity.model.EmployeeDepartment;
import com.ats.gfplsecurity.model.EmployeeDisplay;
import com.ats.gfplsecurity.model.Gate;
import com.ats.gfplsecurity.model.Location;
import com.ats.gfplsecurity.model.LocationDisplay;
import com.ats.gfplsecurity.model.MaterialGatepass;
import com.ats.gfplsecurity.model.Notification;
import com.ats.gfplsecurity.model.ProductionAccess;
import com.ats.gfplsecurity.model.Purpose;
import com.ats.gfplsecurity.model.PurposeDisplay;
import com.ats.gfplsecurity.model.SalaryBracket;
import com.ats.gfplsecurity.model.Settings;
import com.ats.gfplsecurity.model.VisitCard;
import com.ats.gfplsecurity.model.Visitor;
import com.ats.gfplsecurity.repository.CompanyRepository;
import com.ats.gfplsecurity.repository.DocumentHandoverRepository;
import com.ats.gfplsecurity.repository.EmpDeptDisplayRepo;
import com.ats.gfplsecurity.repository.EmpGatepassRepository;
import com.ats.gfplsecurity.repository.EmpTypeRepository;
import com.ats.gfplsecurity.repository.EmployeeCategoryDisplayRepo;
import com.ats.gfplsecurity.repository.EmployeeCategoryRepository;
import com.ats.gfplsecurity.repository.EmployeeDepartmentRepository;
import com.ats.gfplsecurity.repository.EmployeeDisplayRepository;
import com.ats.gfplsecurity.repository.EmployeeRepository;
import com.ats.gfplsecurity.repository.GateRepository;
import com.ats.gfplsecurity.repository.LocationDisplayRepo;
import com.ats.gfplsecurity.repository.LocationRepo;
import com.ats.gfplsecurity.repository.MaterialGatepassRepository;
import com.ats.gfplsecurity.repository.NotificationRepository;
import com.ats.gfplsecurity.repository.ProductionAccessRepository;
import com.ats.gfplsecurity.repository.PurposeDisplayRepository;
import com.ats.gfplsecurity.repository.PurposeRepository;
import com.ats.gfplsecurity.repository.SalaryBracketRepository;
import com.ats.gfplsecurity.repository.SettingsRepository;
import com.ats.gfplsecurity.repository.VisitCardRepository;
import com.ats.gfplsecurity.repository.VisitorRepository;

@RestController
@RequestMapping("/master")
public class MasterController {

	@Autowired
	PurposeRepository purposeRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeCategoryRepository employeeCategoryRepository;

	@Autowired
	EmployeeDepartmentRepository employeeDepartmentRepository;

	@Autowired
	EmpTypeRepository empTypeRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	GateRepository gateRepository;

	@Autowired
	ProductionAccessRepository productionAccessRepository;

	@Autowired
	SalaryBracketRepository salaryBracketRepository;

	@Autowired
	EmpGatepassRepository empGatepassRepository;

	@Autowired
	VisitorRepository visitorRepository;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	MaterialGatepassRepository materialGatepassRepository;

	@Autowired
	DocumentHandoverRepository documentHandoverRepository;

	@Autowired
	VisitCardRepository visitCardRepository;

	@Autowired
	PurposeDisplayRepository purposeDisplayRepository;

	@Autowired
	SettingsRepository settingsRepository;

	@Autowired
	EmployeeCategoryDisplayRepo employeeCategoryDisplayRepo;

	@Autowired
	EmpDeptDisplayRepo empDeptDisplayRepo;

	@Autowired
	LocationRepo locationRepo;

	@Autowired
	LocationDisplayRepo locationDisplayRepo;

	@Autowired
	EmployeeDisplayRepository employeeDisplayRepository;

	// --Get all Purposes--
	@GetMapping("/allPurposes")
	public List<Purpose> getAllPurposes() {
		return purposeRepository.findAllByDelStatus(1);
	}

	// --Get all Purposes--
	@GetMapping("/getAllPurposes")
	public List<PurposeDisplay> getAllPurposesWithName() {
		return purposeDisplayRepository.getAllPurposeList();
	}

	// --Get all Purposes by type--
	@PostMapping("/getAllPurposesByType")
	public List<Purpose> getAllPurposesByType(@RequestParam(value = "typeList") ArrayList<Integer> typeList) {
		return purposeRepository.findByDelStatusAndPurposeTypeIn(1, typeList);
	}

	// --Save new Purpose--
	@PostMapping("/savePurpose")
	public Purpose savePurpose(@RequestBody Purpose purpose) {
		return purposeRepository.save(purpose);
	}

	// --Get Purpose By Id--
	@PostMapping("/getPurposeById")
	public Purpose getPurposeById(@RequestParam(value = "purposeId") int purposeId) {
		return purposeRepository.findByPurposeId(purposeId);
	}

	// ---Update Purpose--
	@PostMapping("/updatePurpose")
	public Purpose updateNote(@RequestBody Purpose purposeDetails) {

		Purpose purpose = purposeRepository.findById(purposeDetails.getPurposeId())
				.orElseThrow(() -> new ResourceNotFoundException("Purpose", "id", purposeDetails.getPurposeId()));

		purpose.setPurposeHeading(purpose.getPurposeHeading());
		purpose.setPurposeType(purpose.getPurposeType());
		purpose.setDescription(purpose.getDescription());
		purpose.setDelStatus(purpose.getDelStatus());
		purpose.setEmpId(purpose.getEmpId());
		purpose.setNotificationL4(purpose.getNotificationL4());
		purpose.setPassDuration(purpose.getPassDuration());
		purpose.setRemark(purpose.getRemark());
		purpose.setIsUsed(purpose.getIsUsed());
		purpose.setExInt1(purpose.getExInt1());
		purpose.setExInt2(purpose.getExInt2());
		purpose.setExInt3(purpose.getExInt3());
		purpose.setExVar1(purpose.getExVar1());
		purpose.setExVar2(purpose.getExVar2());
		purpose.setExVar3(purpose.getExVar3());

		Purpose updatedPurpose = purposeRepository.save(purpose);
		return updatedPurpose;
	}

	// --Delete Purpose--
	@PostMapping("/deletePurpose")
	public Info deleteNote(@RequestParam(value = "purposeId") int purposeId) {
		Info info = null;

		Purpose purpose = purposeRepository.findByPurposeId(purposeId);

		if (purpose != null) {
			purpose.setDelStatus(0);
			Purpose updatedPurpose = purposeRepository.save(purpose);
			info = new Info();
			info.setError(false);
			info.setMessage("Purpose Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Purpose Deletion Failed");
		}
		return info;
	}

	// --Login Emp By Dsc No--
	@PostMapping("/login")
	public Employee login(@RequestParam(value = "dscNumber") String dscNumber) {
		return employeeRepository.findByEmpDsc(dscNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Dsc Number", dscNumber));
	}

	// --Get all Employees--
	@GetMapping("/allEmployees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAllByDelStatus(1);
	}

	// --Get all Employees--
	@GetMapping("/allEmployeeList")
	public List<EmployeeDisplay> getAllEmployeeList() {
		return employeeDisplayRepository.getAllEmpList();
	}

	// --Get all Supervisor Employees--
	@GetMapping("/allSupervisorList")
	public List<Employee> getAllSupervisorList() {

		Settings settings = settingsRepository.findBySettingId(2);
		List<Employee> result = new ArrayList();

		try {

			if (settings != null) {
				result = employeeRepository.findAllByDelStatusAndEmpCatId(1,
						Integer.parseInt(settings.getSettingValue()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// --Save Employee--
	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// --Get Employee By Id--
	@PostMapping("/getEmployeeById")
	public Employee getEmployeeById(@RequestParam(value = "empId") int empId) {
		return employeeRepository.findByEmpIdAndDelStatus(empId, 1);
	}

	// --Delete Employee--
	@PostMapping("/deleteEmployee")
	public Info deleteEmployee(@RequestParam(value = "empId") int empId) {
		Info info = null;

		Employee employee = employeeRepository.findByEmpIdAndDelStatus(empId, 1);

		if (employee != null) {
			employee.setDelStatus(0);
			Employee updatedEmployee = employeeRepository.save(employee);
			info = new Info();
			info.setError(false);
			info.setMessage("Employee Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Employee Deletion Failed");
		}
		return info;
	}

	// --Get all Employees Category--
	@GetMapping("/allEmployeeCategory")
	public List<EmployeeCategory> getAllEmployeeCategory() {
		return employeeCategoryRepository.findAllByDelStatus(1);
	}

	@GetMapping("/allEmployeeCategoryList")
	public List<EmployeeCategoryDisplay> getAllEmployeeCategoryList() {
		return employeeCategoryDisplayRepo.getEmpCategorylist();
	}

	// --Save Employee Category--
	@PostMapping("/saveEmployeeCategory")
	public EmployeeCategory saveEmployeeCategory(@RequestBody EmployeeCategory employeeCategory) {
		return employeeCategoryRepository.save(employeeCategory);
	}

	// --Get Employee Category By Id--
	@PostMapping("/getEmployeeCategoryById")
	public EmployeeCategory getEmployeeCategoryById(@RequestParam(value = "empCatId") int empCatId) {
		return employeeCategoryRepository.findByEmpCatId(empCatId);
	}

	// --Delete Employee Category--
	@PostMapping("/deleteEmployeeCategory")
	public Info deleteEmployeeCategory(@RequestParam(value = "empCatId") int empCatId) {
		Info info = null;

		EmployeeCategory employeeCat = employeeCategoryRepository.findByEmpCatId(empCatId);

		if (employeeCat != null) {
			employeeCat.setDelStatus(0);
			EmployeeCategory updatedEmployeeCat = employeeCategoryRepository.save(employeeCat);
			info = new Info();
			info.setError(false);
			info.setMessage("Employee Category Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Employee Category Deletion Failed");
		}
		return info;
	}

	// --Get all Employees Department--
	@GetMapping("/allEmployeeDepartment")
	public List<EmployeeDepartment> getAllEmployeeDepartment() {
		return employeeDepartmentRepository.findAllByDelStatus(1);
	}

	// --Get all Employees Department--
	@GetMapping("/allEmployeeDepartmentList")
	public List<EmpDeptDisplay> getAllEmployeeDepartmentList() {
		return empDeptDisplayRepo.getEmpDeptlist();
	}

	// --Save Employee Department--
	@PostMapping("/saveEmployeeDepartment")
	public EmployeeDepartment saveEmployeeDepartment(@RequestBody EmployeeDepartment employeeDepartment) {
		return employeeDepartmentRepository.save(employeeDepartment);
	}

	// --Get Employee Department By Id--
	@PostMapping("/getEmployeeDepartmentById")
	public EmployeeDepartment getEmployeeDepartmentById(@RequestParam(value = "empDeptId") int empDeptId) {
		return employeeDepartmentRepository.findByEmpDeptIdAndDelStatus(empDeptId, 1);
	}

	// --Delete Employee Department--
	@PostMapping("/deleteEmployeeDepartment")
	public Info deleteEmployeeDepartment(@RequestParam(value = "empDeptId") int empDeptId) {
		Info info = null;

		EmployeeDepartment employeeDept = employeeDepartmentRepository.findByEmpDeptIdAndDelStatus(empDeptId, 1);

		if (employeeDept != null) {
			employeeDept.setDelStatus(0);
			EmployeeDepartment updatedEmployeeDept = employeeDepartmentRepository.save(employeeDept);
			info = new Info();
			info.setError(false);
			info.setMessage("Employee Department Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Employee Department Deletion Failed");
		}
		return info;
	}

	// --Get all Employee Type--
	@GetMapping("/allEmployeeType")
	public List<EmpType> allEmployeeType() {
		return empTypeRepository.findAllByDelStatus(1);
	}

	// --Save Employee Type--
	@PostMapping("/saveEmployeeType")
	public EmpType saveEmployeeType(@RequestBody EmpType empType) {
		return empTypeRepository.save(empType);
	}

	// --Get Employee Type By Id--
	@PostMapping("/getEmployeeTypeById")
	public EmpType getEmployeeTypeById(@RequestParam(value = "empTypeId") int empTypeId) {
		return empTypeRepository.findByEmpTypeId(empTypeId);
	}

	// --Delete Employee Type--
	@PostMapping("/deleteEmployeeType")
	public Info deleteEmployeeType(@RequestParam(value = "empTypeId") int empTypeId) {
		Info info = null;

		EmpType empType = empTypeRepository.findByEmpTypeId(empTypeId);

		if (empType != null) {
			empType.setDelStatus(0);
			EmpType updatedEmpType = empTypeRepository.save(empType);
			info = new Info();
			info.setError(false);
			info.setMessage("Employee Type Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Employee Type Deletion Failed");
		}
		return info;
	}

	// --Get all Company--
	@GetMapping("/allCompany")
	public List<Company> getAllCompany() {
		return companyRepository.findAllByDelStatus(1);
	}

	// --Save Company--
	@PostMapping("/saveCompany")
	public Company saveCompany(@RequestBody Company company) {

		System.err.println("comp is::" + company.toString());
		return companyRepository.save(company);
	}

	// --Get Company By Id--
	@PostMapping("/getCompanyById")
	public Company getCompanyById(@RequestParam(value = "companyId") int companyId) {
		return companyRepository.findByCompanyId(companyId);
	}

	// --Delete Company--
	@PostMapping("/deleteCompany")
	public Info deleteCompany(@RequestParam(value = "companyId") int companyId) {
		Info info = null;

		Company company = companyRepository.findByCompanyId(companyId);

		if (company != null) {
			company.setDelStatus(0);
			Company updatedCompany = companyRepository.save(company);
			info = new Info();
			info.setError(false);
			info.setMessage("Company Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Company Deletion Failed");
		}
		return info;
	}

	// --Get all Gate--
	@GetMapping("/allGate")
	public List<Gate> getAllGate() {
		return gateRepository.findAllByDelStatus(1);
	}

	// --Save Gate--
	@PostMapping("/saveGate")
	public Gate saveGate(@RequestBody Gate gate) {
		return gateRepository.save(gate);
	}

	// --Get Gate By Id--
	@PostMapping("/getGateById")
	public Gate getGateById(@RequestParam(value = "gateId") int gateId) {
		return gateRepository.findByGateId(gateId);
	}

	// --Delete Gate--
	@PostMapping("/deleteGate")
	public Info deleteGate(@RequestParam(value = "gateId") int gateId) {
		Info info = null;

		Gate gate = gateRepository.findByGateId(gateId);

		if (gate != null) {
			gate.setDelStatus(0);
			Gate updatedGate = gateRepository.save(gate);
			info = new Info();
			info.setError(false);
			info.setMessage("Company Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Company Deletion Failed");
		}
		return info;
	}

	// --Get all Gate--
	@GetMapping("/allProductionAccess")
	public List<ProductionAccess> getAllProductionAccess() {
		return productionAccessRepository.findAllByDelStatus(1);
	}

	// --Save Gate--
	@PostMapping("/saveProductionAccess")
	public ProductionAccess saveGate(@RequestBody ProductionAccess productionAccess) {
		return productionAccessRepository.save(productionAccess);
	}

	// --Get Gate By Id--
	@PostMapping("/getProductionAccessById")
	public ProductionAccess getProductionAccessIdById(
			@RequestParam(value = "productionAccessId") int productionAccessId) {
		return productionAccessRepository.findByProductionAccessId(productionAccessId);
	}

	// --Delete Gate--
	@PostMapping("/deleteProductionAccess")
	public Info deleteProductionAccess(@RequestParam(value = "productionAccessId") int productionAccessId) {
		Info info = null;

		ProductionAccess productionAccess = productionAccessRepository.findByProductionAccessId(productionAccessId);

		if (productionAccess != null) {
			productionAccess.setDelStatus(0);
			ProductionAccess updatedProductionAccess = productionAccessRepository.save(productionAccess);
			info = new Info();
			info.setError(false);
			info.setMessage("Production Access Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Production Access Deletion Failed");
		}
		return info;
	}

	// --Get all Salary Bracket--
	@GetMapping("/allSalaryBracket")
	public List<SalaryBracket> getAllSalaryBracket() {
		return salaryBracketRepository.findAllByDelStatus(1);
	}

	// --Save Salary Bracket--
	@PostMapping("/saveSalaryBracket")
	public SalaryBracket saveSalaryBracket(@RequestBody SalaryBracket salaryBracket) {
		return salaryBracketRepository.save(salaryBracket);
	}

	// --Get Salary Bracket By Id--
	@PostMapping("/getSalaryBracketById")
	public SalaryBracket getSalaryBracketById(@RequestParam(value = "salaryId") int salaryId) {
		return salaryBracketRepository.findBySalaryId(salaryId);
	}

	// --Delete Salary Bracket--
	@PostMapping("/deleteSalaryBracket")
	public Info deleteSalaryBracket(@RequestParam(value = "salaryId") int salaryId) {
		Info info = null;

		SalaryBracket salaryBracket = salaryBracketRepository.findBySalaryId(salaryId);

		if (salaryBracket != null) {
			salaryBracket.setDelStatus(0);
			SalaryBracket updatedSalaryBracket = salaryBracketRepository.save(salaryBracket);
			info = new Info();
			info.setError(false);
			info.setMessage("Salary Bracket Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Salary Bracket Deletion Failed");
		}
		return info;
	}

	// --Get all Employee Gatepass--
	@GetMapping("/allEmployeeGatepass")
	public List<EmpGatepass> getAllEmployeeGatepass() {
		return empGatepassRepository.findAllByDelStatus(1);
	}

	// --Save Employee Gatepass--
	@PostMapping("/saveEmployeeGatepass")
	public EmpGatepass saveEmployeeGatepass(@RequestBody EmpGatepass empGatepass) {

		EmpGatepass result = null;

		try {
			Settings settings = settingsRepository.findBySettingId(1);

			empGatepass.setExVar1(settings.getSettingKey() + "" + settings.getSettingValue());

			result = empGatepassRepository.save(empGatepass);

			if (result != null) {
				int val = Integer.parseInt(settings.getSettingValue());
				int value = val + 1;
				int updateRes = settingsRepository.updateValue(1, "" + value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// --Get Employee Gatepass By Id--
	@PostMapping("/getEmployeeGatepassById")
	public EmpGatepass getEmployeeGatepassById(@RequestParam(value = "gatepassEmpId") int gatepassEmpId) {
		return empGatepassRepository.findByGatepassEmpId(gatepassEmpId);
	}

	// --Delete Employee Gatepass--
	@PostMapping("/deleteEmployeeGatepass")
	public Info deleteEmployeeGatepass(@RequestParam(value = "gatepassEmpId") int gatepassEmpId) {
		Info info = null;

		EmpGatepass employeeGatepass = empGatepassRepository.findByGatepassEmpId(gatepassEmpId);

		if (employeeGatepass != null) {
			employeeGatepass.setDelStatus(0);
			EmpGatepass updatedEmpGatepass = empGatepassRepository.save(employeeGatepass);
			info = new Info();
			info.setError(false);
			info.setMessage("Employee Gatepass Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Employee Gatepass Deletion Failed");
		}
		return info;
	}

	// --Get all Visitor--
	@GetMapping("/allVisitor")
	public List<Visitor> getAllVisitor() {
		return visitorRepository.findAllByDelStatus(1);
	}

	// --Save Visitor--
	@PostMapping("/saveVisitor")
	public Visitor saveVisitor(@RequestBody Visitor visitor) {
		return visitorRepository.save(visitor);
	}

	// --Get Visitor By Id--
	@PostMapping("/getVisitorById")
	public Visitor getVisitorById(@RequestParam(value = "gatepassVisitorId") int gatepassVisitorId) {
		return visitorRepository.findByGatepassVisitorId(gatepassVisitorId);
	}

	// --Delete Visitor--
	@PostMapping("/deleteVisitor")
	public Info deleteVisitor(@RequestParam(value = "gatepassVisitorId") int gatepassVisitorId) {
		Info info = null;

		Visitor visitor = visitorRepository.findByGatepassVisitorId(gatepassVisitorId);

		if (visitor != null) {
			visitor.setDelStatus(0);
			Visitor updatedVisitor = visitorRepository.save(visitor);
			info = new Info();
			info.setError(false);
			info.setMessage("Visitor Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Visitor Deletion Failed");
		}
		return info;
	}

	// --Get all Notification--
	@GetMapping("/allNotification")
	public List<Notification> getAllNotification() {
		return notificationRepository.findAllByDelStatus(1);
	}

	// --Save Notification--
	@PostMapping("/saveNotification")
	public Notification saveNotification(@RequestBody Notification notification) {
		return notificationRepository.save(notification);
	}

	// --Get Notification By Id--
	@PostMapping("/getNotificationById")
	public Notification getNotificationById(@RequestParam(value = "notificationId") int notificationId) {
		return notificationRepository.findByNotificationId(notificationId);
	}

	// --Get Notification By Id--
	@PostMapping("/getNotificationByGatepassId")
	public List<Notification> getNotificationByGatepassId(
			@RequestParam(value = "gatepassVisitorId") int gatepassVisitorId) {
		return notificationRepository.findByGatepassVisitorIdAndDelStatus(gatepassVisitorId, 1);
	}

	// --Delete Notification--
	@PostMapping("/deleteNotification")
	public Info deleteNotification(@RequestParam(value = "notificationId") int notificationId) {
		Info info = null;

		Notification notification = notificationRepository.findByNotificationId(notificationId);

		if (notification != null) {
			notification.setDelStatus(0);
			Notification updatedNotification = notificationRepository.save(notification);
			info = new Info();
			info.setError(false);
			info.setMessage("Notification Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Notification Deletion Failed");
		}
		return info;
	}

	// --Get all Material Gatepass--
	@GetMapping("/allMaterialGatepass")
	public List<MaterialGatepass> getAllMaterialGatepass() {
		return materialGatepassRepository.findAllByDelStatus(1);
	}

	// --Save Material Gatepass--
	@PostMapping("/saveMaterialGatepass")
	public MaterialGatepass saveMaterialGatepass(@RequestBody MaterialGatepass materialGatepass) {
		return materialGatepassRepository.save(materialGatepass);
	}

	// --Get Material Gatepass By Id--
	@PostMapping("/getMaterialGatepassById")
	public MaterialGatepass getMaterialGatepassById(@RequestParam(value = "gatepassInwardId") int gatepassInwardId) {
		return materialGatepassRepository.findByGatepassInwardId(gatepassInwardId);
	}

	// --Delete Material Gatepass--
	@PostMapping("/deleteMaterialGatepass")
	public Info deleteMaterialGatepass(@RequestParam(value = "gatepassInwardId") int gatepassInwardId) {
		Info info = null;

		MaterialGatepass materialGatepass = materialGatepassRepository.findByGatepassInwardId(gatepassInwardId);

		if (materialGatepass != null) {
			materialGatepass.setDelStatus(0);
			MaterialGatepass updatedMaterialGatepass = materialGatepassRepository.save(materialGatepass);
			info = new Info();
			info.setError(false);
			info.setMessage("Material Gatepass Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Material Gatepass Deletion Failed");
		}
		return info;
	}

	// --Get all Document Handover--
	@GetMapping("/allDocumentHandover")
	public List<DocumentHandover> getAllDocumentHandover() {
		return documentHandoverRepository.findAllByDelStatus(1);
	}

	// --Save Document Handover--
	@PostMapping("/saveDocumentHandover")
	public DocumentHandover saveDocumentHandover(@RequestBody DocumentHandover documentHandover) {
		return documentHandoverRepository.save(documentHandover);
	}

	// --Get Document Handover By Id--
	@PostMapping("/getDocumentHandoverById")
	public DocumentHandover getDocumentHandoverById(
			@RequestParam(value = "documentHandoverId") int documentHandoverId) {
		return documentHandoverRepository.findByDocumentHandoverId(documentHandoverId);
	}

	// --Delete Document Handover--
	@PostMapping("/deleteDocumentHandover")
	public Info deleteDocumentHandover(@RequestParam(value = "documentHandoverId") int documentHandoverId) {
		Info info = null;

		DocumentHandover documentHandover = documentHandoverRepository.findByDocumentHandoverId(documentHandoverId);

		if (documentHandover != null) {
			documentHandover.setDelStatus(0);
			DocumentHandover updateddocumentHandover = documentHandoverRepository.save(documentHandover);
			info = new Info();
			info.setError(false);
			info.setMessage("Document Handover Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Document Handover Deletion Failed");
		}
		return info;
	}

	// --Get all Visit Card--
	@GetMapping("/allVisitCard")
	public List<VisitCard> getAllVisitCard() {
		return visitCardRepository.findAllByDelStatus(1);
	}

	// --Get All Active Visit Card--
	@GetMapping("/allActiveVisitCard")
	public List<VisitCard> getAllActiveVisitCard() {
		return visitCardRepository.findAllByDelStatusAndIsActive(1, 1);
	}

	// --Save Visit Card--
	@PostMapping("/saveVisitCard")
	public VisitCard saveVisitCard(@RequestBody VisitCard visitCard) {
		return visitCardRepository.save(visitCard);
	}

	// --Get Visit Card By Id--
	@PostMapping("/getVisitCardById")
	public VisitCard getVisitCardById(@RequestParam(value = "cardId") int cardId) {
		return visitCardRepository.findByCardId(cardId);
	}

	// --Delete VisitCard--
	@PostMapping("/deleteVisitCard")
	public Info deleteVisitCard(@RequestParam(value = "cardId") int cardId) {
		Info info = null;

		VisitCard visitCard = visitCardRepository.findByCardId(cardId);

		if (visitCard != null) {
			visitCard.setDelStatus(0);
			VisitCard updatedVisitCard = visitCardRepository.save(visitCard);
			info = new Info();
			info.setError(false);
			info.setMessage("Visit Card Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Visit Card Deletion Failed");
		}
		return info;
	}

	// --Active/De-Active Visit Card--
	@PostMapping("/setStatusOfVisitCard")
	public Info setStatusOfVisitCard(@RequestParam(value = "cardId") int cardId,
			@RequestParam(value = "status") int status) {
		Info info = null;

		VisitCard visitCard = visitCardRepository.findByCardId(cardId);

		if (visitCard != null) {
			visitCard.setIsActive(status);
			VisitCard updatedVisitCard = visitCardRepository.save(visitCard);
			info = new Info();
			info.setError(false);
			info.setMessage("Visit Card Status Changed Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Visit Card Status Failed");
		}
		return info;
	}

	// -- Update App token
	@RequestMapping(value = { "/updateToken" }, method = RequestMethod.POST)
	public @ResponseBody Info updateToken(@RequestParam("empId") int empId, @RequestParam("token") String token) {

		Info info = new Info();

		try {
			int res = employeeRepository.updateUserToken(empId, token);

			if (res == 1) {
				info.setError(false);
				info.setMessage("Successfully Updated Token");
			} else {
				info.setError(true);
				info.setMessage(" Error Failed to Update Token");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("Exce Failed to Update Token ");

		}
		return info;

	}

	// --Get all Location--
	@GetMapping("/allLocation")
	public List<Location> getAllLocation() {
		return locationRepo.findAllByDelStatus(1);
	}

	@GetMapping("/allLocationList")
	public List<LocationDisplay> getAllLocationList() {
		return locationDisplayRepo.getAllLocList();
	}

	// --Save Visit Card--
	@PostMapping("/saveLocation")
	public Location saveLocation(@RequestBody Location location) {
		return locationRepo.save(location);
	}

	// --Get Visit Card By Id--
	@PostMapping("/getLocById")
	public Location getLocById(@RequestParam(value = "locId") int locId) {
		return locationRepo.findByLocId(locId);
	}

	// --Delete VisitCard--
	@PostMapping("/deleteLocation")
	public Info deleteLocation(@RequestParam(value = "locId") int locId) {
		Info info = null;

		Location location = locationRepo.findByLocId(locId);

		if (location != null) {
			location.setDelStatus(0);
			Location updateLoc = locationRepo.save(location);
			info = new Info();
			info.setError(false);
			info.setMessage("Location Deleted Successfully");
		} else {
			info = new Info();
			info.setError(true);
			info.setMessage("Location Deletion Failed");
		}
		return info;
	}

	// --Get all Settings--
	@GetMapping("/allSettings")
	public List<Settings> getAllSettings() {
		return settingsRepository.findAll();
	}

	@PostMapping("/getSettingsByKey")
	public Settings getSettingsByKey(@RequestParam("key") String key) {
		return settingsRepository.findBySettingKey(key);
	}

}
