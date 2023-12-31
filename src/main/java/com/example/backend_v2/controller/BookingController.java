package com.example.backend_v2.controller;

import com.example.backend_v2.model.dto.BookingDTO;
import com.example.backend_v2.service.BookingService;
import com.example.backend_v2.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {
	private BookingService bookingService;

	@GetMapping
	public ResponseEntity<Page<BookingDTO>> getBookingPagination(
			@RequestParam(name="pageNo", required=false) Integer pageNo,
			@RequestParam(name="pageSize", required=false) Integer pageSize,
			@RequestParam(name="filter_field", required=false) String filterField,
			@RequestParam(name="filter_value", required=false) String filterValue,
			@RequestParam(name="sortBy_field", required=false) String sortByField,
			@RequestParam(name="sortBy_direction", required=false) String sortByDirection
	) {
		final int PAGE_SIZE = 5;

		pageNo = pageNo == null || pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == null || pageSize == 0 ? PAGE_SIZE : pageSize;

		return ResponseEntity.ok(bookingService.getAllBookingsPagination(pageNo, pageSize, filterField, filterValue, sortByField, sortByDirection));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingDTO> getBookingById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(bookingService.getBookingById(id));
	}

	@GetMapping("/date")
	public ResponseEntity<List<BookingDTO>> getBookingsByCreatedDate(
			@RequestParam("date") String date,
			@RequestParam("currentDate") String currentDate
	) {
		LocalDate parsedDate = DateUtil.parseISODate(date.replace("'", ""));
		LocalDate parsedCurrentDate = DateUtil.parseISODate(currentDate.replace("'", ""));
		return ResponseEntity.ok(bookingService.getBookingsAfterDate(parsedDate, parsedCurrentDate));
	}

	@GetMapping("/stay")
	public ResponseEntity<List<BookingDTO>> getBookingsByStartDate(
			@RequestParam(value = "date") String date,
			@RequestParam(value = "currentDate") String currentDate
	) {
		LocalDateTime parsedDate = DateUtil.parseISODateTime(date.replace("'", ""));
		LocalDateTime parsedCurrentDate = DateUtil.parseISODateTime(currentDate.replace("'", ""));
		List<BookingDTO> bookingDTOS = bookingService.getStaysAfterDate(parsedDate, parsedCurrentDate);
		return ResponseEntity.ok(bookingDTOS);
	}

	@CrossOrigin
	@PutMapping
	public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO) {
		try {
			return ResponseEntity.ok(bookingService.updateBooking(bookingDTO));
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<?> deleteBooking(@PathVariable("bookingId") Long bookingId) {
		bookingService.deleteBooking(bookingId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}