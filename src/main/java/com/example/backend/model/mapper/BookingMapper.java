package com.example.backend.model.mapper;

import com.example.backend.model.dto.BookingDTO;
import com.example.backend.model.entity.Booking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookingMapper {
	private CabinMapper cabinMapper;
	private GuestMapper guestMapper;

	public BookingDTO mapToBookingDTO(Booking booking) {
		return BookingDTO.builder()
				.id(booking.getId())
				.startDate(booking.getStartDate())
				.endDate(booking.getEndDate())
				.numNights(booking.getNumNights())
				.numGuests(booking.getNumGuests())
				.extrasPrice(booking.getExtrasPrice())
				.totalPrice(booking.getTotalPrice())
				.bookingStatus(booking.getBookingStatus())
				.hasBreakfast(booking.getHasBreakfast())
				.isPaid(booking.getIsPaid())
				.observations(booking.getObservations())
				.createdAt(booking.getCreatedAt())
				.cabin(cabinMapper.mapToCabinDTO(booking.getCabin()))
				.guest(guestMapper.mapToGuestDTO(booking.getGuest()))
				.build();
	}
}