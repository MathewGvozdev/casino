package com.mgvozdev.casino.validation.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

public class UuidValidatorTest {

    private final UuidValidator uuidValidator = new UuidValidator();

    @ParameterizedTest
    @MethodSource("getUuidExamples")
    void checkUuidValidator(String uuid, Boolean expectedResult) {
        boolean isValid;
        try {
            isValid = uuidValidator.isValid(UUID.fromString(uuid), null);
        } catch (IllegalArgumentException e) {
            isValid = false;
        }
        Assertions.assertEquals(expectedResult, isValid);
    }

    private static Stream<Arguments> getUuidExamples() {
        return Stream.of(
                Arguments.of("123e4567-e89b-12d3-a456-426614174000", true),
                Arguments.of("f47ac10b-58cc-4372-a567-0e02b2c3d479", true),
                Arguments.of("6ba7b810-9dad-11d1-80b4-00c04fd430c8", true),
                Arguments.of("123e4567-e89b-12d3", false),
                Arguments.of("f47ac10b58cc4372a5670e02b2c3d479", false),
                Arguments.of("йцук1234-АААА-0000-ББББ-1234567890", false)
        );
    }
}