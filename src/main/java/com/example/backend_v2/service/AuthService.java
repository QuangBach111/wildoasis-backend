package com.example.backend_v2.service;

import com.example.backend_v2.model.dto.LoginDTO;
import com.example.backend_v2.model.dto.RegisterDTO;

import java.io.IOException;

public interface AuthService {
	String doLogin(LoginDTO loginDTO);
	void doRegister(RegisterDTO registerDTO) throws IOException;
}