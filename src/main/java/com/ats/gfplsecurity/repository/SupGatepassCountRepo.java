package com.ats.gfplsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.SupGatepassCount;

public interface SupGatepassCountRepo extends JpaRepository<SupGatepassCount, Integer> {

	@Query(value = "SELECT uuid() as id,COALESCE((SELECT COUNT(*) FROM t_emp_gatepass g WHERE g.del_status=1 "
			+ "AND g.emp_date_out BETWEEN :fromDate AND :toDate AND g.gate_pass_sub_type=1 AND g.user_id=:supId),0) as sup_temp_count, "
			+ "COALESCE((SELECT COUNT(*) FROM t_emp_gatepass g WHERE g.del_status=1 AND g.emp_date_out "
			+ "BETWEEN :fromDate AND :toDate AND g.gate_pass_sub_type=2 AND g.user_id=:supId),0) as sup_day_count , "
			+ "COALESCE((SELECT COUNT(*) FROM t_emp_gatepass g WHERE g.del_status=1 AND g.emp_date_out "
			+ "BETWEEN :fromDate AND :toDate AND g.gate_pass_status=1 AND g.user_id=:supId),0) as sup_out_emp_count", nativeQuery = true)
	SupGatepassCount getSupGatepassCount(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("supId") int supId);

}
