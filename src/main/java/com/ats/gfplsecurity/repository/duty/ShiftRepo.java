package com.ats.gfplsecurity.repository.duty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.duty.Shift;


public interface ShiftRepo extends JpaRepository<Shift, Integer> {

	List<Shift> findAllByDelStatus(int i);

	Shift findByShiftId(int shiftId);

}

