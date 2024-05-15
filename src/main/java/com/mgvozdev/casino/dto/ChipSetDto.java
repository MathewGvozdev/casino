package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.Chip;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ChipSetDto(Chip chip,
                         @PositiveOrZero Integer amount,
                         @PositiveOrZero BigDecimal total) {
}
