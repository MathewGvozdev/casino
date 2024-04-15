package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.PlayerChipSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PlayerChipSetRepository extends JpaRepository<PlayerChipSet, UUID> {

    Optional<PlayerChipSet> findById(UUID id);

    Set<PlayerChipSet> findByPlayerId(UUID playerId);
}
