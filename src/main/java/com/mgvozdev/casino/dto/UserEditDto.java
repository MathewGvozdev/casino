package com.mgvozdev.casino.dto;

import jakarta.validation.constraints.Size;

public record UserEditDto(@Size(max = 32) String username,
                          @Size(max = 64) String password) {
}
