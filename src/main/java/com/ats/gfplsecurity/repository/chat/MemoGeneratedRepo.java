package com.ats.gfplsecurity.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.chat.MemoGenerated;

public interface MemoGeneratedRepo extends JpaRepository<MemoGenerated, Integer>{

	@Query(value = " SELECT m.memo_id, m.user_id, COALESCE( ( SELECT CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_id = m.user_id ), '' ) AS user_name, m.generated_user_id, COALESCE( ( SELECT CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_id = m.generated_user_id ), '' ) AS generated_user_name, m.memo_desc, m.memo_date, m.task_header_id, h.header_name FROM t_chat_memo_generated m,t_chat_task_header h WHERE m.del_status = 1 AND m.task_header_id=h.header_id AND m.memo_date BETWEEN :fromDate AND :toDate  ", nativeQuery = true)
	List<MemoGenerated> getAllMemoGenerated(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	@Query(value = " SELECT m.memo_id, m.user_id, COALESCE( ( SELECT CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_id = m.user_id ), '' ) AS user_name, m.generated_user_id, COALESCE( ( SELECT CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_id = m.generated_user_id ), '' ) AS generated_user_name, m.memo_desc, m.memo_date, m.task_header_id, h.header_name FROM t_chat_memo_generated m,t_chat_task_header h WHERE m.del_status = 1 AND m.task_header_id=h.header_id AND m.memo_date BETWEEN :fromDate AND :toDate AND m.user_id=:empId  ", nativeQuery = true)
	List<MemoGenerated> getAllMemoGeneratedByDateAndEmpId(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("empId") int empId);

	@Query(value = " SELECT m.memo_id, m.user_id, COALESCE( ( SELECT CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_id = m.user_id ), '' ) AS user_name, m.generated_user_id, COALESCE( ( SELECT CONCAT( emp_fname, ' ', emp_mname, ' ', emp_sname ) FROM emp_info WHERE emp_id = m.generated_user_id ), '' ) AS generated_user_name, m.memo_desc, m.memo_date, m.task_header_id, h.header_name FROM t_chat_memo_generated m,t_chat_task_header h WHERE m.del_status = 1 AND m.task_header_id=h.header_id AND m.user_id=:empId  ", nativeQuery = true)
	List<MemoGenerated> getAllMemoGeneratedByEmpId(@Param("empId") int empId);

	
}
