package com.mgvozdev.casino.service;

import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserEditDto;
import com.mgvozdev.casino.dto.UserInfoEditDto;
import com.mgvozdev.casino.dto.UserReadDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserReadDto findById(UUID id);

    List<UserReadDto> findAll();

    UserReadDto create(UserCreateDto userCreateDto);

    UserReadDto update(UUID id, UserEditDto userEditDto);

    UserReadDto updateInfo(UUID id, UserInfoEditDto userInfoEditDto);

    boolean delete(UUID id);
}
