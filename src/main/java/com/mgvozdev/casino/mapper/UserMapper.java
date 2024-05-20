package com.mgvozdev.casino.mapper;

import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserEditDto;
import com.mgvozdev.casino.dto.UserReadDto;
import com.mgvozdev.casino.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserCreateDto userCreateDto);

    User toEntity(UserEditDto userEditDto, @MappingTarget User user);

    @Mapping(target = "firstName", source = "userInfo.firstName")
    @Mapping(target = "lastName", source = "userInfo.lastName")
    @Mapping(target = "shift", source = "userInfo.shift")
    UserReadDto toDto(User user);
}
