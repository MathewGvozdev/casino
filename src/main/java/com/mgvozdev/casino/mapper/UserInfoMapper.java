package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserInfoEditDto;
import com.mgvozdev.casino.dto.UserReadDto;
import com.mgvozdev.casino.entity.User;
import com.mgvozdev.casino.entity.UserInfo;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.UserException;
import com.mgvozdev.casino.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserInfoMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "user", source = "username", qualifiedByName = "mapUser")
    public abstract UserInfo toEntity(UserCreateDto userCreateDto);

    public abstract UserInfo toEntity(UserInfoEditDto userInfoEditDto, @MappingTarget UserInfo user);

    @Mapping(target = "username", source = "user.username")
    public abstract UserReadDto toDto(UserInfo user);

    @Named("mapUser")
    User mapUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(ErrorMessage.NOT_FOUND));
    }
}
