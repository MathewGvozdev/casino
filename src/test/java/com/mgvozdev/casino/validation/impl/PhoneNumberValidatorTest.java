package com.mgvozdev.casino.validation.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PhoneNumberValidatorTest {

    private final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

    @ParameterizedTest
    @MethodSource("getPhoneNumbers")
    void checkPhoneNumberValidator(String phoneNumber, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, phoneNumberValidator.isValid(phoneNumber, null));
    }

    private static Stream<Arguments> getPhoneNumbers() {
        return Stream.of(
                Arguments.of("1234567890", true),
                Arguments.of("123-456-7890", true),
                Arguments.of("(123)456-7890", true),
                Arguments.of("(123)4567890", true),
                Arguments.of("+1234567890", false),
                Arguments.of("+1 (234) 567-890", false)
        );
    }
}