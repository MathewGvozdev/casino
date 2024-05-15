package com.mgvozdev.casino.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlayerEditDto(@Positive BigDecimal buyIn,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime closedAt,
                            @PositiveOrZero Integer avgBet) {
}
