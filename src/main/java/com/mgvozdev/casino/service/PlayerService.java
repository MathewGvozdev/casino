package com.mgvozdev.casino.service;

import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PlayerService {

    PlayerReadDto findById(UUID id);

    List<PlayerReadDto> findAll();

    List<PlayerReadDto> findByOpenedAtBetween(LocalDateTime openedAtStart,
                                       LocalDateTime openedAtEnd);

    PlayerReadDto create(PlayerCreateDto playerCreateDto);

    PlayerReadDto update(UUID id, PlayerEditDto playerEditDto);

    boolean delete(UUID id);
}
