package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.entity.Profile;
import com.mgvozdev.casino.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Optional<Profile> findById(UUID id) {
        return profileRepository.findById(id);
    }
}
