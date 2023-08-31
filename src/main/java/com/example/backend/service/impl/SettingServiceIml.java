package com.example.backend.service.impl;

import com.example.backend.model.entity.Setting;
import com.example.backend.repo.SettingRepo;
import com.example.backend.service.SettingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SettingServiceIml implements SettingService {
	final SettingRepo settingRepo;

	@Override
	@Transactional(readOnly = true)
	public Setting getSettings() {
		return settingRepo.findAllSettings()
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException("Setting not be stored!"));
	}

	@Override
	public void updateSettings(Setting setting) {
		settingRepo.save(setting);
	}
}