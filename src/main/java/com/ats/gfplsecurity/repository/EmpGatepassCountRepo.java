package com.ats.gfplsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.EmpGatepassCount;

public interface EmpGatepassCountRepo extends JpaRepository<EmpGatepassCount, Integer> {

	@Query(value = "SELECT uuid() as id,COALESCE((SELECT COUNT(*) FROM t_emp_gatepass g WHERE g.del_status=1 "
			+ "AND g.emp_date_out BETWEEN :fromDate AND :toDate AND g.gate_pass_sub_type=1),0) as temp_gp_count, "
			+ "COALESCE((SELECT COUNT(*) FROM t_emp_gatepass g WHERE g.del_status=1 AND g.emp_date_out "
			+ "BETWEEN :fromDate AND :toDate AND g.gate_pass_sub_type=2),0) as day_gp_count , "
			+ "COALESCE((SELECT COUNT(*) FROM t_emp_gatepass g WHERE g.del_status=1 AND g.emp_date_out "
			+ "BETWEEN :fromDate AND :toDate AND g.gate_pass_status=1),0) as out_emp_count", nativeQuery = true)
	EmpGatepassCount getEmpGatepassCount(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
