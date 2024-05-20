package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.DocumentType;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProfileReadDto(DocumentType documentType,
                             String country,
                             String documentNumber,
                             String firstName,
                             String lastName,
                             LocalDate dateOfBirth,
                             LocalDate issueDate,
                             LocalDate expirationDate,
                             String address,
                             String phoneNumber,
                             MembershipType membershipType,
                             ProfileStatus status,
                             BigDecimal totalDeposit,
                             BigDecimal totalWinnings,
                             List<PlayerSessionDto> sessions) {
}
