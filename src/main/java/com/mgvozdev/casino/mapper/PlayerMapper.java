package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.PlayerCreateEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMapper {

    Player toEntity(PlayerCreateEditDto playerCreateEditDto);

    PlayerReadDto toDto(Player player);
}
