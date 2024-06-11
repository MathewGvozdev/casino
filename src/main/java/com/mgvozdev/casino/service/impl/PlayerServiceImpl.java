package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.util.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import com.mgvozdev.casino.mapper.PlayerMapper;
import com.mgvozdev.casino.repository.PlayerRepository;
import com.mgvozdev.casino.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerReadDto findById(UUID id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDto)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public List<PlayerReadDto> findAll() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Override
    public List<PlayerReadDto> findByOpenedAtBetween(LocalDateTime openedAtStart, LocalDateTime openedAtEnd) {
        return playerRepository.findByOpenedAtBetween(openedAtStart, openedAtEnd).stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Override
    public PlayerReadDto create(PlayerCreateDto playerCreateDto) {
        return Optional.of(playerCreateDto)
                .map(playerMapper::toEntity)
                .map(playerRepository::save)
                .map(playerMapper::toDto)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_CREATED));
    }

    @Override
    public PlayerReadDto update(UUID id, PlayerEditDto playerEditDto) {
        var playerFromDB = playerRepository.findById(id);
        if (playerFromDB.isPresent()) {
            return playerFromDB.map(entity -> playerMapper.toEntity(playerEditDto, entity))
                    .map(playerRepository::saveAndFlush)
                    .map(playerMapper::toDto)
                    .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_UPDATED));
        } else {
            throw new PlayerException(ErrorMessage.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(UUID id) {
        return playerRepository.findById(id)
                .map(entity -> {
                    playerRepository.delete(entity);
                    playerRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
