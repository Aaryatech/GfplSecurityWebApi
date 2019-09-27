package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.checklist.ChecklistCountReport;

public interface ChecklistCountReportRepo extends JpaRepository<ChecklistCountReport, Integer> {
	
	@Query(value = "SELECT\r\n" + 
			"    ah.*,\r\n" + 
			"    d.emp_dept_name AS dept_name,\r\n" + 
			"    'na' AS action_by_name,\r\n" + 
			"    'na' AS closed_by_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header h\r\n" + 
			"        WHERE\r\n" + 
			"            d.action_header_id = h.action_header_id AND h.status = 1 AND d.check_status = 0 AND d.del_status = 1 AND h.action_date BETWEEN :fromDate AND :toDate AND h.dept_id IN(ah.dept_id) AND h.del_status = 1\r\n" + 
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
			"            d.action_header_id = h.action_header_id AND h.status = 1 AND d.check_status = 2 AND d.del_status = 1 AND h.action_date BETWEEN :fromDate AND :toDate AND h.dept_id IN(ah.dept_id) AND h.del_status = 1\r\n" + 
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
			"            d.action_header_id = h.action_header_id AND h.status = 1 AND d.check_status = 3 AND d.del_status = 1 AND h.action_date BETWEEN :fromDate AND :toDate AND h.dept_id IN(ah.dept_id) AND h.del_status = 1\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS rejected_count\r\n" + 
			"FROM\r\n" + 
			"    t_checklist_action_header ah,\r\n" + 
			"    m_emp_department d\r\n" + 
			"WHERE\r\n" + 
			"    ah.del_status = 1 AND ah.dept_id = d.emp_dept_id AND d.del_status = 1 AND ah.action_date BETWEEN :fromDate AND :toDate \r\n" + 
			"GROUP BY\r\n" + 
			"    ah.dept_id\r\n" + 
			"ORDER BY\r\n" + 
			"    d.emp_dept_name ", nativeQuery = true)
	List<ChecklistCountReport> getReportByDeptGroupBy(@Param("fromDate") String fromDate,@Param("toDate") String toDate);  

	
	@Query(value = "SELECT\r\n" + 
			"    ah.*,\r\n" + 
			"    d.emp_dept_name AS dept_name,\r\n" + 
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
			"            emp_id = ah.action_by\r\n" + 
			"    ),\r\n" + 
			"    ''\r\n" + 
			"    ) AS action_by_name,\r\n" + 
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
			"            d.action_header_id = h.action_header_id AND h.status = 1 AND d.check_status = 0 AND d.del_status = 1 AND h.action_date BETWEEN :fromDate AND :toDate AND d.checklist_master_header_id IN(ah.checklist_header_id) AND h.del_status=1\r\n" + 
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
			"            d.action_header_id = h.action_header_id AND h.status = 1 AND d.check_status = 2 AND d.del_status = 1 AND h.action_date BETWEEN :fromDate AND :toDate AND d.checklist_master_header_id IN(ah.checklist_header_id) AND h.del_status=1\r\n" + 
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
			"            d.action_header_id = h.action_header_id AND h.status = 1 AND d.check_status = 3 AND d.del_status = 1 AND h.action_date BETWEEN :fromDate AND :toDate AND  d.checklist_master_header_id IN(ah.checklist_header_id) AND h.del_status=1\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS rejected_count\r\n" + 
			"FROM\r\n" + 
			"    t_checklist_action_header ah,\r\n" + 
			"    m_emp_department d\r\n" + 
			"WHERE\r\n" + 
			"    ah.del_status = 1 AND ah.status = 1 AND ah.action_date BETWEEN :fromDate AND :toDate AND ah.checklist_header_id IN(:headerId) AND ah.dept_id = d.emp_dept_id\r\n" + 
			"GROUP BY\r\n" + 
			"    ah.dept_id, ah.checklist_header_id ", nativeQuery = true)
	List<ChecklistCountReport> getReportByChecklistHeaderGroupBy(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("headerId") List<Integer> headerId);  



	@Query(value = "SELECT\r\n" + 
			"    ah.*,\r\n" + 
			"    d.emp_dept_name AS dept_name,\r\n" + 
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
			"            emp_id = ah.action_by\r\n" + 
			"    ),\r\n" + 
			"    ''\r\n" + 
			"    ) AS action_by_name,\r\n" + 
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
			"    ) AS closed_by_name, 0 as pending_count,0 as approved_count,0 as rejected_count\r\n" + 
			"FROM\r\n" + 
			"    t_checklist_action_header ah,\r\n" + 
			"    m_emp_department d\r\n" + 
			"WHERE\r\n" + 
			"    ah.del_status = 1 AND ah.status = 1 AND ah.action_date BETWEEN :fromDate AND :toDate AND ah.checklist_header_id IN(:headerId) AND ah.dept_id = d.emp_dept_id\r\n" + 
			" ", nativeQuery = true)
	List<ChecklistCountReport> getReportByHeader(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("headerId") int headerId);  

}
