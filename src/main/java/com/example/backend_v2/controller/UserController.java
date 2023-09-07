package com.example.backend_v2.controller;

import com.example.backend_v2.model.dto.UserDTO;
import com.example.backend_v2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	@GetMapping("/currentUser")
	public ResponseEntity<UserDTO> getCurrentUser()  {
		return ResponseEntity.ok(userService.getCurrentUser());
	}
}