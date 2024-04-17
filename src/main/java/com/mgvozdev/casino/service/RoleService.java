package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleService {

    Optional<Role> findById(UUID id);

    Role create(Role role);

    boolean delete(UUID id);
}
