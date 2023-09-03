package com.example.backend.model.entity;

import com.example.backend.utils.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="bookings")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="start_date")
	private LocalDateTime startDate;

	@Column(name="end_date")
	private LocalDateTime endDate;

	@Column(name="num_nights")
	private Integer numNights;

	@Column(name="num_guests")
	private Integer numGuests;

	@Column(name="extras_price")
	private Double extrasPrice;

	@Column(name="total_price")
	private Double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name="booking_status")
	private BookingStatus bookingStatus;

	@Column(name="has_breakfast")
	private Boolean hasBreakfast;

	@Column(name="is_paid")
	private Boolean isPaid;

	private String observations;

	@CreationTimestamp
	@Column(name="created_at")
	private LocalDate createdAt;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="cabin_id")
	private Cabin cabin;

	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="guest_id")
	private Guest guest;
}