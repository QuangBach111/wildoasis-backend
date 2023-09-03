package com.example.backend.controller;

import com.example.backend.model.dto.BookingDTO;
import com.example.backend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {
	private BookingService bookingService;
	@GetMapping
	public ResponseEntity<Page<BookingDTO>> getBookingPagination(
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize
	) {
		int pageSizeDefault = 10;

		pageNo = pageNo == null || pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == null || pageSize == 0 ? pageSizeDefault : pageSize;

		return ResponseEntity.ok(bookingService.getAllBookingsPagination(pageNo, pageSize));
	}
}