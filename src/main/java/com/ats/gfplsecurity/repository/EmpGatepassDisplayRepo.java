package com.ats.gfplsecurity.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.EmpGatepassDisplay;

public interface EmpGatepassDisplayRepo extends JpaRepository<EmpGatepassDisplay, Integer>{
	

	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name  FROM t_emp_gatepass eg,emp_info e,"
			+ " m_emp_department d, m_emp_type t  WHERE eg.del_status=1 AND eg.emp_date_out BETWEEN :fromDate AND :toDate "
			+ "AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id AND e.emp_dept_id=d.emp_dept_id "
			+ "AND d.emp_dept_id IN (:deptIds) AND FIND_IN_SET(eg.user_id,:empIds) AND e.emp_type_id=t.emp_type_id AND e.del_status=1 AND d.del_status=1 AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDateByDeptByEmpByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("deptIds") List<Integer> deptIds,
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   
	
	

	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name  FROM t_emp_gatepass eg,emp_info e,"
			+ " m_emp_department d, m_emp_type t   WHERE eg.del_status=1 AND eg.emp_date_out BETWEEN :fromDate AND :toDate "
			+ "AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id AND e.emp_dept_id=d.emp_dept_id "
			+ "AND FIND_IN_SET(eg.user_id,:empIds)  AND e.emp_type_id=t.emp_type_id AND e.del_status=1 AND d.del_status=1 AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDateByEmpByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   
	
	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name  FROM t_emp_gatepass eg,emp_info e,"
			+ " m_emp_department d, m_emp_type t  WHERE eg.del_status=1 AND eg.emp_date_out BETWEEN :fromDate AND :toDate "
			+ "AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id AND e.emp_dept_id=d.emp_dept_id "
			+ "AND d.emp_dept_id IN (:deptIds)  AND e.emp_type_id=t.emp_type_id AND e.del_status=1 AND d.del_status=1 AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDateByDeptByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("deptIds") List<Integer> deptIds,
			@Param("status") List<Integer> status);   
	
	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name  FROM t_emp_gatepass eg,emp_info e,"
			+ " m_emp_department d, m_emp_type t  WHERE eg.del_status=1 AND eg.emp_date_out BETWEEN :fromDate AND :toDate "
			+ "AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id AND e.emp_dept_id=d.emp_dept_id "
			+ "AND e.emp_type_id=t.emp_type_id AND e.del_status=1 AND d.del_status=1 AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDateByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("status") List<Integer> status);   
	
	 
	
	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id AND d.emp_dept_id IN (:deptIds) "
			+ "AND FIND_IN_SET(eg.user_id,:empIds) AND e.emp_type_id=t.emp_type_id "
			+ "AND t.emp_type_id IN (:typeIds) AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByTypeByDeptByEmpByStatus(
			@Param("typeIds") List<Integer> typeIds,
			@Param("deptIds") List<Integer> deptIds,
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   

	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id AND d.emp_dept_id IN (:deptIds) "
			+ "AND FIND_IN_SET(eg.user_id,:empIds) AND e.emp_type_id=t.emp_type_id "
			+ "AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDeptByEmpByStatus(
			@Param("deptIds") List<Integer> deptIds,
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   

	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id "
			+ "AND FIND_IN_SET(eg.user_id,:empIds) AND e.emp_type_id=t.emp_type_id "
			+ "AND t.emp_type_id IN (:typeIds) AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByTypeByEmpByStatus(
			@Param("typeIds") List<Integer> typeIds,
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   
	


	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id AND d.emp_dept_id IN (:deptIds) "
			+ "AND e.emp_type_id=t.emp_type_id "
			+ "AND t.emp_type_id IN (:typeIds) AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByTypeByDeptByStatus(
			@Param("typeIds") List<Integer> typeIds,
			@Param("deptIds") List<Integer> deptIds,
			@Param("status") List<Integer> status);   
	
	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id "
			+ "AND e.emp_type_id=t.emp_type_id "
			+ "AND t.emp_type_id IN (:typeIds) AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByTypeByStatus(
			@Param("typeIds") List<Integer> typeIds,
			@Param("status") List<Integer> status);  
	
	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id AND d.emp_dept_id IN (:deptIds) "
			+ "AND e.emp_type_id=t.emp_type_id "
			+ "AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDeptByStatus(
			@Param("deptIds") List<Integer> deptIds,
			@Param("status") List<Integer> status);  

	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id "
			+ "AND FIND_IN_SET(eg.user_id,:empIds) AND e.emp_type_id=t.emp_type_id "
			+ "AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByEmpByStatus(
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   
	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.gate_pass_status IN (:status) AND eg.user_id=e.emp_id "
			+ "AND e.emp_dept_id=d.emp_dept_id  "
			+ "AND e.emp_type_id=t.emp_type_id "
			+ "AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByStatus(
			@Param("status") List<Integer> status);   
	
	
	@Query(value = " SELECT eg.*,e.emp_dept_id,d.emp_dept_name,e.emp_type_id,t.emp_type_name "
			+ "FROM t_emp_gatepass eg,emp_info e, m_emp_department d, m_emp_type t "
			+ "WHERE eg.del_status=1 AND eg.user_id=e.emp_id AND e.emp_dept_id=d.emp_dept_id  "
			+ "AND e.emp_type_id=t.emp_type_id AND e.del_status=1 AND d.del_status=1 "
			+ "AND t.del_status=1 AND eg.user_id=:userId AND eg.emp_date_out BETWEEN :fromDate AND :toDate ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByUserId(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("userId") int userId);   
	
	
	@Query(value = " SELECT eg.*,COALESCE ((SELECT e.emp_dept_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_dept_id,COALESCE ((SELECT d.emp_dept_name FROM m_emp_department d,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_dept_id=d.emp_dept_id),null) as emp_dept_name,COALESCE ((SELECT e.emp_type_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_type_id,COALESCE ((SELECT t.emp_type_name FROM m_emp_type t,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_type_id=t.emp_type_id),null) as emp_type_name from t_emp_gatepass eg WHERE eg.del_status=1 "
			+ "AND eg.user_id IN(SELECT emp_id from emp_info where del_status=1 "
			+ "AND emp_dept_id IN(:deptIds) AND emp_id IN (:empIds)) AND eg.emp_date_out BETWEEN :fromDate "
			+ "AND :toDate ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDeptByEmp(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("deptIds") List<Integer> deptIds,
			@Param("empIds") List<Integer> empIds);   
	
	@Query(value = " SELECT eg.*,COALESCE ((SELECT e.emp_dept_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_dept_id,COALESCE ((SELECT d.emp_dept_name FROM m_emp_department d,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_dept_id=d.emp_dept_id),null) as emp_dept_name,COALESCE ((SELECT e.emp_type_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_type_id,COALESCE ((SELECT t.emp_type_name FROM m_emp_type t,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_type_id=t.emp_type_id),null) as emp_type_name from t_emp_gatepass eg WHERE eg.del_status=1 "
			+ "AND eg.user_id IN(SELECT emp_id from emp_info where del_status=1 AND emp_cat_id=(SELECT setting_value from t_settings WHERE setting_id=2 )) "
			+ " AND eg.emp_date_out BETWEEN :fromDate "
			+ "AND :toDate ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByAllDeptAndAllEmp(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);   
	
	@Query(value = " SELECT eg.*,COALESCE ((SELECT e.emp_dept_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_dept_id,COALESCE ((SELECT d.emp_dept_name FROM m_emp_department d,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_dept_id=d.emp_dept_id),null) as emp_dept_name,COALESCE ((SELECT e.emp_type_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_type_id,COALESCE ((SELECT t.emp_type_name FROM m_emp_type t,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_type_id=t.emp_type_id),null) as emp_type_name from t_emp_gatepass eg WHERE eg.del_status=1 "
			+ "AND eg.user_id IN(SELECT emp_id from emp_info where del_status=1 AND emp_cat_id=(SELECT setting_value from t_settings WHERE setting_id=2 )"
			+ "AND emp_id IN (:empIds)) AND eg.emp_date_out BETWEEN :fromDate "
			+ "AND :toDate ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByAllDeptAndByEmp(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("empIds") List<Integer> empIds); 

	@Query(value = " SELECT eg.*,COALESCE ((SELECT e.emp_dept_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_dept_id,COALESCE ((SELECT d.emp_dept_name FROM m_emp_department d,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_dept_id=d.emp_dept_id),null) as emp_dept_name,COALESCE ((SELECT e.emp_type_id FROM emp_info e WHERE e.emp_id=eg.user_id),null) as emp_type_id,COALESCE ((SELECT t.emp_type_name FROM m_emp_type t,emp_info e WHERE e.emp_id=eg.user_id AND e.emp_type_id=t.emp_type_id),null) as emp_type_name from t_emp_gatepass eg WHERE eg.del_status=1 "
			+ "AND eg.user_id IN(SELECT emp_id from emp_info where del_status=1 AND emp_cat_id=(SELECT setting_value from t_settings WHERE setting_id=2 )"
			+ "AND emp_dept_id IN(:deptIds)) AND eg.emp_date_out BETWEEN :fromDate "
			+ "AND :toDate ", nativeQuery = true)
	List<EmpGatepassDisplay> getEmpGPListByDeptByAllEmp(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("deptIds") List<Integer> deptIds);

	
}
