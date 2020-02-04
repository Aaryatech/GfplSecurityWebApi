package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatDisplay;

public interface ChatDisplayRepo extends JpaRepository<ChatDisplay, Integer> {

	@Query(value = " SELECT chat_task_detail_id, header_id,type_of_text,text_value,user_id,user_name,del_status,mark_as_read, CASE WHEN c.user_id=:userId THEN c.local_date ELSE c.server_date END AS date_time FROM t_chat_task_detail c WHERE c.del_status=1 AND c.header_id=:headerId AND c.chat_task_detail_id>:lastSyncId   ", nativeQuery = true)
	List<ChatDisplay> getChatByHeaderAndLastSyncId(@Param("headerId") int headerId,@Param("lastSyncId") int lastSyncId,@Param("userId") int userId);

//	@Query(value = " SELECT chat_task_detail_id, header_id, type_of_text, text_value, user_id, user_name, del_status, mark_as_read, CASE WHEN c.user_id = :userId THEN c.local_date ELSE c.server_date END AS date_time FROM t_chat_task_detail c WHERE c.del_status = 1 AND FIND_IN_SET(c.header_id ,(SELECT GROUP_CONCAT(header_id) FROM t_chat_task_header WHERE del_status = 1 AND FIND_IN_SET( :userId , CONCAT( created_user_id, ',', admin_user_ids, ',', assign_user_ids ) ) > 0))>0 AND c.chat_task_detail_id > :lastSyncId     ", nativeQuery = true)
//	List<ChatDisplay> getChatByLastSyncIdAndUserId(@Param("lastSyncId") int lastSyncId,@Param("userId") int userId);


	//21-11-2019----Reply to chat
	@Query(value = " SELECT\r\n" + 
			"    chat_task_detail_id,\r\n" + 
			"    header_id,\r\n" + 
			"    type_of_text,\r\n" + 
			"    text_value,\r\n" + 
			"    user_id,\r\n" + 
			"    user_name,\r\n" + 
			"    del_status,\r\n" + 
			"    mark_as_read,\r\n" + 
			"    CASE WHEN c.user_id = :userId THEN c.local_date ELSE c.server_date\r\n" + 
			"END AS date_time,\r\n" + 
			"ex_int1 as reply_to_id,\r\n" + 
			"    (SELECT type_of_text FROM t_chat_task_detail WHERE chat_task_detail_id=c.ex_int1) as reply_to_msg_type,\r\n" + 
			"    (SELECT text_value FROM t_chat_task_detail WHERE chat_task_detail_id=c.ex_int1) as reply_to_msg,\r\n" + 
			"    (SELECT user_name FROM t_chat_task_detail WHERE chat_task_detail_id=c.ex_int1) as reply_to_name\r\n" + 
			"FROM\r\n" + 
			"    t_chat_task_detail c\r\n" + 
			"WHERE\r\n" + 
			"    c.del_status = 1 AND FIND_IN_SET(\r\n" + 
			"        c.header_id,\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            GROUP_CONCAT(header_id)\r\n" + 
			"        FROM\r\n" + 
			"            t_chat_task_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 1 AND FIND_IN_SET(\r\n" + 
			"                :userId,\r\n" + 
			"                CONCAT(\r\n" + 
			"                    created_user_id,\r\n" + 
			"                    ',',\r\n" + 
			"                    admin_user_ids,\r\n" + 
			"                    ',',\r\n" + 
			"                    assign_user_ids\r\n" + 
			"                )\r\n" + 
			"            ) > 0\r\n" + 
			"    )\r\n" + 
			"    ) > 0 AND c.chat_task_detail_id > :lastSyncId    ", nativeQuery = true)
	List<ChatDisplay> getChatByLastSyncIdAndUserId(@Param("lastSyncId") int lastSyncId,@Param("userId") int userId);

}
