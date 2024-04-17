package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> findById(UUID id);

    User create(User user);

    boolean delete(UUID id);
}
