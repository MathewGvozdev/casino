package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Profile;

import java.util.Optional;
import java.util.UUID;

public interface ProfileService {

    Optional<Profile> findById(UUID id);

    Profile create(Profile profile);

    boolean delete(UUID id);
}
