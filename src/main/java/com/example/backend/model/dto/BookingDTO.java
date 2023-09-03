package com.example.backend.model.dto;

import com.example.backend.utils.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookingDTO {
	private Long id;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Integer numNights;

	private Integer numGuests;

	private Double extrasPrice;

	private Double totalPrice;

	private BookingStatus bookingStatus;

	private Boolean hasBreakfast;

	@Column(name="is_paid")
	private Boolean isPaid;

	private String observations;

	private LocalDate createdAt;

	private CabinDTO cabin;

	private GuestDTO guest;
}