package com.example.backend_v2.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="guests")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="full_name", length=200)
	private String fullName;

	@Column(length=200, unique=true)
	private String email;

	@Column(name="national_id", length=100, unique=true)
	private String nationalID;

	@Column(length=100)
	private String nationality;

	@Column(name="country_flag", columnDefinition="TEXT")
	private String countryFlag;

	@CreationTimestamp
	@Column(name="created_at")
	private LocalDate createdAt;

	@OneToMany(mappedBy="guest")
	private List<Booking> bookings;
}