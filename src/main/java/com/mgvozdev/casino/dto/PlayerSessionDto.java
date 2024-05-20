package com.mgvozdev.casino.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlayerSessionDto(LocalDateTime openedAt,
                               LocalDateTime closedAt,
                               BigDecimal buyIn,
                               BigDecimal cashOut) {
}
