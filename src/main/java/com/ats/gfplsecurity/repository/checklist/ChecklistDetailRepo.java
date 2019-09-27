package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.checklist.ChecklistDetail;

public interface ChecklistDetailRepo extends JpaRepository<ChecklistDetail, Integer> {

	List<ChecklistDetail> findAllByDelStatus(int i);

	ChecklistDetail findByChecklistDetailId(int checklistDetailId);
	
	List<ChecklistDetail> findAllByDelStatusAndIsUsed(int i,int j);	
	
	List<ChecklistDetail> findAllByDelStatusAndIsUsedAndChecklistHeaderId(int i,int j,int id);
	
	List<ChecklistDetail> findAllByDelStatusAndChecklistHeaderId(int i,int id);

}
