package com.mgvozdev.casino.service;

import com.mgvozdev.casino.dto.PlayerCreateEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;

import java.util.List;
import java.util.UUID;

public interface PlayerService {

    PlayerReadDto findById(UUID id);

    List<PlayerReadDto> findAll();

    PlayerReadDto create(PlayerCreateEditDto playerCreateEditDto);

    PlayerReadDto update(UUID id, PlayerCreateEditDto playerCreateEditDto);

    boolean delete(UUID id);
}
