package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.MaterialGatepassDisplay;

public interface MaterialGatepassDisplayRepo extends JpaRepository<MaterialGatepassDisplay, Integer>{
	
	@Query(value = " SELECT m.* "
			+ "FROM t_inward_gatepass m WHERE m.inward_date BETWEEN :fromDate AND :toDate "
			+ "AND m.del_status = 1 AND m.party_id IN(:supIds) AND m.status IN(:status) ", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatGPListByDateBySupByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("supIds") List<Integer> supIds,
			@Param("status") List<Integer> status);   
	

	@Query(value = " SELECT m.* "
			+ "FROM t_inward_gatepass m WHERE m.inward_date BETWEEN :fromDate AND :toDate "
			+ "AND m.del_status = 1 AND m.status IN(:status) ", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatGPListByDateByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("status") List<Integer> status);   
	
	
	@Query(value = " SELECT m.* "
			+ "FROM t_inward_gatepass m WHERE "
			+ "m.del_status = 1 AND m.party_id IN(:supIds) AND m.status IN(:status) ", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatGPListBySupByStatus(
			@Param("supIds") List<Integer> supIds,
			@Param("status") List<Integer> status);   
	
	
	@Query(value = " SELECT m.* "
			+ "FROM t_inward_gatepass m WHERE "
			+ "m.del_status = 1 AND m.status IN(:status) ", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatGPListByStatus(
			@Param("status") List<Integer> status);   
	

	
	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.to_emp_id IN (:empIds) "
			+ "AND m.to_dept_id IN (:deptIds) AND m.del_status=1 "
			+ "AND m.to_status IN (:status) order by m.inward_date desc", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByDeptByEmpByStatus(
			@Param("deptIds") List<Integer> deptIds,
			@Param("empIds") List<Integer> empIds,
			@Param("status") List<Integer> status);   

	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.to_emp_id IN (:empIds) "
			+ "AND m.del_status=1 "
			+ "AND m.to_status IN (:status) order by m.inward_date desc", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByEmpByStatus(
			@Param("empIds") List<Integer> empIds,
			@Param("status") List<Integer> status);   


	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE "
			+ "m.to_dept_id IN (:deptIds) AND m.del_status=1 "
			+ "AND m.to_status IN (:status) order by m.inward_date desc", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByDeptByStatus(
			@Param("deptIds") List<Integer> deptIds,
			@Param("status") List<Integer> status);   

	
	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE "
			+ "m.del_status=1 "
			+ "AND m.to_status IN (:status) order by m.inward_date desc", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByStatus(
			@Param("status") List<Integer> status);   

	
	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.to_emp_id IN (:empIds) "
			+ "AND m.to_dept_id IN (:deptIds) AND m.del_status=1 "
			+ "AND m.to_status IN (:status) AND m.inward_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByDateByDeptByEmpByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("deptIds") List<Integer> deptIds,
			@Param("empIds") List<Integer> empIds,
			@Param("status") List<Integer> status);   

	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.to_emp_id IN (:empIds) "
			+ "AND m.del_status=1 "
			+ "AND m.to_status IN (:status)  AND m.inward_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByDateByEmpByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("empIds") List<Integer> empIds,
			@Param("status") List<Integer> status);   


	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE "
			+ "m.to_dept_id IN (:deptIds) AND m.del_status=1 "
			+ "AND m.to_status IN (:status)  AND m.inward_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByDateByDeptByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("deptIds") List<Integer> deptIds,
			@Param("status") List<Integer> status);   

	
	@Query(value = " SELECT m.* from t_inward_gatepass m WHERE "
			+ "m.del_status=1 "
			+ "AND m.to_status IN (:status)  AND m.inward_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingListByDateByStatus(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("status") List<Integer> status);   

	
	
		@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.del_status=1  "
					+ "AND m.inward_date BETWEEN :fromDate AND :toDate "
					+ "AND m.party_id IN (:supIds)", nativeQuery = true)
	List<MaterialGatepassDisplay> getMatTrackingReport(
					@Param("fromDate") String fromDate,
					@Param("toDate") String toDate,
					@Param("supIds") List<Integer> supIds);   
	
			
			@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.del_status=1  "
					+ "AND m.inward_date BETWEEN :fromDate AND :toDate ", nativeQuery = true)
			List<MaterialGatepassDisplay> getMatTrackingReport(
					@Param("fromDate") String fromDate,
					@Param("toDate") String toDate);   
			
			
			
			//-----------------------------------
			
			@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.party_id IN (:supIds) "
					+ "AND m.to_dept_id IN (:deptIds) AND m.del_status=1 "
					+ "AND m.to_status IN (:status) AND m.inward_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
			List<MaterialGatepassDisplay> getMatTrackingListByDateByDeptBySupByStatus(
					@Param("fromDate") String fromDate,
					@Param("toDate") String toDate,
					@Param("deptIds") List<Integer> deptIds,
					@Param("supIds") List<Integer> supIds,
					@Param("status") List<Integer> status);   

			@Query(value = " SELECT m.* from t_inward_gatepass m WHERE m.party_id IN (:supIds) "
					+ "AND m.del_status=1 "
					+ "AND m.to_status IN (:status)  AND m.inward_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
			List<MaterialGatepassDisplay> getMatTrackingListByDateBySupByStatus(
					@Param("fromDate") String fromDate,
					@Param("toDate") String toDate,
					@Param("supIds") List<Integer> supIds,
					@Param("status") List<Integer> status);   
			
	
			
}
