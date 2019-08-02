package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.duty.DutyReport;

public interface DutyReportRepo extends JpaRepository<DutyReport, Integer> {
	
	@Query(value = " SELECT UUID() as id,a.duty_id,d.duty_name,d.type,d.type_desc,d.total_task_wt,d.shift_id,s.shift_name,s.shift_from_time,s.shift_to_time,t.task_id,t.task_name_eng, t.task_desc_eng, t.task_weight, t.photo_req,t.remark_req,COALESCE ((t.ex_int1),0) as time_req, COALESCE((t.ex_var1),'na') as task_time from t_assign_duty a,m_duty_header d, m_task_detail t,m_shift s WHERE a.del_status=1 AND d.del_status=1 AND t.del_status=1 AND a.duty_id=d.duty_id AND d.duty_id=t.duty_id AND d.shift_id=s.shift_id AND FIND_IN_SET(:empId,a.emp_ids) ORDER BY d.duty_id,t.task_id  ", nativeQuery = true)
	List<DutyReport> getDutyReportByEmpId(@Param("empId") int empId);
}
