package com.example.backend_v2.model.dto;

import com.example.backend_v2.utils.BookingStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("status")
	private BookingStatus bookingStatus;

	private Boolean hasBreakfast;

	private Boolean isPaid;

	private String observations;

	private LocalDate createdAt;

	private CabinDTO cabin;

	private GuestDTO guest;
}