package com.example.backend_v2.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private String email;
	private String password;
}