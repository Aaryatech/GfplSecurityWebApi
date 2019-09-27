package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatMemoDisplay;

public interface ChatMemoDisplayRepo extends JpaRepository<ChatMemoDisplay, Integer> {

	@Query(value = " SELECT m.*,h.header_name,CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as user_name, CONCAT(e1.emp_fname,' ',e1.emp_mname,' ',e1.emp_sname) as generated_user_name FROM t_chat_memo_generated m,emp_info e,emp_info e1,t_chat_task_header h WHERE m.del_status=1 AND e.del_status=1 AND e1.del_status=1 AND h.del_status=1 AND m.user_id=e.emp_id AND m.task_header_id=h.header_id AND m.generated_user_id=e1.emp_id ", nativeQuery = true)
	List<ChatMemoDisplay> getAllChatMemoDisplay();

	@Query(value = " SELECT m.*,h.header_name,CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as user_name, CONCAT(e1.emp_fname,' ',e1.emp_mname,' ',e1.emp_sname) as generated_user_name FROM t_chat_memo_generated m,emp_info e,emp_info e1,t_chat_task_header h WHERE m.del_status=1 AND e.del_status=1 AND e1.del_status=1 AND h.del_status=1 AND m.user_id=e.emp_id AND m.task_header_id=h.header_id AND m.generated_user_id=e1.emp_id AND m.generated_user_id=:generatedUserId ", nativeQuery = true)
	List<ChatMemoDisplay> getChatMemoByGeneratedUserId(@Param("generatedUserId") int generatedUserId);

	@Query(value = " SELECT m.*,h.header_name,CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as user_name, CONCAT(e1.emp_fname,' ',e1.emp_mname,' ',e1.emp_sname) as generated_user_name FROM t_chat_memo_generated m,emp_info e,emp_info e1,t_chat_task_header h WHERE m.del_status=1 AND e.del_status=1 AND e1.del_status=1 AND h.del_status=1 AND m.user_id=e.emp_id AND m.task_header_id=h.header_id AND m.generated_user_id=e1.emp_id AND m.task_header_id=:headerId ", nativeQuery = true)
	List<ChatMemoDisplay> getChatMemoByHeaderId(@Param("headerId") int headerId);

	
}
