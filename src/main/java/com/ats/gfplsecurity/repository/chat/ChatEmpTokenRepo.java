package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatEmpToken;
import com.ats.gfplsecurity.model.chat.ChatGroupDisplay;

public interface ChatEmpTokenRepo extends JpaRepository<ChatEmpToken, Integer> {

	@Query(value = " SELECT e.emp_id, e.ex_var3 AS token, h.header_name  FROM emp_info e INNER JOIN t_chat_task_header h ON FIND_IN_SET( e.emp_id, CONCAT( h.created_user_id, ',', h.admin_user_ids, ',', h.assign_user_ids ) ) > 0 WHERE h.header_id=:headerId  ", nativeQuery = true)
	List<ChatEmpToken> getEmpTokenByHeader(@Param("headerId") int headerId);

}
