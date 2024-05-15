package com.mgvozdev.casino.utils;

import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.entity.enums.Chip;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestUtils {

    public static PlayerEditDto getPlayerEditDto() {
        var buyIn = new BigDecimal(5500);
        var date = LocalDateTime.of(2024, 3, 30, 15, 50, 0);
        var avgBet = 200;
        return new PlayerEditDto(buyIn, date, 10);
    }

    public static PlayerCreateDto getPlayerCreateDto() {
        var chip = Chip.GREEN;
        var amount = 10;
        var documentNumber = "46728918";
        var total = BigDecimal.valueOf(chip.getValue())
                .multiply(BigDecimal.valueOf(amount));

        return new PlayerCreateDto(documentNumber, total);
    }
}
