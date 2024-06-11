package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.DocumentType;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;
import com.mgvozdev.casino.validation.PhoneNumberChecker;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class ProfileCreateEditDto {

    DocumentType documentType;

    @Size(max = 3)
    String country;

    @Size(min = 8, max = 32)
    String documentNumber;

    @Size(max = 32)
    String firstName;

    @Size(max = 32)
    String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfBirth;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate issueDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate expirationDate;

    @Size(max = 64)
    String address;

    @PhoneNumberChecker
    String phoneNumber;

    MembershipType membershipType;

    ProfileStatus status;

    @PositiveOrZero
    BigDecimal totalDeposit;

    @PositiveOrZero
    BigDecimal totalWinnings;
}
