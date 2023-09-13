package com.example.backend_v2.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterDTO {
	private String email;
	private String fullName;
	private String password;
	private String passwordConfirm;
	private MultipartFile avatar;
	private String avatarUrl;
}