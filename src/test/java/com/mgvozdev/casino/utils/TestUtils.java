package com.mgvozdev.casino.utils;

import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.entity.enums.Chip;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TestUtils {

    public static PlayerEditDto getPlayerEditDto() {
        var buyIn = new BigDecimal(5500);
        var date = LocalDateTime.of(2024, 3, 30, 15, 50, 0);
        var avgBet = 200;
        return new PlayerEditDto(buyIn, date, avgBet);
    }

    public static PlayerCreateDto getPlayerCreateDto() {
        var chip = Chip.GREEN;
        var amount = 10;
        var documentNumber = "46728918";
        var total = BigDecimal.valueOf(chip.getValue())
                .multiply(BigDecimal.valueOf(amount));

        return new PlayerCreateDto(documentNumber, total);
    }

    public static Set<ChipSetDto> getSetOfChips() {
        Set<ChipSetDto> chips = new HashSet<>();
        chips.add(new ChipSetDto(Chip.GREEN, 10, new BigDecimal(250)));
        chips.add(new ChipSetDto(Chip.RED, 15, new BigDecimal(75)));
        return chips;
    }

    public static Set<ChipSetDto> getSetOfChipsForNegative() {
        Set<ChipSetDto> chips = new HashSet<>();
        chips.add(new ChipSetDto(Chip.GREEN, null, new BigDecimal(-50)));
        chips.add(new ChipSetDto(Chip.RED, null, new BigDecimal(100)));
        return chips;
    }

    public static ChipSetDto getChipSetDto() {
        return new ChipSetDto(Chip.RED, 40, new BigDecimal(200));
    }
}
