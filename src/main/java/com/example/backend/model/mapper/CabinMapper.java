package com.example.backend.model.mapper;

import com.example.backend.model.dto.CabinDTO;
import com.example.backend.model.entity.Cabin;
import org.springframework.stereotype.Component;

@Component
public class CabinMapper {
	public CabinDTO mapToCabinDTO(Cabin cabin){
		return CabinDTO.builder()
				.id(cabin.getId())
				.name(cabin.getName())
				.maxCapacity(cabin.getMaxCapacity())
				.discount(cabin.getDiscount())
				.imageUrl(cabin.getImageUrl())
				.description(cabin.getDescription())
				.createdAt(cabin.getCreatedAt())
				.regularPrice(cabin.getRegularPrice())
				.build();
	}

	public Cabin mapToCabin(CabinDTO cabinDTO){
		return Cabin.builder()
				.id(cabinDTO.getId())
				.name(cabinDTO.getName())
				.maxCapacity(cabinDTO.getMaxCapacity())
				.discount(cabinDTO.getDiscount())
				.imageUrl(cabinDTO.getImageUrl())
				.description(cabinDTO.getDescription())
				.createdAt(cabinDTO.getCreatedAt())
				.build();
	}
}