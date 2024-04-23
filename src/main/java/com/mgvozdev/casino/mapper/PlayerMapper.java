package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.PlayerCreateEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.entity.Profile;
import com.mgvozdev.casino.repository.ProfileRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class PlayerMapper {

    @Autowired
    protected ProfileRepository profileRepository;

    @Mapping(target = "openedAt", source = "openedAt")
    @Mapping(target = "buyIn", source = "buyIn")
    @Mapping(target = "closedAt", source = "closedAt")
    @Mapping(target = "avgBet", source = "avgBet")
    @Mapping(target = "profile", source = "documentNumber", qualifiedByName = "getProfile")
    public abstract Player toEntity(PlayerCreateEditDto dto);

    @Mapping(target = "documentType", source = "profile.documentType")
    @Mapping(target = "country", source = "profile.country")
    @Mapping(target = "documentNumber", source = "profile.documentNumber")
    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "lastName", source = "profile.lastName")
    @Mapping(target = "membershipType", source = "profile.membershipType")
    @Mapping(target = "openedAt", source = "openedAt")
    @Mapping(target = "buyIn", source = "buyIn")
    @Mapping(target = "closedAt", source = "closedAt")
    @Mapping(target = "avgBet", source = "avgBet")
    public abstract PlayerReadDto toDto(Player player);

    @Named("getProfile")
    Profile getProfile(String documentNumber) {
        return Optional.ofNullable(documentNumber)
                .flatMap(profileRepository::findBy)
                .orElse(null);
    }
}
