package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.duty.DutyHeader;

public interface DutyHeaderRepo extends JpaRepository<DutyHeader, Integer> {

	List<DutyHeader> findAllByDelStatus(int i);

	DutyHeader findByDutyId(int dutyId);

}
