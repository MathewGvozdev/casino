package com.mgvozdev.casino.dto;

import com.mgvozdev.casino.entity.enums.Shift;

public record UserReadDto(String username,
                          String firstName,
                          String lastName,
                          Shift shift,
                          String role) {
}
