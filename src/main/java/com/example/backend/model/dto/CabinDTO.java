package com.example.backend.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CabinDTO {
	private Long id;

	private String name;

	private Integer maxCapacity;

	private Double regularPrice;

	private Integer discount;

	private String imageUrl;

	private MultipartFile image;

	private String description;

	private LocalDate createdAt;
}