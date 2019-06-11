package com.ats.gfplsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Employee;
import com.ats.gfplsecurity.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{

	List<Notification> findAllByDelStatus(int i);
	
	Notification findByNotificationId(int notificationId);

	List<Notification> findByGatepassVisitorIdAndDelStatus(int gatepassVisitorId, int i);

	
	@Transactional
	@Modifying
	@Query("update Notification set status=:status WHERE emp_id=:empId and gatepass_visitor_id=:gatepassVisitorId")
	int updateNotificationStatus(@Param("gatepassVisitorId") int gatepassVisitorId,@Param("empId") int empId,@Param("status") int status);
 

	
	
}
