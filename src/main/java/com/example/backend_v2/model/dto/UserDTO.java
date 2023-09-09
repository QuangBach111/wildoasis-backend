package com.example.backend_v2.model.dto;

import com.example.backend_v2.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String email;
	private String fullName;
	private MultipartFile avatar;
	private String avatarUrl;
	private Role role;
}