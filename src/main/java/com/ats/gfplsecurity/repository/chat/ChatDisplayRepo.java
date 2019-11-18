package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatDisplay;

public interface ChatDisplayRepo extends JpaRepository<ChatDisplay, Integer> {

	@Query(value = " SELECT chat_task_detail_id, header_id,type_of_text,text_value,user_id,user_name,del_status,mark_as_read, CASE WHEN c.user_id=:userId THEN c.local_date ELSE c.server_date END AS date_time FROM t_chat_task_detail c WHERE c.del_status=1 AND c.header_id=:headerId AND c.chat_task_detail_id>:lastSyncId   ", nativeQuery = true)
	List<ChatDisplay> getChatByHeaderAndLastSyncId(@Param("headerId") int headerId,@Param("lastSyncId") int lastSyncId,@Param("userId") int userId);

	@Query(value = " SELECT chat_task_detail_id, header_id, type_of_text, text_value, user_id, user_name, del_status, mark_as_read, CASE WHEN c.user_id = :userId THEN c.local_date ELSE c.server_date END AS date_time FROM t_chat_task_detail c WHERE c.del_status = 1 AND FIND_IN_SET(c.header_id ,(SELECT GROUP_CONCAT(header_id) FROM t_chat_task_header WHERE del_status = 1 AND FIND_IN_SET( :userId , CONCAT( created_user_id, ',', admin_user_ids, ',', assign_user_ids ) ) > 0))>0 AND c.chat_task_detail_id > :lastSyncId     ", nativeQuery = true)
	List<ChatDisplay> getChatByLastSyncIdAndUserId(@Param("lastSyncId") int lastSyncId,@Param("userId") int userId);

}
