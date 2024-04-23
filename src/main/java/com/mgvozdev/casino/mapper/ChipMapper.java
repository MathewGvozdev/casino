package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.entity.PlayerChipSet;
import com.mgvozdev.casino.entity.enums.Chip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChipMapper {

    @Mapping(target = "chip", source = "chip")
    @Mapping(target = "amount", expression = "java(countAmount(dto.chip(), dto.total()))")
    @Mapping(target = "total", source = "total")
    PlayerChipSet toEntity(ChipSetDto dto);

    @Mapping(target = "chip", source = "chip")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "total", source = "total")
    ChipSetDto toDto(PlayerChipSet playerChipSet);

    default Integer countAmount(Chip chip, BigDecimal total) {
        var chipValue = BigDecimal.valueOf(chip.getValue());
        return total.divide(chipValue, RoundingMode.HALF_UP).intValue();
    }
}
