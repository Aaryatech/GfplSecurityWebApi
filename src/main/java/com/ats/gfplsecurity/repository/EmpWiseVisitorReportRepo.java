package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.EmpWiseVisitorReport;
import com.ats.gfplsecurity.model.EmployeeDisplay;


public interface EmpWiseVisitorReportRepo extends JpaRepository<EmpWiseVisitorReport, Integer> {
	
	@Query(value = "SELECT emp_id, CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) AS emp_name,"
			+ " ( SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status = 1 "
			+ "AND v.visit_date_in BETWEEN :fromDate AND :toDate "
			+ "AND v.gate_passtype=:type AND v.visit_status=:status "
			+ "AND FIND_IN_SET(emp_id, v.emp_ids) ) AS count FROM emp_info "
			+ "WHERE del_status = 1 AND FIND_IN_SET(emp_info.emp_id, :empIds)", nativeQuery = true)
	List<EmpWiseVisitorReport> getEmpWiseReport(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("type") int type,
			@Param("status") int status,
			@Param("empIds") String empIds);   

	
	@Query(value = "SELECT emp_id, CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) AS emp_name,"
			+ " ( SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status = 1 "
			+ "AND v.visit_date_in BETWEEN :fromDate AND :toDate "
			+ "AND v.gate_passtype=:type AND v.visit_status=:status "
			+ "AND FIND_IN_SET(emp_id, v.emp_ids) ) AS count FROM emp_info "
			+ "WHERE del_status = 1 ", nativeQuery = true)
	List<EmpWiseVisitorReport> getEmpWiseReportAllEmp(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("type") int type,
			@Param("status") int status);   

	
	
	@Query(value = "SELECT emp_id, CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) AS emp_name,"
			+ " ( SELECT COUNT(*) FROM t_emp_gatepass e WHERE e.del_status = 1 AND e.emp_date_out "
			+ "BETWEEN :fromDate AND :toDate AND e.user_id=emp_info.emp_id ) AS count "
			+ "FROM emp_info WHERE del_status = 1 AND emp_info.emp_dept_id IN (:deptIds) "
			+ "AND emp_info.emp_cat_id =(SELECT setting_value from t_settings WHERE setting_id=2)", nativeQuery = true)
	List<EmpWiseVisitorReport> getEmpGatepassReportForSupervisor(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("deptIds") List<Integer> deptIds);
	
	
	@Query(value = "SELECT emp_id, CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) AS emp_name, "
			+ "( SELECT COUNT(*) FROM t_emp_gatepass e WHERE e.del_status = 1 "
			+ "AND e.emp_date_out BETWEEN :fromDate AND :toDate "
			+ "AND e.user_id=emp_info.emp_id ) AS count FROM emp_info "
			+ "WHERE del_status = 1 AND emp_info.emp_cat_id =(SELECT setting_value from t_settings "
			+ "WHERE setting_id=2)", nativeQuery = true)
	List<EmpWiseVisitorReport> getEmpGatepassReportForAllSupervisor(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
	
	

}
