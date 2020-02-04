package com.ats.gfplsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.AlbumEnquiry;

public interface AlbumEnquiryRepo extends JpaRepository<AlbumEnquiry, Integer> {

	List<AlbumEnquiry> findByDelStatus(int del);

	AlbumEnquiry findByEnquiryNoAndDelStatus(int enqNo, int del);

	AlbumEnquiry findByEnquiryNo(int enqNo);

	@Transactional
	@Modifying
	@Query(value="UPDATE AlbumEnquiry SET album_id=:albmId WHERE enquiry_no=:enqId")
	int updtAlbmIdByEnqId(@Param("enqId") int enqId, @Param("albmId") int albmId);

}
