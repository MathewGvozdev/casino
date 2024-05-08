package com.mgvozdev.casino.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PlayerCreateDto(@Size(min = 8, max = 32) String documentNumber,
                              @Positive BigDecimal buyIn) {
}
