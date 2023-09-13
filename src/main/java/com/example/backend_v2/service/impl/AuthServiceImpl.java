package com.example.backend_v2.service.impl;

import com.example.backend_v2.model.dto.LoginDTO;
import com.example.backend_v2.model.dto.RegisterDTO;
import com.example.backend_v2.model.entity.User;
import com.example.backend_v2.model.mapper.UserMapper;
import com.example.backend_v2.repo.UserRepo;
import com.example.backend_v2.service.AuthService;
import com.example.backend_v2.utils.ImageUtils;
import com.example.backend_v2.utils.JwtUtil;
import javax.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthenticationManager authenticationManager;
	private final UserRepo userRepo;
	private final JwtUtil jwtUtil;
	private final UserMapper userMapper;

	@Override
	public String doLogin(LoginDTO loginDTO) {
		// Do authenticate
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
		);
		return jwtUtil.generateTokenWithName(authentication.getPrincipal().toString());
	}

	@Override
	public void doRegister(RegisterDTO registerDTO) throws IOException {
		// Check email is exists or not
		Optional<User> userOptional = userRepo.findByEmail(registerDTO.getEmail());

		if (userOptional.isPresent()) {
			throw new EntityExistsException("Username or email or idCard is already existed");
		}

//		// Save avatar into fileSystem
//		if(registerDTO.getAvatar() != null) {
//			String imageName = ImageUtils.uploadImageToFileSystem(registerDTO.getAvatar(), imageCabinPath, registerDTO.getFullName());
//			registerDTO.setAvatarUrl(imageCabinUrl + imageName);
//		}

		userRepo.save(userMapper.mapfromRegisterDTOtoUser(registerDTO));
	}
}