package com.ats.gfplsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.MatGatepassEmpWiseCount;

public interface MatGatepassEmpWiseCountRepo extends JpaRepository<MatGatepassEmpWiseCount, Integer> {

/*	@Query(value = "SELECT uuid() as id, "
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.to_emp_id=:empId AND g.to_status=0)) as emp_pending_count, "
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.to_emp_id=:empId AND g.to_status=1)) as emp_approved_count,"
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 AND g.inward_date "
			+ "BETWEEN :fromDate AND :toDate AND g.to_emp_id=:empId AND g.to_status=2)) as emp_rejected_count", nativeQuery = true)
	MatGatepassEmpWiseCount getMatGatepassCount(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("empId") int empId);*/
	
	
	@Query(value = "SELECT uuid() as id, "
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1 "
			+ " AND g.to_emp_id=:empId AND g.to_status=0)) as emp_pending_count, "
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1  "
			+ " AND g.to_emp_id=:empId AND g.to_status=1)) as emp_approved_count,"
			+ "COALESCE((SELECT COUNT(*) FROM t_inward_gatepass g WHERE g.del_status=1  "
			+ " AND g.to_emp_id=:empId AND g.to_status=2)) as emp_rejected_count", nativeQuery = true)
	MatGatepassEmpWiseCount getMatGatepassCount(
			@Param("empId") int empId);

}
