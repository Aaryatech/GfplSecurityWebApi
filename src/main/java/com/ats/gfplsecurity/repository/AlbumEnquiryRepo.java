package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.gfplsecurity.model.AlbumEnquiry;

public interface AlbumEnquiryRepo extends JpaRepository<AlbumEnquiry, Integer> {

	List<AlbumEnquiry> findByDelStatus(int del);

	AlbumEnquiry findByEnquiryNoAndDelStatus(int enqNo, int del);

	AlbumEnquiry findByEnquiryNo(int enqNo);

}
