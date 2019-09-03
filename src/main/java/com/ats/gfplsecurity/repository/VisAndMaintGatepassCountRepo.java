package com.ats.gfplsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.VisAndMaintGatepassCount;

public interface VisAndMaintGatepassCountRepo extends JpaRepository<VisAndMaintGatepassCount, Integer> {

	@Query(value = "SELECT uuid() as id, COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=0),0) as visitor_pending ,"
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status IN(1,3)),0) as visitor_approved , "
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=2),0) as visitor_rejected,  "
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status>3),0) as visitor_completed, "

			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status IN(1,3,4)),0) as visitor_in_comp, "

			+ " 0 as visitor_total, \r\n"
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=0 AND FIND_IN_SET(:empId,v.emp_ids)),0) "
			+ "as emp_visitor_pending , "
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status IN(1,3) AND FIND_IN_SET(:empId,v.emp_ids)),0)"
			+ " as emp_visitor_approved ,"
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=2 AND FIND_IN_SET(:empId,v.emp_ids)),0)"
			+ " as emp_visitor_rejected ,"
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status>3 AND FIND_IN_SET(:empId,v.emp_ids)),0) as emp_visitor_completed,"
			+ "0 as emp_visitor_total,\r\n"
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status=0),0) as maint_pending ,"
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status IN(1,3)),0) as maint_approved , "
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status=2),0) as maint_rejected,  "
			+ "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
			+ "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status>3),0) as maint_completed, "
			+ "0 as maint_total", nativeQuery = true)
	VisAndMaintGatepassCount getVisAndMaintCount(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("empId") Integer empId);

	/*
	 * @Query(value =
	 * "SELECT uuid() as id, COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=0),0) as visitor_pending ,"
	 * +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status IN(1,3)),0) as visitor_approved , "
	 * +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=2),0) as visitor_rejected,  "
	 * +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status>3),0) as visitor_completed, "
	 * 
	 * +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status IN(1,3,4)),0) as visitor_in_comp, "
	 * 
	 * + "0 as visitor_in_comp, 0 as visitor_total, \r\n" +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=0 AND FIND_IN_SET(:empId,v.emp_ids)),0) "
	 * + "as emp_visitor_pending , " +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=1 AND FIND_IN_SET(:empId,v.emp_ids)),0)"
	 * + " as emp_visitor_approved ," +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status=2 AND FIND_IN_SET(:empId,v.emp_ids)),0)"
	 * + " as emp_visitor_rejected ," +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=1 AND v.visit_status>=3 AND FIND_IN_SET(:empId,v.emp_ids)),0) as emp_visitor_completed,"
	 * + "0 as emp_visitor_total,\r\n" +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status=0),0) as maint_pending ,"
	 * +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status IN(1,3)),0) as maint_approved , "
	 * +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status=2),0) as maint_rejected,  "
	 * +
	 * "COALESCE((SELECT COUNT(*) FROM t_gatepass_visitor v WHERE v.del_status=1 AND v.visit_date_in "
	 * +
	 * "BETWEEN :fromDate AND :toDate AND v.gate_passtype=2 AND v.visit_status>3),0) as maint_completed, "
	 * + "0 as maint_total", nativeQuery = true) VisAndMaintGatepassCount
	 * getVisAndMaintCount(
	 * 
	 * @Param("fromDate") String fromDate,
	 * 
	 * @Param("toDate") String toDate,
	 * 
	 * @Param("empId") Integer empId);
	 */

}
