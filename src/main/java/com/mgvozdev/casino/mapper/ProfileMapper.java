package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.PlayerSessionDto;
import com.mgvozdev.casino.dto.ProfileCreateEditDto;
import com.mgvozdev.casino.dto.ProfileReadDto;
import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.entity.Profile;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProfileMapper {

    @Autowired
    protected PlayerMapper playerMapper;

    public abstract Profile toEntity(ProfileCreateEditDto dto);

    public abstract Profile toEntity(ProfileCreateEditDto dto, @MappingTarget Profile profile);

    @Mapping(target = "sessions", source = "playerSessions", qualifiedByName = "mapSessions")
    public abstract ProfileReadDto toDto(Profile profile);

    @Named("mapSessions")
    List<PlayerSessionDto> mapSessions(List<Player> sessions) {
        return sessions.stream()
                .map(playerMapper::toSessionDto)
                .toList();
    }
}
