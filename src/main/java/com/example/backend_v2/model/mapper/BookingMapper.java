package com.example.backend_v2.model.mapper;

import com.example.backend_v2.model.dto.BookingDTO;
import com.example.backend_v2.model.entity.Booking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

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

	public Booking mapToBooking(BookingDTO bookingDTO) {
		return Booking.builder()
				.id(bookingDTO.getId())
				.startDate(bookingDTO.getStartDate())
				.endDate(bookingDTO.getEndDate())
				.numNights(bookingDTO.getNumNights())
				.numGuests(bookingDTO.getNumGuests())
				.extrasPrice(bookingDTO.getExtrasPrice())
				.totalPrice(bookingDTO.getTotalPrice())
				.bookingStatus(bookingDTO.getBookingStatus())
				.hasBreakfast(bookingDTO.getHasBreakfast())
				.isPaid(bookingDTO.getIsPaid())
				.observations(bookingDTO.getObservations())
				.createdAt(bookingDTO.getCreatedAt())
				.build();
	}

	public void setNonNullFieldsFromBookingDTO(Object object, Object objectDTO) throws IllegalAccessException {
		Field[] fields = object.getClass().getDeclaredFields();
		Field[] dtoFields = objectDTO.getClass().getDeclaredFields();

		for (int i = 0; i < dtoFields.length; i++) {
			dtoFields[i].setAccessible(true);
			Object value = dtoFields[i].get(objectDTO);

			if (value != null) {
				fields[i].setAccessible(true);
				fields[i].set(object, value);
			}
		}
	}
}