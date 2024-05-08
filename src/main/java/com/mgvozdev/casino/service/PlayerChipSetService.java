package com.mgvozdev.casino.service;

import com.mgvozdev.casino.dto.ChipSetDto;

import java.util.Set;
import java.util.UUID;

public interface PlayerChipSetService {

    Set<ChipSetDto> findPlayerChips(UUID id);

    ChipSetDto update(UUID playerId, ChipSetDto chip);

    Set<ChipSetDto> create(UUID playerId, Set<ChipSetDto> chips);

    boolean deleteAll(UUID playerId);
}
