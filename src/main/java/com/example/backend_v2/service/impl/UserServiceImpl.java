package com.example.backend_v2.service.impl;

import com.example.backend_v2.model.dto.UserDTO;
import com.example.backend_v2.model.entity.User;
import com.example.backend_v2.model.mapper.UserMapper;
import com.example.backend_v2.repo.UserRepo;

import javax.persistence.EntityNotFoundException;

import com.example.backend_v2.service.UserService;
import com.example.backend_v2.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	private final UserRepo userRepo;

	@Value("${images.avatar.path}")
	private String imageCabinPath;

	@Value("${images.avatar.url}")
	private String imageCabinUrl;
	@Override
	public UserDTO getCurrentUser() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User with email: %s is not found!", email)));
		return userMapper.mapToUserDTO(user);
	}

	@Transactional
	@Override
	public UserDTO updateUser(UserDTO userDTO) throws IOException {
		User user = userRepo.findByEmail(userDTO.getEmail())
				.orElseThrow(() -> new EntityNotFoundException("Something wrong with email: " + userDTO.getEmail()));

		if (userDTO.getAvatar() != null) {
			String imageName = ImageUtils.uploadImageToFileSystem(userDTO.getAvatar(), imageCabinPath, String.valueOf(user.getId()));
			user.setAvatarUrl(imageCabinUrl + imageName);
		}
		user.setFullName(userDTO.getFullName());

		return userMapper.mapToUserDTO(user);
	}
}