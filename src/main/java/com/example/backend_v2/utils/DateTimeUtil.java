package com.example.backend_v2.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeUtil {
	public static LocalDateTime toZone(final LocalDateTime time, final ZoneId fromZone, final ZoneId toZone) {
		final ZonedDateTime zonedTime = time.atZone(fromZone);
		final ZonedDateTime converted = zonedTime.withZoneSameInstant(toZone);
		return converted.toLocalDateTime();
	}

	public static LocalDateTime toZone(final LocalDateTime time, final ZoneId toZone) {
		return DateTimeUtil.toZone(time, ZoneId.systemDefault(), toZone);
	}

	public static LocalDateTime toUtc(final LocalDateTime time, final ZoneId fromZone) {
		return DateTimeUtil.toZone(time, fromZone, ZoneOffset.UTC);
	}

	public static LocalDateTime toUtc(final LocalDateTime time) {
		return DateTimeUtil.toUtc(time, ZoneId.systemDefault());
	}
}