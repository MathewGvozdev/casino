package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.PlayerChipSet;

import java.util.Optional;
import java.util.UUID;

public interface PlayerChipSetService {

    Optional<PlayerChipSet> findById(UUID id);

    PlayerChipSet create(PlayerChipSet playerChipSet);

    boolean delete(UUID id);
}
