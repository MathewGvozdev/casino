package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.Shift;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserInfoEditDto(@Size(max = 32) String firstName,
                              @Size(max = 32) String lastName,
                              Shift shift,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredOn,
                              @Positive BigDecimal salary) {
}
