package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.Chip;

import java.math.BigDecimal;

public record ChipSetDto(Chip chip,
                         Integer amount,
                         BigDecimal total) {
}
