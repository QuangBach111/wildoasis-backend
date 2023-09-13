package com.example.backend_v2.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateUtil {
	public static LocalDateTime parseISODateTime(String isoDateTime) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				.appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
				.optionalStart()
				.appendOffset("+HH:MM", "Z")
				.toFormatter();

		ZonedDateTime zonedDateTime = ZonedDateTime.parse(isoDateTime, formatter);
		return zonedDateTime.toLocalDateTime();
	}
	public static LocalDate parseISODate(String isoDateTime) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				.appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
				.optionalStart()
				.appendOffset("+HH:MM", "Z")
				.toFormatter();

		ZonedDateTime zonedDateTime = ZonedDateTime.parse(isoDateTime, formatter);
		return zonedDateTime.toLocalDate();
	}
}