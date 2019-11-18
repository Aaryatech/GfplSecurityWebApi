package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatGroupDisplay;

public interface ChatGroupDisplayRepo extends JpaRepository<ChatGroupDisplay, Integer> {

	@Query(value = " SELECT g.*, CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as created_by_name, (SELECT GROUP_CONCAT(emp_fname,' ',emp_mname,' ',emp_sname) FROM emp_info WHERE emp_info.del_status=1 AND FIND_IN_SET(emp_info.emp_id,g.user_ids)) as user_names FROM m_chat_task_group g,emp_info e WHERE g.del_status=1 AND g.group_created_user_id=e.emp_id  AND e.del_status=1 AND g.is_active=:isActive ", nativeQuery = true)
	List<ChatGroupDisplay> getAllChatGroupDisplay(@Param("isActive") int isActive);

	
	@Query(value = " SELECT g.*, CONCAT( e.emp_fname, ' ', e.emp_mname, ' ', e.emp_sname ) AS created_by_name, ( SELECT GROUP_CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_info.del_status = 1 AND FIND_IN_SET(emp_info.emp_id, g.user_ids) ) AS user_names FROM m_chat_task_group g, emp_info e WHERE g.del_status = 1 AND g.group_created_user_id = e.emp_id AND e.del_status = 1 AND g.is_active = 1 AND FIND_IN_SET(:userId ,(SELECT CONCAT(user_ids,',',group_created_user_id) FROM m_chat_task_group WHERE group_id=g.group_id)) ", nativeQuery = true)
	List<ChatGroupDisplay> getAllChatGroupDisplayByUser(@Param("userId") int userId);


	/*@Query(value = " SELECT g.*, CONCAT( e.emp_fname, ' ', e.emp_mname, ' ', e.emp_sname ) AS created_by_name, ( SELECT GROUP_CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_info.del_status = 1 AND FIND_IN_SET(emp_info.emp_id, g.user_ids) ) AS user_names FROM m_chat_task_group g, emp_info e WHERE g.del_status = 1 AND g.group_created_user_id = e.emp_id AND e.del_status = 1 AND g.is_active = 1 AND FIND_IN_SET(:userId  , g.user_ids) ", nativeQuery = true)
	List<ChatGroupDisplay> getAllChatGroupDisplayByUser(@Param("userId") int userId);*/

	@Query(value = " SELECT g.*, CONCAT( e.emp_fname, ' ', e.emp_mname, ' ', e.emp_sname ) AS created_by_name, ( SELECT GROUP_CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_info.del_status = 1 AND FIND_IN_SET(emp_info.emp_id, g.user_ids) ) AS user_names FROM m_chat_task_group g, emp_info e WHERE g.del_status = 1 AND g.group_created_user_id = e.emp_id AND e.del_status = 1 AND g.is_active = 1 AND g.group_created_user_id=:userId  ", nativeQuery = true)
	List<ChatGroupDisplay> getAllChatGroupDisplayMasterByUser(@Param("userId") int userId);

	
}
