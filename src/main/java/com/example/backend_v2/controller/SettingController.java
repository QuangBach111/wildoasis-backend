package com.example.backend_v2.controller;

import com.example.backend_v2.model.entity.Setting;
import com.example.backend_v2.service.SettingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/settings")
@AllArgsConstructor
public class SettingController {
	final SettingService settingService;
	@GetMapping
	public ResponseEntity<Setting> getSettings(){
			return ResponseEntity.ok(settingService.getSettings());
	}

	@PutMapping
	public ResponseEntity<Long> updateSetting(@RequestBody Setting setting){
		settingService.updateSettings(setting);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}