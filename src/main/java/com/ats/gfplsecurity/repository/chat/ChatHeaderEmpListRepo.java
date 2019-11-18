package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatHeaderEmpList;

public interface ChatHeaderEmpListRepo extends JpaRepository<ChatHeaderEmpList, Integer> {

	@Query(value = " SELECT e.emp_id, concat(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as name,e.emp_photo as photo, CASE WHEN e.emp_id=h.created_user_id THEN 1 ELSE CASE WHEN FIND_IN_SET(e.emp_id,h.admin_user_ids) THEN 2 ELSE 3 END END AS user_type FROM emp_info e INNER JOIN t_chat_task_header h ON FIND_IN_SET( e.emp_id, CONCAT( h.created_user_id, ',', h.admin_user_ids, ',', h.assign_user_ids ) ) > 0 WHERE h.header_id=:headerId   ", nativeQuery = true)
	List<ChatHeaderEmpList> getChatHeaderEmps(@Param("headerId") int headerId);

}