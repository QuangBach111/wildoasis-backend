package com.example.backend_v2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestDTO {
	private Long id;

	private String fullName;

	private String email;

	private String nationalID;

	private String nationality;

	private String countryFlag;

	private LocalDate createdAt;
}