package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.DocumentType;
import com.mgvozdev.casino.entity.enums.MembershipType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record PlayerReadDto(DocumentType documentType,
                            String country,
                            String documentNumber,
                            String firstName,
                            String lastName,
                            MembershipType membershipType,
                            LocalDateTime openedAt,
                            BigDecimal buyIn,
                            LocalDateTime closedAt,
                            Integer avgBet,
                            BigDecimal total,
                            Set<ChipSetDto> chips) {
}
