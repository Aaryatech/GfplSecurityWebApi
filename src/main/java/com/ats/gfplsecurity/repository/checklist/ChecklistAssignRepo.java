package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.checklist.ChecklistAssign;

public interface ChecklistAssignRepo extends JpaRepository<ChecklistAssign, Integer> {

	List<ChecklistAssign> findAllByDelStatus(int i);

	ChecklistAssign findByAssignId(int assignId);

}
