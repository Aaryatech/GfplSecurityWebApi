package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.checklist.ChecklistHeader;

public interface ChecklistHeaderRepo  extends JpaRepository<ChecklistHeader, Integer> {

	List<ChecklistHeader> findAllByDelStatus(int i);
	
	List<ChecklistHeader> findAllByDelStatusAndIsUsed(int i,int j);

	ChecklistHeader findByChecklistHeaderId(int checklistHeaderId);
	
	List<ChecklistHeader> findAllByDelStatusAndIsUsedAndDeptId(int i,int j,int id);
	
	
	@Query(value = "select h.* FROM m_dept_checklist_header h WHERE h.del_status=1 AND h.is_used=1 AND h.dept_id=:deptId AND h.checklist_header_id NOT IN(SELECT checklist_header_id FROM t_checklist_assign WHERE dept_id=:deptId AND del_status=1) ", nativeQuery = true)
	List<ChecklistHeader> getUnAssignedChecklistByDept(@Param("deptId") int deptId);   
	
}
