package com.mgvozdev.casino.utils;

import com.mgvozdev.casino.dto.*;
import com.mgvozdev.casino.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        return new ChipSetDto(Chip.GREEN, 16, new BigDecimal(400));
    }

    public static ProfileCreateEditDto getProfileCreateEditDto() {
        return new ProfileCreateEditDto(
                DocumentType.DRIVER_LICENSE,
                "CAN",
                "00224400",
                "Tomas",
                "Shwarz",
                LocalDate.of(1980, 3, 23),
                LocalDate.of(2020, 1, 14),
                LocalDate.of(2025, 1, 14),
                "Berlin Gote Strasse 80",
                "4021948199",
                MembershipType.BRONZE,
                ProfileStatus.PERMITTED,
                new BigDecimal(0),
                new BigDecimal(0)
                );
    }

    public static UserCreateDto getUserCreateDto() {
        return new UserCreateDto(
                "newuser",
                "pass",
                "John",
                "Johnson",
                Shift.DAY,
                LocalDate.of(2024, 6, 10),
                new BigDecimal(32)
        );
    }

    public static UserEditDto getUserEditDto() {
        return new UserEditDto("edited_user", "newpass");
    }

    public static UserInfoEditDto getUserInfoEditDto() {
        return new UserInfoEditDto(
                "New",
                "User",
                Shift.GRAVE,
                LocalDate.now(),
                new BigDecimal(30));
    }
}
