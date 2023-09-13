package com.example.backend_v2.model.mapper;

import com.example.backend_v2.model.dto.RegisterDTO;
import com.example.backend_v2.model.dto.UserDTO;
import com.example.backend_v2.model.entity.User;
import com.example.backend_v2.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
	private final PasswordEncoder passwordEncoder;
	public User mapfromRegisterDTOtoUser(RegisterDTO registerDTO) {
		return User.builder()
				.email(registerDTO.getEmail())
				.fullName(registerDTO.getFullName())
				.password(passwordEncoder.encode(registerDTO.getPassword()))
				.avatarUrl(registerDTO.getAvatarUrl())
				.role(Role.ROLE_USER)
				.build();
	}

	public UserDTO mapToUserDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.fullName(user.getFullName())
				.email(user.getEmail())
				.avatarUrl(user.getAvatarUrl())
				.role(user.getRole())
				.build();
	}
}