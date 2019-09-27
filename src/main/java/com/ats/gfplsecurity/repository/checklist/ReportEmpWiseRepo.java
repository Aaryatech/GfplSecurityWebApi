package com.ats.gfplsecurity.repository.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.gfplsecurity.model.checklist.ReportEmpWise;

public interface ReportEmpWiseRepo extends JpaRepository<ReportEmpWise, Integer> {

	
	@Query(value = "SELECT\r\n" + 
			"    ah.action_by AS emp_id,\r\n" + 
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
			"    ) AS emp_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_assign a\r\n" + 
			"        WHERE\r\n" + 
			"            a.del_status = 1 AND FIND_IN_SET(ah.action_by, a.assign_emp_ids)\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS total_assign,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_header a\r\n" + 
			"        WHERE\r\n" + 
			"            a.action_by = ah.action_by AND a.status = 1 AND a.del_status = 1 AND a.action_date BETWEEN :fromDate AND :toDate\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS total_task,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header a\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND a.action_header_id = d.action_header_id AND a.action_by = ah.action_by AND a.action_date BETWEEN :fromDate AND :toDate AND a.status = 1 AND a.del_status = 1\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS total_detail_task,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header a\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND a.action_header_id = d.action_header_id AND a.action_by = ah.action_by AND d.check_status = 0 AND a.action_date BETWEEN :fromDate AND :toDate AND a.status = 1\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS pending_count,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header a\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND a.action_header_id = d.action_header_id AND a.action_by = ah.action_by AND d.check_status = 2 AND a.action_date BETWEEN :fromDate AND :toDate AND a.status = 1\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS approved_count,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            COUNT(*)\r\n" + 
			"        FROM\r\n" + 
			"            t_checklist_action_detail d,\r\n" + 
			"            t_checklist_action_header a\r\n" + 
			"        WHERE\r\n" + 
			"            d.del_status = 1 AND a.action_header_id = d.action_header_id AND a.action_by = ah.action_by AND d.check_status = 3 AND a.action_date BETWEEN :fromDate AND :toDate AND a.status = 1\r\n" + 
			"    ),\r\n" + 
			"    0\r\n" + 
			"    ) AS rejected_count\r\n" + 
			"FROM\r\n" + 
			"    t_checklist_action_header ah,\r\n" + 
			"    emp_info e,\r\n" + 
			"    m_emp_department d\r\n" + 
			"WHERE\r\n" + 
			"    ah.action_by = e.emp_id AND ah.status = 1 AND ah.del_status = 1 AND ah.action_date BETWEEN :fromDate AND :toDate AND e.del_status = 1 AND ah.dept_id IN(:deptId) AND ah.dept_id = d.emp_dept_id\r\n" + 
			"GROUP BY\r\n" + 
			"    ah.action_by\r\n" + 
			"ORDER BY\r\n" + 
			"    d.emp_dept_name AND e.emp_fname ", nativeQuery = true)
	List<ReportEmpWise> getReportEmpWise(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("deptId") List<Integer> deptId);  

	
	
}
