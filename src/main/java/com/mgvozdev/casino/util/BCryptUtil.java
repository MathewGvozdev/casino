package com.mgvozdev.casino.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public static String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
