package com.example.backend.service;

import com.example.backend.model.dto.BookingDTO;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface BookingService {
	Page<BookingDTO> getAllBookingsPagination(int pageNo, int pageSize,String filterField, String filterValue, String sortByField, String sortByDirection);

	BookingDTO getBookingById(Long id);
}