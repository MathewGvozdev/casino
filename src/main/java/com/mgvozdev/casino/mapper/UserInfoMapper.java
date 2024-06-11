package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserInfoEditDto;
import com.mgvozdev.casino.dto.UserReadDto;
import com.mgvozdev.casino.entity.Role;
import com.mgvozdev.casino.entity.User;
import com.mgvozdev.casino.entity.UserInfo;
import com.mgvozdev.casino.util.ErrorMessage;
import com.mgvozdev.casino.exception.UserException;
import com.mgvozdev.casino.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserInfoMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "user", source = "username", qualifiedByName = "mapUser")
    public abstract UserInfo toEntity(UserCreateDto userCreateDto);

    public abstract UserInfo toEntity(UserInfoEditDto userInfoEditDto, @MappingTarget UserInfo user);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "role", source = "user.roles", qualifiedByName = "mapRoles")
    public abstract UserReadDto toDto(UserInfo user);

    @Named("mapUser")
    User mapUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(ErrorMessage.NOT_FOUND));
    }

    @Named("mapRoles")
    String mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getTitle)
                .collect(Collectors.joining(", "));
    }
}
