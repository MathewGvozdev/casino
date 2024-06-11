package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserEditDto;
import com.mgvozdev.casino.dto.UserReadDto;
import com.mgvozdev.casino.entity.Role;
import com.mgvozdev.casino.entity.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public abstract User toEntity(UserCreateDto userCreateDto);

    public abstract User toEntity(UserEditDto userEditDto, @MappingTarget User user);

    @Mapping(target = "firstName", source = "userInfo.firstName")
    @Mapping(target = "lastName", source = "userInfo.lastName")
    @Mapping(target = "shift", source = "userInfo.shift")
    @Mapping(target = "role", source = "roles", qualifiedByName = "mapRoles")
    public abstract UserReadDto toDto(User user);

    @Named("mapRoles")
    String mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getTitle)
                .collect(Collectors.joining(", "));
    }
}
