package com.example.backend.service;

import com.example.backend.model.dto.BookingDTO;
import org.springframework.data.domain.Page;

public interface BookingService {
	Page<BookingDTO> getAllBookingsPagination(int pageNo, int pageSize);
}