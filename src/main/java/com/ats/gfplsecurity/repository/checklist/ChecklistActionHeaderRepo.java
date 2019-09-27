package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.checklist.ChecklistActionHeader;

public interface ChecklistActionHeaderRepo extends JpaRepository<ChecklistActionHeader, Integer> {

	List<ChecklistActionHeader> findAllByDelStatus(int i);

	ChecklistActionHeader findByActionHeaderId(int actionHeaderId);

	@Transactional
	@Modifying
	@Query("update ChecklistActionHeader set status=:status, closed_by=:empId, closed_date=:date, closed_datetime=:datetime WHERE action_header_id=:actionHeaderId ")
	int updateClosedHeaderStatus(@Param("actionHeaderId") int actionHeaderId, @Param("status") int status,
			@Param("empId") int empId, @Param("date") String date, @Param("datetime") String datetime);

}
