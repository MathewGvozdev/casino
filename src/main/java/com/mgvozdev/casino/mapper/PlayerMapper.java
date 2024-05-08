package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.entity.PlayerChipSet;
import com.mgvozdev.casino.entity.Profile;
import com.mgvozdev.casino.repository.PlayerRepository;
import com.mgvozdev.casino.repository.ProfileRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PlayerMapper {

    @Autowired
    protected ProfileRepository profileRepository;

    @Autowired
    protected PlayerRepository playerRepository;

    @Autowired
    protected ChipMapper chipMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "openedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "buyIn", source = "buyIn")
    @Mapping(target = "closedAt", ignore = true)
    @Mapping(target = "avgBet", ignore = true)
    @Mapping(target = "profile", source = "documentNumber", qualifiedByName = "getProfile")
    @Mapping(target = "tableSessions", ignore = true)
    @Mapping(target = "chips", ignore = true)
    public abstract Player toEntity(PlayerCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "openedAt", ignore = true)
    @Mapping(target = "buyIn", source = "buyIn")
    @Mapping(target = "closedAt", source = "closedAt")
    @Mapping(target = "avgBet", source = "avgBet")
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "tableSessions", ignore = true)
    @Mapping(target = "chips", ignore = true)
    public abstract Player toEntity(PlayerEditDto dto, @MappingTarget Player player);

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
    @Mapping(target = "total", source = "chips", qualifiedByName = "countTotal")
    public abstract PlayerReadDto toDto(Player player);

    @Named("getProfile")
    Profile getProfile(String documentNumber) {
        return Optional.ofNullable(documentNumber)
                .flatMap(profileRepository::findBy)
                .orElse(null);
    }

    @Named("countTotal")
    BigDecimal countTotal(Set<PlayerChipSet> chips) {
        var total = new BigDecimal(0);
        for (PlayerChipSet chipSet : chips) {
            total = total.add(chipSet.getTotal());
        }
        return total;
    }
}
