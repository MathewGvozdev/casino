package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.Shift;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserInfoEditDto(String firstName,
                              String lastName,
                              Shift shift,
                              LocalDate hiredOn,
                              BigDecimal salary) {
}
