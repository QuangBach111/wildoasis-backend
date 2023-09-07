package com.example.backend_v2.service.impl;

import com.example.backend_v2.model.dto.UserDTO;
import com.example.backend_v2.model.entity.User;
import com.example.backend_v2.model.mapper.UserMapper;
import com.example.backend_v2.repo.UserRepo;
import javax.persistence.EntityNotFoundException;

import com.example.backend_v2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	private final UserRepo userRepo;
	@Override
	public UserDTO getCurrentUser() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User with email: %s is not found!", email)));
		return userMapper.mapToUserDTO(user);
	}
}