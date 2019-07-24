package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.OutwardGatepassDisplay;

public interface OutwardGatepassDisplayRepo extends JpaRepository<OutwardGatepassDisplay, Integer> {

	@Query(value = "SELECT o.*,COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM "
			+ "emp_info e WHERE e.emp_id=o.sec_id_out),'na') as sec_out_name,"
			+ "COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM emp_info e "
			+ "WHERE e.emp_id=o.sec_id_in),'na') as sec_in_name FROM t_outward_gatepass o WHERE o.del_status=1 "
			+ "AND o.date_in BETWEEN :fromDate AND :toDate AND o.status IN(:status) AND o.emp_id IN(:empId) ", nativeQuery = true)
	List<OutwardGatepassDisplay> getOutwardGatepassListByEmpAndDate(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("empId") List<Integer> empId, @Param("status") List<Integer> status);

	@Query(value = "SELECT o.*,COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM "
			+ "emp_info e WHERE e.emp_id=o.sec_id_out),'na') as sec_out_name,"
			+ "COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM emp_info e "
			+ "WHERE e.emp_id=o.sec_id_in),'na') as sec_in_name FROM t_outward_gatepass o WHERE o.del_status=1 "
			+ "AND o.date_in BETWEEN :fromDate AND :toDate AND o.status IN(:status)", nativeQuery = true)
	List<OutwardGatepassDisplay> getOutwardGatepassListByDate(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("status") List<Integer> status);

	@Query(value = "SELECT o.*,COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM "
			+ "emp_info e WHERE e.emp_id=o.sec_id_out),'na') as sec_out_name,"
			+ "COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM emp_info e "
			+ "WHERE e.emp_id=o.sec_id_in),'na') as sec_in_name FROM t_outward_gatepass o WHERE o.del_status=1 "
			+ "AND o.status IN(:status) AND o.emp_id IN(:empId) ", nativeQuery = true)
	List<OutwardGatepassDisplay> getOutwardGatepassListByEmp(@Param("empId") List<Integer> empId,
			@Param("status") List<Integer> status);

	@Query(value = "SELECT o.*,COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM "
			+ "emp_info e WHERE e.emp_id=o.sec_id_out),'na') as sec_out_name,"
			+ "COALESCE((SELECT CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) FROM emp_info e "
			+ "WHERE e.emp_id=o.sec_id_in),'na') as sec_in_name FROM t_outward_gatepass o WHERE o.del_status=1 "
			+ "AND o.status IN(:status)", nativeQuery = true)
	List<OutwardGatepassDisplay> getOutwardGatepassList(@Param("status") List<Integer> status);

}
