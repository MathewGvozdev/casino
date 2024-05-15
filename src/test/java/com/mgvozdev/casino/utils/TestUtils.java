package com.mgvozdev.casino.utils;

import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.entity.enums.Chip;
import com.mgvozdev.casino.entity.enums.DocumentType;
import com.mgvozdev.casino.entity.enums.MembershipType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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

//    public static PlayerReadDto getPlayerReadDto() {
//        return new PlayerReadDto(DocumentType.DRIVER_LICENSE,
//                "USA",
//                "18401294",
//                "Olivia",
//                "Sin",
//                MembershipType.SILVER,
//                LocalDateTime.of(2024, 3, 30, 16, 0, 0),
//                new BigDecimal(800),
//                null,
//                null,
//                new BigDecimal(1200));
//    }

//    public static List<PlayerReadDto> getListOfPlayerReadDtos() {
//        List<PlayerReadDto> players = new ArrayList<>();
//
//        players.add(new PlayerReadDto(DocumentType.DRIVER_LICENSE,
//                "USA",
//                "18401294",
//                "Olivia",
//                "Sin",
//                MembershipType.SILVER,
//                LocalDateTime.of(2024, 3, 29, 11, 30, 0),
//                new BigDecimal(1000),
//                LocalDateTime.of(2024, 3, 29, 16, 0, 0),
//                50,
//                new BigDecimal(0)));
//        players.add(new PlayerReadDto(DocumentType.DRIVER_LICENSE,
//                "USA",
//                "98139401",
//                "Henry",
//                "Styles",
//                MembershipType.GOLD,
//                LocalDateTime.of(2024, 3, 29, 19, 25, 0),
//                new BigDecimal(5000),
//                LocalDateTime.of(2024, 3, 30, 2, 10, 0),
//                150,
//                new BigDecimal(0)));
//        players.add(new PlayerReadDto(DocumentType.DRIVER_LICENSE,
//                "USA",
//                "18401294",
//                "Olivia",
//                "Sin",
//                MembershipType.SILVER,
//                LocalDateTime.of(2024, 3, 30, 16, 00, 0),
//                new BigDecimal(800),
//                null,
//                null,
//                new BigDecimal(1200)));
//        players.add(new PlayerReadDto(DocumentType.ID_CARD,
//                "USA",
//                "46728918",
//                "John",
//                "White",
//                MembershipType.BRONZE,
//                LocalDateTime.of(2024, 3, 30, 16, 30, 0),
//                new BigDecimal(100),
//                null,
//                null,
//                new BigDecimal(125)));
//        return players;
//    }
}
