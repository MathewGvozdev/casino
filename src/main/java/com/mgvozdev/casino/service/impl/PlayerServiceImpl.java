package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.PlayerCreateEditDto;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
                    var chipSets = chips.stream()
                            .map(chipMapper::toDto)
                            .collect(Collectors.toSet());
                    return playerMapper.toDto(player).withChips(chipSets);
                })
                .toList();
    }

    @Transactional
    @Override
    public PlayerReadDto create(PlayerCreateEditDto playerCreateEditDto) {
        var player = Optional.of(playerCreateEditDto)
                .map(playerMapper::toEntity)
                .map(playerRepository::save);

        playerCreateEditDto.chips().stream()
                .map(chipMapper::toEntity)
                .forEach(playerChipSet -> {
                    player.ifPresent(playerChipSet::setPlayer);
                    playerChipSetRepository.save(playerChipSet);
                });

        return player.map(playerMapper::toDto)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_CREATED));
    }

    @Override
    public PlayerReadDto update(UUID id, PlayerCreateEditDto playerCreateEditDto) {
        var updatedPlayer = Optional.of(playerCreateEditDto)
                .map(playerMapper::toEntity)
                .map(updated -> {
                    playerRepository.findById(id)
                            .ifPresent(player -> updated.setProfile(player.getProfile()));
                    return playerRepository.saveAndFlush(updated);
                });

        playerCreateEditDto.chips().stream()
                .map(chipMapper::toEntity)
                .forEach(playerChipSet -> {
                    updatedPlayer.ifPresent(playerChipSet::setPlayer);
                    playerChipSetRepository.saveAndFlush(playerChipSet);
                });

        return updatedPlayer.map(playerMapper::toDto)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_UPDATED));
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
