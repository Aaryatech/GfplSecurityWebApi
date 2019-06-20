package com.ats.gfplsecurity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.gfplsecurity.model.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {

	Settings findBySettingId(int settingId);
	
	@Transactional
	@Modifying
	@Query("update Settings set setting_value=:value  WHERE setting_id=:settingId")
	int updateValue(@Param("settingId") int settingId,@Param("value") String value);
	
	List<Settings> findAll();
	
	Settings findBySettingKey(String key);
	

}