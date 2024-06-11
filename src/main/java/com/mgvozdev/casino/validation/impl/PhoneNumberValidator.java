package com.mgvozdev.casino.validation.impl;

import com.mgvozdev.casino.validation.PhoneNumberChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberChecker, String> {

    private static final String PHONE_NUMBER_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .map(phoneNumber -> phoneNumber.matches(PHONE_NUMBER_PATTERN))
                .orElse(false);
    }
}
