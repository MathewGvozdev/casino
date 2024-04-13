package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Override
    Optional<Profile> findById(UUID uuid);
}