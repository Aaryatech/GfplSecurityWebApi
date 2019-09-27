package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.checklist.ClosedChecklist;

public interface ClosedChecklistRepo extends JpaRepository<ClosedChecklist, Integer> {

	@Query(value = "SELECT ah.*,d.emp_dept_name as dept_name,COALESCE((SELECT CONCAT(emp_fname, ' ',emp_mname,' ',emp_sname) FROM emp_info WHERE emp_id=ah.action_by),'') as action_by_name, COALESCE((SELECT CONCAT(emp_fname, ' ',emp_mname,' ',emp_sname) FROM emp_info WHERE emp_id=ah.closed_by),'') as closed_by_name FROM t_checklist_action_header ah, m_emp_department d WHERE ah.del_status=1 AND d.del_status=1 AND ah.dept_id=d.emp_dept_id AND ah.status=0 AND ah.assign_id IN((SELECT assign_id FROM t_checklist_assign WHERE del_status = 1 AND FIND_IN_SET(:empId,assign_emp_ids))) ", nativeQuery = true)
	List<ClosedChecklist> getClosedChecklist(@Param("empId") int empId);   

}
