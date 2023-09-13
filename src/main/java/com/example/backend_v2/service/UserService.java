package com.example.backend_v2.service;

import com.example.backend_v2.model.dto.UserDTO;

import java.io.IOException;

public interface UserService {
	UserDTO getCurrentUser();

	UserDTO updateUser(UserDTO userDTO) throws IOException;
}