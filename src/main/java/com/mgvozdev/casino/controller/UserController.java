package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.dto.*;
import com.mgvozdev.casino.service.UserService;
import com.mgvozdev.casino.validation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserReadDto findById(@UuidChecker @PathVariable("id") UUID id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public UserReadDto create(@Validated @RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@UuidChecker @PathVariable("id") UUID id,
                              @Validated @RequestBody UserEditDto userEditDto) {
        return userService.update(id, userEditDto);
    }

    @PutMapping("/{id}/info")
    public UserReadDto update(@UuidChecker @PathVariable("id") UUID id,
                              @Validated @RequestBody UserInfoEditDto userInfoEditDto) {
        return userService.updateInfo(id, userInfoEditDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@UuidChecker @PathVariable("id") UUID id) {
        return userService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
