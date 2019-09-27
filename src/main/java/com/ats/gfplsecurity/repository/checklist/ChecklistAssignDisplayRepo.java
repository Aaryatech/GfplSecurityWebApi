package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.gfplsecurity.model.checklist.ChecklistAssignDisplay;

public interface ChecklistAssignDisplayRepo extends JpaRepository<ChecklistAssignDisplay, Integer> {

	@Query(value = "SELECT a.*,d.emp_dept_name as dept_name, ch.checklist_name,(SELECT GROUP_CONCAT(CONCAT(emp_fname, ' ',emp_mname,' ',emp_sname)) FROM emp_info WHERE FIND_IN_SET(emp_id,a.assign_emp_ids)) as assign_emp_name,CONCAT(emp_fname, ' ',emp_mname,' ',emp_sname) as assign_by_name from t_checklist_assign a,m_emp_department d,emp_info e, m_dept_checklist_header ch WHERE a.del_status=1 AND a.dept_id=d.emp_dept_id and d.del_status=1 AND a.checklist_header_id=ch.checklist_header_id AND ch.del_status=1 AND a.assigned_by=e.emp_id AND e.del_status=1 ", nativeQuery = true)
	List<ChecklistAssignDisplay> getChecklistAssignDisplaylist();   

}
