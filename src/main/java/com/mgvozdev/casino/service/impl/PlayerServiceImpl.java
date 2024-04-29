package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import com.mgvozdev.casino.mapper.ChipMapper;
import com.mgvozdev.casino.mapper.PlayerMapper;
import com.mgvozdev.casino.repository.PlayerChipSetRepository;
import com.mgvozdev.casino.repository.PlayerRepository;
import com.mgvozdev.casino.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerChipSetRepository playerChipSetRepository;
    private final PlayerMapper playerMapper;
    private final ChipMapper chipMapper;

    @Override
    public PlayerReadDto findById(UUID id) {
        return playerRepository.findById(id)
                .map((player) -> {
                    var chips = playerChipSetRepository.findByPlayerId(player.getId());
                    var chipSets = chips.stream()
                            .map(chipMapper::toDto)
                            .collect(Collectors.toSet());
                    return playerMapper.toDto(player).withChips(chipSets);
                })
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public List<PlayerReadDto> findAll() {
        var players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerException(ErrorMessage.NOT_FOUND);
        }
        return players.stream()
                .map((player) -> {
                    var chips = playerChipSetRepository.findByPlayerId(player.getId());
                    var chipDtos = chips.stream()
                            .map(chipMapper::toDto)
                            .collect(Collectors.toSet());
                    return playerMapper.toDto(player).withChips(chipDtos);
                })
                .toList();
    }

    @Transactional
    @Override
    public PlayerReadDto create(PlayerCreateDto playerCreateDto) {
        var player = Optional.of(playerCreateDto)
                .map(playerMapper::toEntity)
                .map(playerRepository::save);

        var chipDtos = playerCreateDto.chips().stream()
                .map(chipMapper::toEntity)
                .map(playerChipSet -> {
                    player.ifPresent(playerChipSet::setPlayer);
                    return playerChipSetRepository.save(playerChipSet);
                })
                .map(chipMapper::toDto)
                .collect(Collectors.toSet());

        if (player.isPresent()) {
            return playerMapper.toDto(player.get()).withChips(chipDtos);
        } else {
            throw new PlayerException(ErrorMessage.NOT_CREATED);
        }
    }

    @Transactional
    @Override
    public PlayerReadDto update(UUID id, PlayerEditDto playerEditDto) {
        var existing = playerRepository.findById(id);
        if (existing.isPresent()) {
            var chips = playerChipSetRepository.findByPlayerId(id).stream()
                    .map(chipMapper::toDto)
                    .collect(Collectors.toSet());

            var updated = existing.map(entity -> playerMapper.toEntity(playerEditDto, entity))
                    .map(playerRepository::saveAndFlush)
                    .map(player -> playerMapper.toDto(player).withChips(chips));

            if (updated.isPresent()) {
                return updated.get();
            } else {
                throw new PlayerException(ErrorMessage.NOT_UPDATED);
            }
        } else {
            throw new PlayerException(ErrorMessage.NOT_FOUND);
        }
    }

    @Transactional
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
