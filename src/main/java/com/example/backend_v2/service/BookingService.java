package com.example.backend_v2.service;

import com.example.backend_v2.model.dto.BookingDTO;
import org.springframework.data.domain.Page;

public interface BookingService {
	Page<BookingDTO> getAllBookingsPagination(int pageNo, int pageSize, String filterField, String filterValue, String sortByField, String sortByDirection);

	BookingDTO getBookingById(Long id);

	BookingDTO updateBooking(BookingDTO bookingDTO) throws IllegalAccessException;

	BookingDTO updateBookingWithBookingStatusAndIsPaid(BookingDTO bookingDTO);

	void deleteBooking(Long bookingId);
}