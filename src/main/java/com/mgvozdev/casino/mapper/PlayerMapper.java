package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.PlayerCreateEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PlayerMapper {

//    @Mapping(target = "profile",
//            expression = "java(profileRepository.findBy(dto.documentType(), dto.country(), dto.documentNumber()))")
    @Mapping(target = "openedAt", source = "openedAt")
    @Mapping(target = "buyIn", source = "buyIn")
    @Mapping(target = "closedAt", source = "closedAt")
    @Mapping(target = "avgBet", source = "avgBet")
    Player toEntity(PlayerCreateEditDto dto);

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
    PlayerReadDto toDto(Player player);

//    @AfterMapping
//    default void setProfile(@MappingTarget Player player,
//                          PlayerCreateEditDto dto,
//                          @Context ProfileRepository profileRepository) {
//        profileRepository.findBy(dto.documentType(),
//                        dto.country(),
//                        dto.documentNumber())
//                .ifPresent(player::setProfile);
//}
}
