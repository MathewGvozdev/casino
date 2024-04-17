package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Authority;

import java.util.Optional;
import java.util.UUID;

public interface AuthorityService {

    Optional<Authority> findById(UUID id);

    Authority create(Authority authority);

    boolean delete(UUID id);
}
