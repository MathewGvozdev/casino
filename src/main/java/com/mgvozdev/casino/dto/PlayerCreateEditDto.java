package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.DocumentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlayerCreateEditDto(DocumentType documentType,
                                  String country,
                                  String documentNumber,
                                  LocalDateTime openedAt,
                                  BigDecimal buyIn,
                                  LocalDateTime closedAt,
                                  Integer avgBet) {
}
