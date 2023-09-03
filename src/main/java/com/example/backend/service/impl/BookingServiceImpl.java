package com.example.backend.service.impl;

import com.example.backend.model.dto.BookingDTO;
import com.example.backend.model.entity.Booking;
import com.example.backend.model.mapper.BookingMapper;
import com.example.backend.repo.BookingRepo;
import com.example.backend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
	private BookingRepo bookingRepo;
	private BookingMapper bookingMapper;

	@Override
	public Page<BookingDTO> getAllBookingsPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return  bookingRepo.getAll(pageable)
				.map(booking -> bookingMapper.mapToBookingDTO(booking));
	}
}