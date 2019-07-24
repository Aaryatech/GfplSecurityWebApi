package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.VisitCard;

@Repository
public interface VisitCardRepository extends JpaRepository<VisitCard, Integer> {

	List<VisitCard> findAllByDelStatus(int i);

	List<VisitCard> findAllByDelStatusAndIsActive(int i,int j);

	VisitCard findByCardId(int companyId);
	
	@Query(value="SELECT v.* FROM m_visit_card v WHERE v.del_status=1 AND v.card_id NOT IN "
			+ "(SELECT g.visit_card_id from t_gatepass_visitor g WHERE g.del_status =1 AND g.visit_status IN(3,4))",nativeQuery=true)
	List<VisitCard> getAvailCard();
	

}
