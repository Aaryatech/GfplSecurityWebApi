package com.ats.gfplsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.EmpGatepass;
import com.ats.gfplsecurity.model.Employee;

@Repository
public interface EmpGatepassRepository extends JpaRepository<EmpGatepass, Integer> {

	List<EmpGatepass> findAllByDelStatus(int i);

	EmpGatepass findByGatepassEmpId(int gatepassEmpId);
	
	@Transactional
	@Modifying
	@Query("update EmpGatepass set gate_pass_status=:status, emp_date_out=:date, emp_date_in=:date, new_out_time=:outTime, new_in_time=:inTime, actual_time_difference=:timeDiff, security_id_out=:empId, security_id_in=:empId WHERE gatepass_emp_id=:empGatepassId ")
	int updateEmpGatepassStatusToClose(@Param("empGatepassId") int empGatepassId,@Param("empId") int empId,@Param("status") int status,@Param("date") String date,@Param("outTime") String outTime,@Param("inTime") String inTime,@Param("timeDiff") String timeDiff);

	@Transactional
	@Modifying
	@Query("update EmpGatepass set gate_pass_status=:status, emp_date_out=:date, new_out_time=:outTime, security_id_out=:empId  WHERE gatepass_emp_id=:empGatepassId ")
	int updateEmpGatepassStatusToOut(@Param("empGatepassId") int empGatepassId, @Param("empId") int empId, @Param("status") int status, @Param("date") String date, @Param("outTime") String outTime);

	@Transactional
	@Modifying
	@Query("update EmpGatepass set gate_pass_status=:status,  emp_date_in=:date,  new_in_time=:inTime, actual_time_difference=:timeDiff, security_id_in=:empId WHERE gatepass_emp_id=:empGatepassId ")
	int updateEmpGatepassStatusToIn(@Param("empGatepassId") int empGatepassId, @Param("empId") int empId,@Param("status") int status, @Param("date") String date, @Param("inTime") String inTime, @Param("timeDiff") String timeDiff);


}
