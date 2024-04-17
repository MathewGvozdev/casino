package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.entity.Profile;
import com.mgvozdev.casino.repository.ProfileRepository;
import com.mgvozdev.casino.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public Optional<Profile> findById(UUID id) {
        return profileRepository.findById(id);
    }

    @Transactional
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Transactional
    public boolean delete(UUID id) {
        return profileRepository.findById(id)
                .map(entity -> {
                    profileRepository.delete(entity);
                    profileRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
