package com.mgvozdev.casino.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record PlayerCreateEditDto(String documentNumber,
                                  LocalDateTime openedAt,
                                  BigDecimal buyIn,
                                  LocalDateTime closedAt,
                                  Integer avgBet,
                                  Set<ChipSetDto> chips) {
}
