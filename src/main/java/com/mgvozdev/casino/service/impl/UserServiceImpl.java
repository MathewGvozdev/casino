package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.UserCreateDto;
import com.mgvozdev.casino.dto.UserEditDto;
import com.mgvozdev.casino.dto.UserInfoEditDto;
import com.mgvozdev.casino.dto.UserReadDto;
import com.mgvozdev.casino.entity.Role;
import com.mgvozdev.casino.entity.User;
import com.mgvozdev.casino.entity.UserInfo;
import com.mgvozdev.casino.repository.RoleRepository;
import com.mgvozdev.casino.util.ErrorMessage;
import com.mgvozdev.casino.exception.UserException;
import com.mgvozdev.casino.mapper.UserInfoMapper;
import com.mgvozdev.casino.mapper.UserMapper;
import com.mgvozdev.casino.repository.UserInfoRepository;
import com.mgvozdev.casino.repository.UserRepository;
import com.mgvozdev.casino.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.mgvozdev.casino.util.BCryptUtil.encryptPassword;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;
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
                .map(dto -> dto.withEncryptedPassword(encryptPassword(userCreateDto.password())))
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(user -> {
                    var userInfo = saveUserInfo(userCreateDto);
                    setRoleFromDto(userCreateDto, user);
                    return userInfoMapper.toDto(userInfo);
                })
                .orElseThrow(() -> new UserException(ErrorMessage.NOT_CREATED));
    }

    private UserInfo saveUserInfo(UserCreateDto userCreateDto) {
        var userInfo = userInfoMapper.toEntity(userCreateDto);
        return userInfoRepository.save(userInfo);
    }

    private void setRoleFromDto(UserCreateDto userCreateDto, User user) {
        var role = roleRepository.findByTitle(userCreateDto.role());
        if (role.isPresent()) {
            var roles = Collections.singleton(role.get());
            user.setRoles(roles);
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        getAuthorities(user.getRoles())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
            role.getAuthorities().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getPermission())));
        }
        return authorities;
    }
}
