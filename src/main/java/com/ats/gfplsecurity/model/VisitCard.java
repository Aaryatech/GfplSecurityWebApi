package com.ats.gfplsecurity.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "m_visit_card")
public class VisitCard implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cardId;

	private String cardNumber;
	private String cardDesc;
	private int delStatus;
	private int isActive;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;

}
