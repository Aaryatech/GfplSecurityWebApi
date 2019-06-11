package com.ats.gfplsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_settings")
public class Settings {
	
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private int settingId;
	private String settingKey;
	private String settingValue;
	

}
