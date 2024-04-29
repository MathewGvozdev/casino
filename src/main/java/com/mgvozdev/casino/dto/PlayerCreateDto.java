package com.mgvozdev.casino.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record PlayerCreateDto(String documentNumber,
                              LocalDateTime openedAt,
                              BigDecimal buyIn,
                              Set<ChipSetDto> chips) {
}
