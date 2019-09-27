package com.ats.gfplsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.VisitorGatepassDisplay;

@Repository
public interface VisitorGatepassDisplayRepository extends JpaRepository<VisitorGatepassDisplay, Integer>{
	
	@Query(value = " SELECT\r\n" + 
			"    v.`gatepass_visitor_id`,\r\n" + 
			"    v.`visit_date_in`,\r\n" + 
			"    v.`security_id_in`,\r\n" + 
			"    v.`person_name`,\r\n" + 
			"    v.`person_company`,\r\n" + 
			"    v.`person_photo`,\r\n" + 
			"    v.`mobile_no`,\r\n" + 
			"    v.`id_proof`,\r\n" + 
			"    v.`id_proof1`,\r\n" + 
			"    v.`other_photo`,\r\n" + 
			"    v.`purpose_id`,\r\n" + 
			"    v.`visit_purpose_text`,\r\n" + 
			"    v.`purpose_remark`,\r\n" + 
			"    v.`emp_ids`,\r\n" + 
			"    v.`emp_name`,\r\n" + 
			"    v.`gate_id`,\r\n" + 
			"    v.`gate_passtype`,\r\n" + 
			"    v.`visit_status`,\r\n" + 
			"    v.`visit_type`,\r\n" + 
			"    v.`in_time`,\r\n" + 
			"    v.`visit_card_id`,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(vc.card_number)\r\n" + 
			"    FROM\r\n" + 
			"        m_visit_card vc\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(vc.card_id, v.ex_var3)\r\n" + 
			") AS visit_card_no,\r\n" + 
			"v.`take_mobile`,\r\n" + 
			"v.`meeting_discussion`,\r\n" + 
			"v.`upload_photo`,\r\n" + 
			"v.`visit_out_time`,\r\n" + 
			"v.`total_time_difference`,\r\n" + 
			"v.`security_id_out`,\r\n" + 
			"v.`visit_date_out`,\r\n" + 
			"v.`user_sign_image`,\r\n" + 
			"v.`del_status`,\r\n" + 
			"v.`is_used`,\r\n" + 
			"v.`ex_int1`,\r\n" + 
			"v.`ex_int2`,\r\n" + 
			"v.`ex_int3`,\r\n" + 
			"v.`ex_var1`,\r\n" + 
			"v.`ex_var2`,\r\n" + 
			"v.`ex_var3`," + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_in\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_in_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_out\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_out_name,\r\n" + 
			"    p.purpose_heading,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"                g.gate_name\r\n" + 
			"        FROM\r\n" + 
			"            m_gate g\r\n" + 
			"        WHERE\r\n" + 
			"            g.gate_id = v.gate_id\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS gate_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            GROUP_CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname,\r\n" + 
			"                ' '\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            FIND_IN_SET(e.emp_id, v.emp_ids)\r\n" + 
			"    ),\r\n" + 
			"    '-'\r\n" + 
			"    ) AS assign_emp_name\r\n" + 
			"FROM\r\n" + 
			"    t_gatepass_visitor v,\r\n" + 
			"    m_purpose p,\r\n" + 
			"    m_gate g,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        emp_id\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        del_status = 1 AND FIND_IN_SET(emp_info.emp_id,:empIds)\r\n" + 
			") AS emp\r\n" + 
			"WHERE\r\n" + 
			"    v.visit_date_in BETWEEN :fromDate AND :toDate AND v.gate_passtype IN(:gatepassType)"
			+ " AND v.`del_status` = 1 AND v.gate_id = g.gate_id AND v.purpose_id = p.purpose_id"
			+ " AND v.visit_status IN(:status) AND FIND_IN_SET(emp.emp_id, v.emp_ids)\r\n" + 
			"GROUP BY\r\n" + 
			"    v.gatepass_visitor_id ORDER BY v.visit_status", nativeQuery = true)
	List<VisitorGatepassDisplay> getVisitorGatepassListInDate(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("gatepassType") List<Integer> gatepassType,
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   


	@Query(value = " SELECT\r\n" + 
			"    v.`gatepass_visitor_id`,\r\n" + 
			"    v.`visit_date_in`,\r\n" + 
			"    v.`security_id_in`,\r\n" + 
			"    v.`person_name`,\r\n" + 
			"    v.`person_company`,\r\n" + 
			"    v.`person_photo`,\r\n" + 
			"    v.`mobile_no`,\r\n" + 
			"    v.`id_proof`,\r\n" + 
			"    v.`id_proof1`,\r\n" + 
			"    v.`other_photo`,\r\n" + 
			"    v.`purpose_id`,\r\n" + 
			"    v.`visit_purpose_text`,\r\n" + 
			"    v.`purpose_remark`,\r\n" + 
			"    v.`emp_ids`,\r\n" + 
			"    v.`emp_name`,\r\n" + 
			"    v.`gate_id`,\r\n" + 
			"    v.`gate_passtype`,\r\n" + 
			"    v.`visit_status`,\r\n" + 
			"    v.`visit_type`,\r\n" + 
			"    v.`in_time`,\r\n" + 
			"    v.`visit_card_id`,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(vc.card_number)\r\n" + 
			"    FROM\r\n" + 
			"        m_visit_card vc\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(vc.card_id, v.ex_var3)\r\n" + 
			") AS visit_card_no,\r\n" + 
			"v.`take_mobile`,\r\n" + 
			"v.`meeting_discussion`,\r\n" + 
			"v.`upload_photo`,\r\n" + 
			"v.`visit_out_time`,\r\n" + 
			"v.`total_time_difference`,\r\n" + 
			"v.`security_id_out`,\r\n" + 
			"v.`visit_date_out`,\r\n" + 
			"v.`user_sign_image`,\r\n" + 
			"v.`del_status`,\r\n" + 
			"v.`is_used`,\r\n" + 
			"v.`ex_int1`,\r\n" + 
			"v.`ex_int2`,\r\n" + 
			"v.`ex_int3`,\r\n" + 
			"v.`ex_var1`,\r\n" + 
			"v.`ex_var2`,\r\n" + 
			"v.`ex_var3`," + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_in\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_in_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_out\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_out_name,\r\n" + 
			"    p.purpose_heading,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"                g.gate_name\r\n" + 
			"        FROM\r\n" + 
			"            m_gate g\r\n" + 
			"        WHERE\r\n" + 
			"            g.gate_id = v.gate_id\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS gate_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            GROUP_CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname,\r\n" + 
			"                ' '\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            FIND_IN_SET(e.emp_id, v.emp_ids)\r\n" + 
			"    ),\r\n" + 
			"    '-'\r\n" + 
			"    ) AS assign_emp_name\r\n" + 
			"FROM\r\n" + 
			"    t_gatepass_visitor v,\r\n" + 
			"    m_purpose p,\r\n" + 
			"    m_gate g\r\n" + 
			"WHERE\r\n" + 
			"    v.visit_date_in BETWEEN :fromDate AND :toDate AND v.gate_passtype IN(:gatepassType) "
			+ "AND v.`del_status` = 1 AND v.gate_id = g.gate_id AND v.purpose_id = p.purpose_id "
			+ "AND v.visit_status IN(:status) ORDER BY v.visit_status", nativeQuery = true)
	List<VisitorGatepassDisplay> getVisitorGatepassListInDate(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("gatepassType") List<Integer> gatepassType,
			@Param("status") List<Integer> status);   

	
	
	
	@Query(value = "SELECT\r\n" + 
			"    v.`gatepass_visitor_id`,\r\n" + 
			"    v.`visit_date_in`,\r\n" + 
			"    v.`security_id_in`,\r\n" + 
			"    v.`person_name`,\r\n" + 
			"    v.`person_company`,\r\n" + 
			"    v.`person_photo`,\r\n" + 
			"    v.`mobile_no`,\r\n" + 
			"    v.`id_proof`,\r\n" + 
			"    v.`id_proof1`,\r\n" + 
			"    v.`other_photo`,\r\n" + 
			"    v.`purpose_id`,\r\n" + 
			"    v.`visit_purpose_text`,\r\n" + 
			"    v.`purpose_remark`,\r\n" + 
			"    v.`emp_ids`,\r\n" + 
			"    v.`emp_name`,\r\n" + 
			"    v.`gate_id`,\r\n" + 
			"    v.`gate_passtype`,\r\n" + 
			"    v.`visit_status`,\r\n" + 
			"    v.`visit_type`,\r\n" + 
			"    v.`in_time`,\r\n" + 
			"    v.`visit_card_id`,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(vc.card_number)\r\n" + 
			"    FROM\r\n" + 
			"        m_visit_card vc\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(vc.card_id, v.ex_var3)\r\n" + 
			") AS visit_card_no,\r\n" + 
			"v.`take_mobile`,\r\n" + 
			"v.`meeting_discussion`,\r\n" + 
			"v.`upload_photo`,\r\n" + 
			"v.`visit_out_time`,\r\n" + 
			"v.`total_time_difference`,\r\n" + 
			"v.`security_id_out`,\r\n" + 
			"v.`visit_date_out`,\r\n" + 
			"v.`user_sign_image`,\r\n" + 
			"v.`del_status`,\r\n" + 
			"v.`is_used`,\r\n" + 
			"v.`ex_int1`,\r\n" + 
			"v.`ex_int2`,\r\n" + 
			"v.`ex_int3`,\r\n" + 
			"v.`ex_var1`,\r\n" + 
			"v.`ex_var2`,\r\n" + 
			"v.`ex_var3`," + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_in\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_in_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_out\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_out_name,\r\n" + 
			"    p.purpose_heading,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"                g.gate_name\r\n" + 
			"        FROM\r\n" + 
			"            m_gate g\r\n" + 
			"        WHERE\r\n" + 
			"            g.gate_id = v.gate_id\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS gate_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            GROUP_CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            FIND_IN_SET(e.emp_id, v.emp_ids)\r\n" + 
			"    ),\r\n" + 
			"    '-'\r\n" + 
			"    ) AS empname\r\n" + 
			"FROM\r\n" + 
			"    t_gatepass_visitor v,\r\n" + 
			"    m_purpose p,\r\n" + 
			"    m_gate g,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        emp_id\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        del_status = 1 AND FIND_IN_SET(emp_info.emp_id,:empIds)\r\n" + 
			") AS emp\r\n" + 
			"WHERE\r\n" + 
			"     v.gate_passtype IN(:gatepassType) AND v.`del_status` = 1 AND v.gate_id = g.gate_id AND v.purpose_id = p.purpose_id AND v.visit_status IN(:status) AND FIND_IN_SET(emp.emp_id, v.emp_ids)\r\n" + 
			"GROUP BY\r\n" + 
			"    v.gatepass_visitor_id ORDER BY v.visit_status", nativeQuery = true)
	List<VisitorGatepassDisplay> getVisitorGatepassList(
			@Param("gatepassType") List<Integer> gatepassType,
			@Param("empIds") String empIds,
			@Param("status") List<Integer> status);   
	
	
	@Query(value = " SELECT\r\n" + 
			"    v.`gatepass_visitor_id`,\r\n" + 
			"    v.`visit_date_in`,\r\n" + 
			"    v.`security_id_in`,\r\n" + 
			"    v.`person_name`,\r\n" + 
			"    v.`person_company`,\r\n" + 
			"    v.`person_photo`,\r\n" + 
			"    v.`mobile_no`,\r\n" + 
			"    v.`id_proof`,\r\n" + 
			"    v.`id_proof1`,\r\n" + 
			"    v.`other_photo`,\r\n" + 
			"    v.`purpose_id`,\r\n" + 
			"    v.`visit_purpose_text`,\r\n" + 
			"    v.`purpose_remark`,\r\n" + 
			"    v.`emp_ids`,\r\n" + 
			"    v.`emp_name`,\r\n" + 
			"    v.`gate_id`,\r\n" + 
			"    v.`gate_passtype`,\r\n" + 
			"    v.`visit_status`,\r\n" + 
			"    v.`visit_type`,\r\n" + 
			"    v.`in_time`,\r\n" + 
			"    v.`visit_card_id`,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(vc.card_number)\r\n" + 
			"    FROM\r\n" + 
			"        m_visit_card vc\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(vc.card_id, v.ex_var3)\r\n" + 
			") AS visit_card_no,\r\n" + 
			"v.`take_mobile`,\r\n" + 
			"v.`meeting_discussion`,\r\n" + 
			"v.`upload_photo`,\r\n" + 
			"v.`visit_out_time`,\r\n" + 
			"v.`total_time_difference`,\r\n" + 
			"v.`security_id_out`,\r\n" + 
			"v.`visit_date_out`,\r\n" + 
			"v.`user_sign_image`,\r\n" + 
			"v.`del_status`,\r\n" + 
			"v.`is_used`,\r\n" + 
			"v.`ex_int1`,\r\n" + 
			"v.`ex_int2`,\r\n" + 
			"v.`ex_int3`,\r\n" + 
			"v.`ex_var1`,\r\n" + 
			"v.`ex_var2`,\r\n" + 
			"v.`ex_var3`," + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_in\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_in_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_out\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_out_name,\r\n" + 
			"    p.purpose_heading,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"                g.gate_name\r\n" + 
			"        FROM\r\n" + 
			"            m_gate g\r\n" + 
			"        WHERE\r\n" + 
			"            g.gate_id = v.gate_id\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS gate_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            GROUP_CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            FIND_IN_SET(e.emp_id, v.emp_ids)\r\n" + 
			"    ),\r\n" + 
			"    '-'\r\n" + 
			"    ) AS empname\r\n" + 
			"FROM\r\n" + 
			"    t_gatepass_visitor v,\r\n" + 
			"    m_purpose p,\r\n" + 
			"    m_gate g,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        emp_id\r\n" + 
			"    FROM\r\n" + 
			"        emp_info\r\n" + 
			"    WHERE\r\n" + 
			"        del_status = 1 AND FIND_IN_SET(emp_info.emp_id,:empIds)\r\n" + 
			") AS emp\r\n" + 
			"WHERE\r\n" + 
			"     v.gate_passtype IN(:gatepassType) AND v.`del_status` = 1 AND v.gate_id = g.gate_id AND v.purpose_id = p.purpose_id AND v.visit_status IN(:status) ORDER BY v.visit_status", nativeQuery = true)
	List<VisitorGatepassDisplay> getVisitorGatepassList(
			@Param("gatepassType") List<Integer> gatepassType,
			@Param("status") List<Integer> status);   
	
	
	
	@Query(value = " SELECT\r\n" + 
			"    v.`gatepass_visitor_id`,\r\n" + 
			"    v.`visit_date_in`,\r\n" + 
			"    v.`security_id_in`,\r\n" + 
			"    v.`person_name`,\r\n" + 
			"    v.`person_company`,\r\n" + 
			"    v.`person_photo`,\r\n" + 
			"    v.`mobile_no`,\r\n" + 
			"    v.`id_proof`,\r\n" + 
			"    v.`id_proof1`,\r\n" + 
			"    v.`other_photo`,\r\n" + 
			"    v.`purpose_id`,\r\n" + 
			"    v.`visit_purpose_text`,\r\n" + 
			"    v.`purpose_remark`,\r\n" + 
			"    v.`emp_ids`,\r\n" + 
			"    v.`emp_name`,\r\n" + 
			"    v.`gate_id`,\r\n" + 
			"    v.`gate_passtype`,\r\n" + 
			"    v.`visit_status`,\r\n" + 
			"    v.`visit_type`,\r\n" + 
			"    v.`in_time`,\r\n" + 
			"    v.`visit_card_id`,\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        GROUP_CONCAT(vc.card_number)\r\n" + 
			"    FROM\r\n" + 
			"        m_visit_card vc\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(vc.card_id, v.ex_var3)\r\n" + 
			") AS visit_card_no,\r\n" + 
			"v.`take_mobile`,\r\n" + 
			"v.`meeting_discussion`,\r\n" + 
			"v.`upload_photo`,\r\n" + 
			"v.`visit_out_time`,\r\n" + 
			"v.`total_time_difference`,\r\n" + 
			"v.`security_id_out`,\r\n" + 
			"v.`visit_date_out`,\r\n" + 
			"v.`user_sign_image`,\r\n" + 
			"v.`del_status`,\r\n" + 
			"v.`is_used`,\r\n" + 
			"v.`ex_int1`,\r\n" + 
			"v.`ex_int2`,\r\n" + 
			"v.`ex_int3`,\r\n" + 
			"v.`ex_var1`,\r\n" + 
			"v.`ex_var2`,\r\n" + 
			"v.`ex_var3`," + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_in\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_in_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname\r\n" + 
			"            ) AS NAME\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            e.emp_id = v.security_id_out\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS security_out_name,\r\n" + 
			"    p.purpose_heading,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"                g.gate_name\r\n" + 
			"        FROM\r\n" + 
			"            m_gate g\r\n" + 
			"        WHERE\r\n" + 
			"            g.gate_id = v.gate_id\r\n" + 
			"    ),\r\n" + 
			"    NULL\r\n" + 
			"    ) AS gate_name,\r\n" + 
			"    COALESCE(\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            GROUP_CONCAT(\r\n" + 
			"                e.emp_fname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_mname,\r\n" + 
			"                ' ',\r\n" + 
			"                e.emp_sname,\r\n" + 
			"                ' '\r\n" + 
			"            )\r\n" + 
			"        FROM\r\n" + 
			"            emp_info e\r\n" + 
			"        WHERE\r\n" + 
			"            FIND_IN_SET(e.emp_id, v.emp_ids)\r\n" + 
			"    ),\r\n" + 
			"    '-'\r\n" + 
			"    ) AS assign_emp_name\r\n" + 
			"FROM\r\n" + 
			"    t_gatepass_visitor v,\r\n" + 
			"    m_purpose p,\r\n" + 
			"    m_gate g\r\n" + 
			"WHERE\r\n" + 
			"    v.gate_passtype IN(:gatepassType) AND v.`del_status` = 1 AND v.gate_id = g.gate_id AND v.purpose_id = p.purpose_id AND v.visit_status IN(:status) AND FIND_IN_SET(:empId, v.emp_ids) AND v.visit_date_in BETWEEN :fromDate AND :toDate"  
			, nativeQuery = true)
	List<VisitorGatepassDisplay> getVisitorGatepassReport(
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate,
			@Param("gatepassType") List<Integer> gatepassType,
			@Param("empId") int empId,
			@Param("status") List<Integer> status);   
	 

	
	
}
