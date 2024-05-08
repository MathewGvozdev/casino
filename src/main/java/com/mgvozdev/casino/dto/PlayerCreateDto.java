package com.mgvozdev.casino.dto;

import java.math.BigDecimal;
import java.util.Set;

public record PlayerCreateDto(String documentNumber,
                              BigDecimal buyIn,
                              Set<ChipSetDto> chips) {
}
