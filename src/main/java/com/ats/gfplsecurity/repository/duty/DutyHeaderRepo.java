package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.duty.DutyHeader;

@Repository
public interface DutyHeaderRepo extends JpaRepository<DutyHeader, Integer> {

	List<DutyHeader> findAllByDelStatus(int i);

	DutyHeader findByDutyId(int dutyId);
	
	@Transactional
	@Modifying
	@Query("update DutyHeader set total_task_wt=:totalWt  WHERE duty_id=:dutyId")
	int updateTotalWt(@Param("dutyId") int dutyId,@Param("totalWt") float totalWt);
	

}
