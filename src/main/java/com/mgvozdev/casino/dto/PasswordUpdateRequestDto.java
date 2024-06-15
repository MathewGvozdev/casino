package com.mgvozdev.casino.dto;

import lombok.Value;

@Value
public class PasswordUpdateRequestDto {

    String currentPassword;
    String newPassword;
}
