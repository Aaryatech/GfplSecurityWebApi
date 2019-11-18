package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.ChatHeaderDisplay;

public interface ChatHeaderDisplayRepo extends JpaRepository<ChatHeaderDisplay, Integer>{

	@Query(value = " SELECT h.*, CONCAT(e.emp_fname,' ',e.emp_mname,' ',e.emp_sname) as created_by_name, (SELECT GROUP_CONCAT(emp_fname,' ',emp_mname,' ',emp_sname) FROM emp_info WHERE emp_info.del_status=1 AND FIND_IN_SET(emp_info.emp_id,h.admin_user_ids)) as admin_user_names, (SELECT GROUP_CONCAT(emp_fname,' ',emp_mname,' ',emp_sname) FROM emp_info WHERE emp_info.del_status=1 AND FIND_IN_SET(emp_info.emp_id,h.assign_user_ids)) as assign_user_names, (SELECT GROUP_CONCAT(emp_fname,' ',emp_mname,' ',emp_sname) FROM emp_info WHERE emp_info.del_status=1 AND emp_info.emp_id=h.request_user_id) as request_user_name, (SELECT GROUP_CONCAT(emp_fname,' ',emp_mname,' ',emp_sname) FROM emp_info WHERE emp_info.del_status=1 AND emp_info.emp_id=h.task_close_user_id) as task_close_user_name, 0 as privilege FROM t_chat_task_header h,emp_info e WHERE h.del_status=1 AND h.created_user_id=e.emp_id AND e.del_status=1 AND h.is_active=:isActive ", nativeQuery = true)
	List<ChatHeaderDisplay> getAllChatHeaderDisplay(@Param("isActive") int isActive);

	@Query(value = " SELECT\r\n" + 
			"    h.*,\r\n" + 
			"    CONCAT(\r\n" + 
			"        e.emp_fname,\r\n" + 
			"        ' ',\r\n" + 
			"        e.emp_mname,\r\n" + 
			"        ' ',\r\n" + 
			"        e.emp_sname\r\n" + 
			"    ) AS created_by_name,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND FIND_IN_SET(\r\n" + 
			"            emp_info.emp_id,\r\n" + 
			"            h.admin_user_ids\r\n" + 
			"        )\r\n" + 
			") AS admin_user_names,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND FIND_IN_SET(\r\n" + 
			"            emp_info.emp_id,\r\n" + 
			"            h.assign_user_ids\r\n" + 
			"        )\r\n" + 
			") AS assign_user_names,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND emp_info.emp_id = h.request_user_id\r\n" + 
			") AS request_user_name,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND emp_info.emp_id = h.task_close_user_id\r\n" + 
			") AS task_close_user_name,\r\n" + 
			"(\r\n" + 
			"    CASE WHEN(h.created_user_id = :userId) THEN 1 ELSE(\r\n" + 
			"        CASE WHEN(\r\n" + 
			"            FIND_IN_SET(:userId, h.admin_user_ids)\r\n" + 
			"        ) THEN 2 ELSE(\r\n" + 
			"            CASE WHEN(\r\n" + 
			"                FIND_IN_SET(:userId, h.assign_user_ids)\r\n" + 
			"            ) THEN 3 ELSE 0\r\n" + 
			"        END\r\n" + 
			"    )\r\n" + 
			"END\r\n" + 
			")\r\n" + 
			"END\r\n" + 
			") AS privilege\r\n" + 
			"FROM\r\n" + 
			"    t_chat_task_header h,\r\n" + 
			"    emp_info e\r\n" + 
			"WHERE\r\n" + 
			"    h.del_status = 1 AND h.created_user_id = e.emp_id AND e.del_status = 1 AND h.is_active = 1 AND h.status IN(0,1) AND FIND_IN_SET(\r\n" + 
			"        :userId,\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                created_user_id,\r\n" + 
			"                ',',\r\n" + 
			"                admin_user_ids,\r\n" + 
			"                ',',\r\n" + 
			"                assign_user_ids\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            t_chat_task_header\r\n" + 
			"        WHERE\r\n" + 
			"            header_id = h.header_id\r\n" + 
			"    )\r\n" + 
			"    ) ", nativeQuery = true)
	List<ChatHeaderDisplay> getAllChatHeaderDisplayByUserId(@Param("userId") int userId);
	
	
	@Query(value = " SELECT\r\n" + 
			"    h.*,\r\n" + 
			"    CONCAT(\r\n" + 
			"        e.emp_fname,\r\n" + 
			"        ' ',\r\n" + 
			"        e.emp_mname,\r\n" + 
			"        ' ',\r\n" + 
			"        e.emp_sname\r\n" + 
			"    ) AS created_by_name,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND FIND_IN_SET(\r\n" + 
			"            emp_info.emp_id,\r\n" + 
			"            h.admin_user_ids\r\n" + 
			"        )\r\n" + 
			") AS admin_user_names,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND FIND_IN_SET(\r\n" + 
			"            emp_info.emp_id,\r\n" + 
			"            h.assign_user_ids\r\n" + 
			"        )\r\n" + 
			") AS assign_user_names,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND emp_info.emp_id = h.request_user_id\r\n" + 
			") AS request_user_name,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND emp_info.emp_id = h.task_close_user_id\r\n" + 
			") AS task_close_user_name,\r\n" + 
			"0 AS privilege\r\n" + 
			"FROM\r\n" + 
			"    t_chat_task_header h,\r\n" + 
			"    emp_info e\r\n" + 
			"WHERE\r\n" + 
			"    h.del_status = 1 AND h.created_user_id = e.emp_id AND e.del_status = 1 AND h.is_active = 1 AND h.status = 2 AND h.ex_var2 BETWEEN :fromDate AND :toDate\r\n" + 
			"ORDER BY\r\n" + 
			"    h.ex_var2\r\n" + 
			"DESC\r\n" + 
			"     ", nativeQuery = true)
	List<ChatHeaderDisplay> getAllClosedChatHeaderDisplay(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	
	@Query(value = "SELECT\r\n" + 
			"    h.*,\r\n" + 
			"    CONCAT(\r\n" + 
			"        e.emp_fname,\r\n" + 
			"        ' ',\r\n" + 
			"        e.emp_mname,\r\n" + 
			"        ' ',\r\n" + 
			"        e.emp_sname\r\n" + 
			"    ) AS created_by_name,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND FIND_IN_SET(\r\n" + 
			"            emp_info.emp_id,\r\n" + 
			"            h.admin_user_ids\r\n" + 
			"        )\r\n" + 
			") AS admin_user_names,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND FIND_IN_SET(\r\n" + 
			"            emp_info.emp_id,\r\n" + 
			"            h.assign_user_ids\r\n" + 
			"        )\r\n" + 
			") AS assign_user_names,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND emp_info.emp_id = h.request_user_id\r\n" + 
			") AS request_user_name,\r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(\r\n" + 
			"            emp_fname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_mname,\r\n" + 
			"            ' ',\r\n" + 
			"            emp_sname\r\n" + 
			"        )\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        emp_info.del_status = 1 AND emp_info.emp_id = h.task_close_user_id\r\n" + 
			") AS task_close_user_name,\r\n" + 
			"(\r\n" + 
			"    CASE WHEN(h.created_user_id = :userId) THEN 1 ELSE(\r\n" + 
			"        CASE WHEN(\r\n" + 
			"            FIND_IN_SET(:userId, h.admin_user_ids)\r\n" + 
			"        ) THEN 2 ELSE(\r\n" + 
			"            CASE WHEN(\r\n" + 
			"                FIND_IN_SET(:userId, h.assign_user_ids)\r\n" + 
			"            ) THEN 3 ELSE 0\r\n" + 
			"        END\r\n" + 
			"    )\r\n" + 
			"END\r\n" + 
			")\r\n" + 
			"END\r\n" + 
			") AS privilege\r\n" + 
			"FROM\r\n" + 
			"    t_chat_task_header h,\r\n" + 
			"    emp_info e\r\n" + 
			"WHERE\r\n" + 
			"    h.del_status = 1 AND h.created_user_id = e.emp_id AND e.del_status = 1 AND h.is_active = 1 AND h.status IN(0, 1) AND h.ex_int1=:groupId AND FIND_IN_SET(\r\n" + 
			"        :userId,\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                created_user_id,\r\n" + 
			"                ',',\r\n" + 
			"                admin_user_ids,\r\n" + 
			"                ',',\r\n" + 
			"                assign_user_ids\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            t_chat_task_header\r\n" + 
			"        WHERE\r\n" + 
			"            header_id = h.header_id\r\n" + 
			"    )\r\n" + 
			"    )", nativeQuery = true)
	List<ChatHeaderDisplay> getAllChatHeaderDisplayByUserIdAndGroup(@Param("userId") int userId,@Param("groupId") int groupId);
	
}
