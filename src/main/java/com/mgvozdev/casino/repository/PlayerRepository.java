package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

    @Override
    Optional<Player> findById(UUID uuid);
}
