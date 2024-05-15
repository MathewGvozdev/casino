package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.Chip;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public record ChipSetDto(Chip chip,
                         @PositiveOrZero Integer amount,
                         @PositiveOrZero BigDecimal total) {
}
