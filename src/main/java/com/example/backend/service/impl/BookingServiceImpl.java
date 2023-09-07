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
import org.springframework.transaction.annotation.Transactional;

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
		if (sortByField != null && sortByDirection != null) {
			pageRequest = pageRequest.withSort(Sort.Direction.valueOf(sortByDirection.toUpperCase()), sortByField);
		}

		// FILTER BY BOOKING STATUS
		if (filterField != null && filterValue != null) {
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

	@Override
	@Transactional
	public BookingDTO updateBooking(BookingDTO bookingDTO) throws IllegalAccessException {
		Booking booking = bookingRepo.findById(bookingDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException(String.format("Booking with id: %d not found!", bookingDTO.getId())));

		bookingMapper.setNonNullFieldsFromBookingDTO(booking, bookingDTO);




		return bookingMapper.mapToBookingDTO((booking));
	}

	@Override
	@Transactional
	public BookingDTO updateBookingWithBookingStatusAndIsPaid(BookingDTO bookingDTO) {
		Booking booking = bookingRepo.findById(bookingDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException(String.format("Booking with id: %d not found!", bookingDTO.getId())));

		if (bookingDTO.getIsPaid() != null) {
			booking.setIsPaid(bookingDTO.getIsPaid());
		}
		if (bookingDTO.getBookingStatus() != null) {
			booking.setBookingStatus(bookingDTO.getBookingStatus());
		}

		return bookingMapper.mapToBookingDTO(booking);
	}

	@Override
	public void deleteBooking(Long bookingId) {
		bookingRepo.deleteById(bookingId);
	}
}