package com.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cabins")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cabin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false, unique = true)
	private String name;

	@Column(name = "max_capacity")
	private Integer maxCapacity;

	@Column(name = "regular_price")
	private Double regularPrice;

	private Integer discount;

	private String imageUrl;

	@Column(columnDefinition = "TEXT")
	private String description;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDate createdAt;

	@OneToMany(mappedBy = "cabin", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookings;

}