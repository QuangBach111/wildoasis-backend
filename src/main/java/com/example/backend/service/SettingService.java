package com.example.backend.service;

import com.example.backend.model.entity.Setting;

public interface SettingService {
	Setting getSettings();

	void updateSettings(Setting setting);
}