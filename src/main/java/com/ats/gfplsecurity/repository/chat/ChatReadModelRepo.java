package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatReadModel;

public interface ChatReadModelRepo extends JpaRepository<ChatReadModel, Integer> {

	@Query(value = " SELECT e.emp_id, CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) AS emp_name FROM emp_info e INNER JOIN t_chat_task_detail d ON FIND_IN_SET( e.emp_id,d.ex_var1) > 0 WHERE d.chat_task_detail_id=:detailId  ", nativeQuery = true)
	List<ChatReadModel> getEmpChatRead(@Param("detailId") int detailId);

}