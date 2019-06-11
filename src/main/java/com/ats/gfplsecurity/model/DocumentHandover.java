package com.ats.gfplsecurity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_document_handover")
public class DocumentHandover implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int documentHandoverId;

	private int gatepassInwardId;
	private String handOverDate;
	private int fromUserId;
	private int toUserId;
	private String fromUserName;
	private String toUserName;
	private int status;
	private int delStatus;
	private int fromDepatrmentId;
	private String fromDepatrmentName;
	private int toDeptId;
	private String toDeptName;
	private int exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;

}
