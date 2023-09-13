package com.example.backend_v2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtResponse {
	private String accessToken;
	private String refreshToken;
}