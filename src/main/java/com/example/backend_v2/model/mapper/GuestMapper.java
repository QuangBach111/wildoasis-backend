package com.example.backend_v2.model.mapper;

import com.example.backend_v2.model.dto.GuestDTO;
import com.example.backend_v2.model.entity.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper {
	public GuestDTO mapToGuestDTO(Guest guest) {
		return GuestDTO.builder()
				.id(guest.getId())
				.fullName(guest.getFullName())
				.email(guest.getEmail())
				.nationalID(guest.getNationalID())
				.nationality(guest.getNationality())
				.countryFlag(guest.getCountryFlag())
				.createdAt(guest.getCreatedAt())
				.build();
	}
}