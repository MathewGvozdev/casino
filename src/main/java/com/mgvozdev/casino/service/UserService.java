package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.User;
import com.mgvozdev.casino.repository.UserInfoRepository;
import com.mgvozdev.casino.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User create(User user) {
        var userInfo = user.getUserInfo();
        var savedUser = userRepository.save(user);
        userInfoRepository.save(userInfo);
        return savedUser;
    }

    @Transactional
    public boolean delete(UUID id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userInfoRepository.delete(entity.getUserInfo());
                    userRepository.delete(entity);
                    userInfoRepository.flush();
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
