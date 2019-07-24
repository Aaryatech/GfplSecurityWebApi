package com.ats.gfplsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.OutwardGatepass;

public interface OutwardGatepassRepository extends JpaRepository<OutwardGatepass, Integer> {

	List<OutwardGatepass> findAllByDelStatus(int i);

	OutwardGatepass findByGpOutwardId(int gpOutwardId);

	@Transactional
	@Modifying
	@Query("update OutwardGatepass set sec_id_out=:secId , out_time=:outTime , out_photo=:photo , status=1  WHERE gp_outward_id=:gpOutwardId")
	int updateOutStatus(@Param("secId") int secId, @Param("outTime") String outTime, @Param("photo") String photo,
			@Param("gpOutwardId") int gpOutwardId);

	@Transactional
	@Modifying
	@Query("update OutwardGatepass set sec_id_in=:secId , in_time=:inTime , in_photo=:photo , date_in=:inDate , status=2 WHERE gp_outward_id=:gpOutwardId")
	int updateInStatus(@Param("secId") int secId, @Param("inTime") String inTime, @Param("inDate") String inDate,
			@Param("photo") String photo, @Param("gpOutwardId") int gpOutwardId);

	@Transactional
	@Modifying
	@Query("update OutwardGatepass set status=:status WHERE gp_outward_id=:gpOutwardId")
	int updateStatus(@Param("status") int status, @Param("gpOutwardId") int gpOutwardId);

}