package com.example.backend.repo;

import com.example.backend.model.dto.BookingDTO;
import com.example.backend.model.entity.Booking;
import com.example.backend.model.entity.Cabin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepo extends JpaRepository<Booking, Long> {
	@Query("""
					SELECT b FROM Booking b
					LEFT JOIN b.guest
					LEFT JOIN b.cabin
			""")
	Page<Booking> getAll(Pageable pageable);
}