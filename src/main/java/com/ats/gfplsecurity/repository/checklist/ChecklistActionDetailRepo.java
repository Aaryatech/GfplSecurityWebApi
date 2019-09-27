package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.checklist.ChecklistActionDetail;

public interface ChecklistActionDetailRepo extends JpaRepository<ChecklistActionDetail, Integer> {

	List<ChecklistActionDetail> findAllByDelStatus(int i);

	List<ChecklistActionDetail> findAllByDelStatusAndActionHeaderId(int i, int id);

	ChecklistActionDetail findByActionDetailId(int actionDetailId);

	@Transactional
	@Modifying
	@Query("update ChecklistActionDetail set check_status=:status, closed_photo=:photo, check_date=:date WHERE action_detail_id=:actionDetailId ")
	int updateClosedDetailStatus(@Param("actionDetailId") int actionDetailId, @Param("status") int status,
			@Param("photo") String photo, @Param("date") String date);

	
	@Transactional
	@Modifying
	@Query("update ChecklistActionDetail set closed_photo=:photo WHERE action_detail_id=:actionDetailId ")
	int updateClosedDetailPhoto(@Param("actionDetailId") int actionDetailId, @Param("photo") String photo);

	@Transactional
	@Modifying
	@Query("update ChecklistActionDetail set check_status=:status, check_date=:date WHERE action_detail_id IN(:actionDetailId) ")
	int updateClosedDetailStatusMultiple(@Param("actionDetailId") List<Integer> actionDetailId, @Param("status") int status, @Param("date") String date);

	
}
