package com.example.backend.controller;

import com.example.backend.model.dto.BookingDTO;
import com.example.backend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {
	private BookingService bookingService;
	@GetMapping
	public ResponseEntity<Page<BookingDTO>> getBookingPagination(
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "filter_field", required = false) String  filterField,
			@RequestParam(name = "filter_value", required = false) String  filterValue,
			@RequestParam(name = "sortBy_field", required = false) String sortByField,
			@RequestParam(name = "sortBy_direction", required = false) String sortByDirection
	) {
		int pageSizeDefault = 10;

		pageNo = pageNo == null || pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == null || pageSize == 0 ? pageSizeDefault : pageSize;

		return ResponseEntity.ok(bookingService.getAllBookingsPagination(pageNo, pageSize, filterField,filterValue,sortByField, sortByDirection));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingDTO> getBookingById(@PathVariable("id") Long id) {
			return ResponseEntity.ok(bookingService.getBookingById(id));
	}
}