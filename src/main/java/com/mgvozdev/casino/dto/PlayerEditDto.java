package com.mgvozdev.casino.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlayerEditDto(BigDecimal buyIn,
                            LocalDateTime closedAt,
                            Integer avgBet) {
}
