package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.Shift;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserCreateDto(@Size(max = 32) String username,
                            @Size(max = 64) String password,
                            @Size(max = 32) String firstName,
                            @Size(max = 32) String lastName,
                            Shift shift,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredOn,
                            @Positive BigDecimal salary,
                            String role) {

    public UserCreateDto withEncryptedPassword(String encryptedPassword) {
        return new UserCreateDto(
                this.username,
                encryptedPassword,
                this.firstName,
                this.lastName,
                this.shift,
                this.hiredOn,
                this.salary,
                this.role
        );
    }
}
