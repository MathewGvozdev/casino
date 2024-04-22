package com.mgvozdev.casino.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlayerFilter(LocalDateTime openedAt,
                           LocalDateTime closedAt,
                           BigDecimal buyIn,
                           Integer avgBet) {
}
