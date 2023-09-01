package com.example.backend.repo;

import com.example.backend.model.entity.Booking;
import com.example.backend.model.entity.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepo extends JpaRepository<Booking, Long> {
//	@Query("""
//					DELETE FROM Booking b WHERE b.cabin = ?1
//			""")
//	void deleteByCabin(Cabin cabin);
}