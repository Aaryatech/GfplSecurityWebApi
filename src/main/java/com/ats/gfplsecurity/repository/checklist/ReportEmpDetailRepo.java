package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.checklist.ReportEmpDetail;

public interface ReportEmpDetailRepo extends JpaRepository<ReportEmpDetail, Integer> {

	
	@Query(value = "SELECT\r\n" + 
			"    ah.action_header_id,\r\n" + 
			"    ah.action_date,\r\n" + 
			"    ah.action_datetime,\r\n" + 
			"    ah.checklist_name,\r\n" + 
			"    ah.closed_date,\r\n" + 
			"    ah.closed_datetime,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                emp_sname\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            emp_info\r\n" + 
			"        WHERE\r\n" + 
			"            emp_id = ah.closed_by\r\n" + 
			"    ),\r\n" + 
			"    ''\r\n" + 
			"    ) AS closed_by_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header h\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND d.action_header_id = h.action_header_id AND d.checklist_master_header_id = ah.checklist_header_id AND h.action_date BETWEEN :fromDate AND :toDate AND h.action_by = :empId\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS total_detail_task,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header h\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND d.action_header_id = h.action_header_id AND d.checklist_master_header_id = ah.checklist_header_id AND h.action_date BETWEEN :fromDate AND :toDate AND h.action_by = :empId AND d.check_status = 0\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS pending_count,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header h\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND d.action_header_id = h.action_header_id AND d.checklist_master_header_id = ah.checklist_header_id AND h.action_date BETWEEN :fromDate AND :toDate AND h.action_by = :empId AND d.check_status = 2\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS approved_count,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header h\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND d.action_header_id = h.action_header_id AND d.checklist_master_header_id = ah.checklist_header_id AND h.action_date BETWEEN :fromDate AND :toDate AND h.action_by = :empId AND d.check_status = 3\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS rejected_count\r\n" + 
			"FROM\r\n" + 
			"    t_checklist_action_header ah\r\n" + 
			"WHERE\r\n" + 
			"    ah.del_status = 1 AND ah.status = 1 AND ah.action_by = :empId AND ah.action_date BETWEEN :fromDate AND :toDate\r\n" + 
			"ORDER BY\r\n" + 
			"    ah.checklist_name AND ah.action_date ", nativeQuery = true)
	List<ReportEmpDetail> getReportEmpDetail(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("empId") int empId);  

	
}
