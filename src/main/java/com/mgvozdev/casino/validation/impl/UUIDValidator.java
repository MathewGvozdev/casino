package com.mgvozdev.casino.validation.impl;

import com.mgvozdev.casino.validation.UUIDChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;
import java.util.regex.Pattern;

public class UUIDValidator implements ConstraintValidator<UUIDChecker, UUID> {

    private static final String UUID_PATTERN = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        var uuid_regex = Pattern.compile(UUID_PATTERN);
        return uuid_regex.matcher(value.toString()).matches();
    }
}
