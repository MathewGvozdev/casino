package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

    Optional<Player> findById(UUID id);

    List<Player> findByOpenedAtBetween(LocalDateTime openedAtStart,
                                       LocalDateTime openedAtEnd);

    List<Player> findByProfileId(UUID profileId);
}
