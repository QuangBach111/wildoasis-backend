package com.example.backend.service.impl;

import com.example.backend.model.dto.BookingDTO;
import com.example.backend.model.entity.Booking;
import com.example.backend.model.mapper.BookingMapper;
import com.example.backend.repo.BookingRepo;
import com.example.backend.service.BookingService;
import com.example.backend.utils.BookingStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
	private BookingRepo bookingRepo;
	private BookingMapper bookingMapper;

	@Override
	public Page<BookingDTO> getAllBookingsPagination(int pageNo, int pageSize, String filterField, String filterValue, String sortByField, String sortByDirection) {
		// Default: sort by startDate - asc
		PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "startDate"));

		// SORT BY
		if(sortByField != null && sortByDirection != null) {
			pageRequest = pageRequest.withSort(Sort.Direction.valueOf(sortByDirection.toUpperCase()), sortByField);
		}

		// FILTER
		if(filterField != null && filterValue != null){
			return bookingRepo.getAllByBookingStatus(BookingStatus.valueOf(filterValue.toUpperCase()), pageRequest)
					.map(booking -> bookingMapper.mapToBookingDTO(booking));
		}

		return bookingRepo.getAll(pageRequest)
				.map(booking -> bookingMapper.mapToBookingDTO(booking));
	}

	@Override
	public BookingDTO getBookingById(Long id) {
		Booking booking = bookingRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Booking with id: %d is not found!", id)));

		return bookingMapper.mapToBookingDTO(booking);
	}
}