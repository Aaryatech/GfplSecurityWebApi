package com.ats.gfplsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.MatGatepassCount;

public interface MatGatepassCountRepo extends JpaRepository<MatGatepassCount, Integer> {

	@Query(value = "SELECT uuid() as id, COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.gate_pass_sub_type=1)) as inward_count, "
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.gate_pass_sub_type=2)) as parcel_count, "
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.to_dept_id=:deptId AND g.to_status=0)) as dept_pending_count, "
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.to_dept_id=:deptId AND g.to_status=1)) as dept_approved_count,"
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.to_dept_id=:deptId AND g.to_status=2)) as dept_rejected_count", nativeQuery = true)
	MatGatepassCount getMatGatepassCount(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("deptId") int deptId);

}
