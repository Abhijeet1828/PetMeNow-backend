package com.petmenow.utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateTimeUtilities {

	private DateTimeUtilities() {
		throw new IllegalStateException("DateTimeUtilities class cannot be instantiated");
	}

	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
