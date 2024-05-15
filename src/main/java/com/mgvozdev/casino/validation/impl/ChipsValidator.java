package com.mgvozdev.casino.validation.impl;

import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.validation.ChipsChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Set;

public class ChipsValidator implements ConstraintValidator<ChipsChecker, Set<ChipSetDto>> {

    @Override
    public boolean isValid(Set<ChipSetDto> value, ConstraintValidatorContext context) {
        return value != null
               && !value.isEmpty()
               && value.stream().allMatch(this::isValidChipSetDto);
    }

    private boolean isValidChipSetDto(ChipSetDto chipSetDto) {
        return chipSetDto.chip() != null
               && chipSetDto.total() != null
               && chipSetDto.total().compareTo(BigDecimal.ZERO) >= 0;
    }
}
