package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.DocumentType;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class ProfileCreateEditDto {

    DocumentType documentType;
    String country;
    String documentNumber;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    LocalDate issueDate;
    LocalDate expirationDate;
    String address;
    String phoneNumber;
    MembershipType membershipType;
    ProfileStatus status;
    BigDecimal totalDeposit;
    BigDecimal totalWinnings;
}
