package com.example.backend_v2.service.impl;

import com.example.backend_v2.model.entity.Setting;
import com.example.backend_v2.repo.SettingRepo;
import javax.persistence.EntityNotFoundException;

import com.example.backend_v2.service.SettingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SettingServiceImpl implements SettingService {
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