package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserEditDto;
import com.mgvozdev.casino.dto.UserInfoEditDto;
import com.mgvozdev.casino.dto.UserReadDto;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.UserException;
import com.mgvozdev.casino.mapper.UserInfoMapper;
import com.mgvozdev.casino.mapper.UserMapper;
import com.mgvozdev.casino.repository.UserInfoRepository;
import com.mgvozdev.casino.repository.UserRepository;
import com.mgvozdev.casino.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;

    @Override
    public UserReadDto findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserReadDto create(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto)
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(entity -> {
                    saveUserInfo(userCreateDto);
                    return userMapper.toDto(entity);
                })
                .orElseThrow(() -> new UserException(ErrorMessage.NOT_CREATED));
    }

    private void saveUserInfo(UserCreateDto userCreateDto) {
        var userInfo = userInfoMapper.toEntity(userCreateDto);
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserReadDto update(UUID id, UserEditDto userEditDto) {
        var userFromDB = userRepository.findById(id);
        if (userFromDB.isPresent()) {
            return userFromDB.map(entity -> userMapper.toEntity(userEditDto, entity))
                    .map(userRepository::saveAndFlush)
                    .map(userMapper::toDto)
                    .orElseThrow(() -> new UserException(ErrorMessage.NOT_UPDATED));
        } else {
            throw new UserException(ErrorMessage.NOT_FOUND);
        }
    }

    @Override
    public UserReadDto updateInfo(UUID id, UserInfoEditDto userInfoEditDto) {
        var userInfoFromDB = userInfoRepository.findByUserId(id);
        if (userInfoFromDB.isPresent()) {
            return userInfoFromDB.map(entity -> userInfoMapper.toEntity(userInfoEditDto, entity))
                    .map(userInfoRepository::saveAndFlush)
                    .map(userInfoMapper::toDto)
                    .orElseThrow(() -> new UserException(ErrorMessage.NOT_UPDATED));
        } else {
            throw new UserException(ErrorMessage.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(UUID id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
