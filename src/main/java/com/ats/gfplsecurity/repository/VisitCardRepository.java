package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.VisitCard;

@Repository
public interface VisitCardRepository extends JpaRepository<VisitCard, Integer> {

	List<VisitCard> findAllByDelStatus(int i);

	List<VisitCard> findAllByDelStatusAndIsActive(int i,int j);

	VisitCard findByCardId(int companyId);

}
