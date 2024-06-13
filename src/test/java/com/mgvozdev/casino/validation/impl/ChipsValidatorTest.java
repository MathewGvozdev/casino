package com.mgvozdev.casino.validation.impl;

import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.entity.enums.Chip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ChipsValidatorTest {

    private final ChipsValidator chipsValidator = new ChipsValidator();

    @ParameterizedTest
    @MethodSource("getChips")
    void checkChipsValidator(Set<ChipSetDto> chips, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, chipsValidator.isValid(chips, null));
    }

    private static Stream<Arguments> getChips() {
        Set<ChipSetDto> example1 = new HashSet<>();
        example1.add(new ChipSetDto(Chip.RED, 5, new BigDecimal(25)));
        example1.add(new ChipSetDto(Chip.GREEN, 5, new BigDecimal(125)));

        Set<ChipSetDto> example2 = new HashSet<>();
        example2.add(new ChipSetDto(Chip.RED, 20, new BigDecimal(100)));
        example2.add(new ChipSetDto(Chip.GREEN, 4, new BigDecimal(100)));
        example2.add(new ChipSetDto(Chip.BLACK, 1, new BigDecimal(100)));

        Set<ChipSetDto> example3 = new HashSet<>();
        example3.add(new ChipSetDto(Chip.RED, -5, new BigDecimal(-25)));

        Set<ChipSetDto> example4 = new HashSet<>();
        example4.add(new ChipSetDto(Chip.RED, -10, new BigDecimal(50)));
        example4.add(new ChipSetDto(Chip.GREEN, 4, new BigDecimal(-100)));

        return Stream.of(
                Arguments.of(example1, true),
                Arguments.of(example2, true),
                Arguments.of(example3, false),
                Arguments.of(example4, false)
        );
    }
}
