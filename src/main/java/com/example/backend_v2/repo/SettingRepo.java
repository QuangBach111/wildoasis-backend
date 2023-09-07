package com.example.backend_v2.repo;

import com.example.backend_v2.model.entity.Setting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface SettingRepo extends CrudRepository<Setting, Long> {
	@Query("SELECT s FROM Setting s")
	Stream<Setting> findAllSettings();
}