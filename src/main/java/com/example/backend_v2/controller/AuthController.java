package com.example.backend_v2.controller;

import com.example.backend_v2.model.dto.LoginDTO;
import com.example.backend_v2.model.dto.RegisterDTO;
import com.example.backend_v2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<String> doLogin(@RequestBody LoginDTO loginDTO) {
		String token = authService.doLogin(loginDTO);
		return ResponseEntity.ok(token);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<?> doRegister(@RequestBody RegisterDTO registerDTO) throws IOException {
		authService.doRegister(registerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/logout")
	public ResponseEntity<?> doLogout() {
		return ResponseEntity.ok().build();
	}
}