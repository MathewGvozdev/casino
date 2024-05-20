package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.entity.PlayerChipSet;
import com.mgvozdev.casino.entity.enums.Chip;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChipMapper {

    @Mapping(target = "amount", expression = "java(countAmount(dto.chip(), dto.total()))")
    PlayerChipSet toEntity(ChipSetDto dto);

    @Mapping(target = "amount", expression = "java(countAmount(dto.chip(), dto.total()))")
    PlayerChipSet toEntity(ChipSetDto dto, @MappingTarget PlayerChipSet playerChipSet);

    ChipSetDto toDto(PlayerChipSet playerChipSet);

    default Integer countAmount(Chip chip, BigDecimal total) {
        var chipValue = BigDecimal.valueOf(chip.getValue());
        return total.divide(chipValue, RoundingMode.HALF_UP).intValue();
    }
}
