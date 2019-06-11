package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.DocumentHandover;

@Repository
public interface DocumentHandoverRepository extends JpaRepository<DocumentHandover, Integer> {

	List<DocumentHandover> findAllByDelStatus(int i);
	
	List<DocumentHandover> findByDelStatusAndGatepassInwardId(int del,int gpId);

	DocumentHandover findByDocumentHandoverId(int documentHandoverId);

}