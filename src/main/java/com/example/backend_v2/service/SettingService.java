package com.example.backend_v2.service;

import com.example.backend_v2.model.entity.Setting;

public interface SettingService {
	Setting getSettings();

	void updateSettings(Setting setting);
}