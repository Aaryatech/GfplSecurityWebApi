package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.MaterialGatepass;
import com.ats.gfplsecurity.model.MaterialGatepassDisplay;

@Repository
public interface MaterialGatepassRepository extends JpaRepository<MaterialGatepass, Integer> {

	List<MaterialGatepass> findAllByDelStatus(int i);

	MaterialGatepass findByGatepassInwardId(int gatepassInwardId);
	
	@Query(value = " SELECT * FROM t_inward_gatepass WHERE del_status=1 AND gatepass_inward_id IN(:ids) ", nativeQuery = true)
	List<MaterialGatepass> getMatGPList(
			@Param("ids") List<Integer> ids);   

}
