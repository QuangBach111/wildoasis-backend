package com.example.backend_v2.repo;

import com.example.backend_v2.model.entity.Booking;
import com.example.backend_v2.utils.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepo extends JpaRepository<Booking, Long> {
	@Query("SELECT b FROM Booking b LEFT JOIN b.guest	LEFT JOIN b.cabin")
	Page<Booking> getAll(Pageable pageable);

	@Query("SELECT b FROM Booking b LEFT JOIN b.guest LEFT JOIN b.cabin WHERE b.bookingStatus = ?1")
	Page<Booking> getAllByBookingStatus(BookingStatus bookingStatus, Pageable pageable);
}