package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.*;
import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.entity.PlayerChipSet;
import com.mgvozdev.casino.entity.Profile;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import com.mgvozdev.casino.repository.PlayerRepository;
import com.mgvozdev.casino.repository.ProfileRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PlayerMapper {

    @Autowired
    protected ProfileRepository profileRepository;

    @Autowired
    protected PlayerRepository playerRepository;

    @Autowired
    protected ChipMapper chipMapper;

    @Mapping(target = "openedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "profile", source = "documentNumber", qualifiedByName = "getProfile")
    public abstract Player toEntity(PlayerCreateDto dto);

    public abstract Player toEntity(PlayerEditDto dto, @MappingTarget Player player);

    @Mapping(target = "documentType", source = "profile.documentType")
    @Mapping(target = "country", source = "profile.country")
    @Mapping(target = "documentNumber", source = "profile.documentNumber")
    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "lastName", source = "profile.lastName")
    @Mapping(target = "membershipType", source = "profile.membershipType")
    @Mapping(target = "total", source = "chips", qualifiedByName = "countTotal")
    @Mapping(target = "chips", source = "chips", qualifiedByName = "mapChips")
    public abstract PlayerReadDto toDto(Player player);

    @Mapping(target = "cashOut", source = "chips", qualifiedByName = "countTotal")
    public abstract PlayerSessionDto toSessionDto(Player player);

    @Named("getProfile")
    Profile getProfile(String documentNumber) {
        return Optional.ofNullable(documentNumber)
                .flatMap(profileRepository::findBy)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_FOUND));
    }

    @Named("countTotal")
    BigDecimal countTotal(Set<PlayerChipSet> chips) {
        var total = new BigDecimal(0);
        for (PlayerChipSet chipSet : chips) {
            total = total.add(chipSet.getTotal());
        }
        return total;
    }
    @Named("mapChips")
    Set<ChipSetDto> mapChips(Set<PlayerChipSet> chips) {
        return chips.stream()
                .map(chipMapper::toDto)
                .collect(Collectors.toSet());
    }

}
