package com.mgvozdev.casino.validation.impl;

import com.mgvozdev.casino.validation.UuidChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;
import java.util.UUID;

public class UuidValidator implements ConstraintValidator<UuidChecker, UUID> {

    private static final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .map(uuid -> uuid.toString().matches(UUID_PATTERN))
                .orElse(false);
    }
}
