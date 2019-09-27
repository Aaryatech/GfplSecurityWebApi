package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.checklist.ChecklistHeader;

public interface ChecklistHeaderRepo  extends JpaRepository<ChecklistHeader, Integer> {

	List<ChecklistHeader> findAllByDelStatus(int i);
	
	List<ChecklistHeader> findAllByDelStatusAndIsUsed(int i,int j);

	ChecklistHeader findByChecklistHeaderId(int checklistHeaderId);
	
	List<ChecklistHeader> findAllByDelStatusAndIsUsedAndDeptId(int i,int j,int id);
	
	
}
