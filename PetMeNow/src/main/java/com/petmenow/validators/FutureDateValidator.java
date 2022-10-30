package com.petmenow.validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.petmenow.utilities.DateTimeUtilities;

public class FutureDateValidator implements ConstraintValidator<FutureDate, Long> {

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		LocalDate startDate = DateTimeUtilities.convertToLocalDate(new Date(value));
		LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

		return startDate.isAfter(currentDate);
	}

}
