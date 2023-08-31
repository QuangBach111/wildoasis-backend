package com.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "settings")
@DynamicUpdate
@Data
@NoArgsConstructor
public class Setting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "min_booking_length")
	private Integer minBookingLength;

	@Column(name = "max_booking_length")
	private Integer maxBookingLength;

	@Column(name = "max_guests_per_booking")
	private Integer maxGuestsPerBooking;

	@Column(name = "breakfast_price")
	private Double breakfastPrice;
}