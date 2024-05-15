package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.exception.ChipException;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import com.mgvozdev.casino.mapper.ChipMapper;
import com.mgvozdev.casino.repository.PlayerChipSetRepository;
import com.mgvozdev.casino.repository.PlayerRepository;
import com.mgvozdev.casino.service.PlayerChipSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerChipSetServiceImpl implements PlayerChipSetService {

    private final PlayerChipSetRepository playerChipSetRepository;
    private final PlayerRepository playerRepository;
    private final ChipMapper chipMapper;

    @Override
    public Set<ChipSetDto> findPlayerChips(UUID id) {
        return playerChipSetRepository.findByPlayerId(id).stream()
                .map(chipMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ChipSetDto update(UUID playerId, ChipSetDto chipDto) {
        var chipSetFromDB = playerChipSetRepository.findByChipAndPlayerId(chipDto.chip(), playerId);
        if (chipSetFromDB.isPresent()) {
            return chipSetFromDB.map(entity -> {
                        var chipSet = chipMapper.toEntity(chipDto, entity);
                        var player = playerRepository.findById(playerId);
                        player.ifPresent(entity::setPlayer);
                        return playerChipSetRepository.saveAndFlush(chipSet);
                    })
                    .map(chipMapper::toDto)
                    .orElseThrow(() -> new ChipException(ErrorMessage.NOT_UPDATED));
        } else {
            throw new ChipException(ErrorMessage.NOT_FOUND);
        }
    }

    @Override
    public Set<ChipSetDto> create(UUID playerId, Set<ChipSetDto> chips) {
        return chips.stream()
                .map(chipMapper::toEntity)
                .map(entity -> {
                    var player = playerRepository.findById(playerId);
                    player.ifPresentOrElse(
                            entity::setPlayer,
                            () -> {
                                throw new PlayerException(ErrorMessage.NOT_FOUND);
                            });
                    return playerChipSetRepository.save(entity);
                })
                .map(chipMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean deleteAll(UUID playerId) {
        var chips = playerChipSetRepository.findByPlayerId(playerId);
        if (chips.isEmpty()) {
            throw new PlayerException(ErrorMessage.NOT_FOUND);
        }
        try {
            playerChipSetRepository.deleteAll(chips);
            playerChipSetRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
