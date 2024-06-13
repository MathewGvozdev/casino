package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.annotation.*;
import com.mgvozdev.casino.dto.*;
import com.mgvozdev.casino.service.UserService;
import com.mgvozdev.casino.validation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @FindUserById(path = "/{id}")
    public UserReadDto findById(@UuidChecker @PathVariable("id") UUID id) {
        return userService.findById(id);
    }

    @FindUsers
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }

    @CreateUser
    public UserReadDto create(@Validated @RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @UpdateUser(path = "/{id}")
    public UserReadDto update(@UuidChecker @PathVariable("id") UUID id,
                              @Validated @RequestBody UserEditDto userEditDto) {
        return userService.update(id, userEditDto);
    }

    @UpdateUserInfo(path = "/{id}/info")
    public UserReadDto update(@UuidChecker @PathVariable("id") UUID id,
                              @Validated @RequestBody UserInfoEditDto userInfoEditDto) {
        return userService.updateInfo(id, userInfoEditDto);
    }
}
