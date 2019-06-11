package com.ats.gfplsecurity.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Purpose;

@Repository
public interface PurposeRepository extends JpaRepository<Purpose, Integer>{

	List<Purpose> findAllByDelStatus(int delStatus);

	Purpose findByPurposeId(int purposeId);

}
