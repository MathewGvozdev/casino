package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.PlayerCreateEditDto;
import com.mgvozdev.casino.dto.PlayerFilter;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.entity.Profile;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import com.mgvozdev.casino.mapper.PlayerMapper;
import com.mgvozdev.casino.repository.PlayerRepository;
import com.mgvozdev.casino.repository.ProfileRepository;
import com.mgvozdev.casino.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ProfileRepository profileRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerReadDto findById(UUID id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDto)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public List<PlayerReadDto> findAll() {
        var players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerException(ErrorMessage.NOT_FOUND);
        }
        return players.stream()
                .map(playerMapper::toDto)
                .toList();
    }

    public Page<PlayerReadDto> findAll(PlayerFilter filter, Pageable pageable) {

    }

    @Transactional
    @Override
    public PlayerReadDto create(PlayerCreateEditDto playerCreateEditDto) {
        var profile = profileRepository.findBy(playerCreateEditDto.documentType(),
                playerCreateEditDto.country(),
                playerCreateEditDto.documentNumber());

        Player savedPlayer;
        if (profile.isPresent()) {
            var entity = playerMapper.toEntity(playerCreateEditDto);
            entity.setProfile(profile.get());
            savedPlayer = playerRepository.save(entity);
        } else {
            throw new PlayerException(ErrorMessage.NOT_CREATED);
        }
        return playerMapper.toDto(savedPlayer);

//        return Optional.of(playerCreateEditDto)
//                .map(playerMapper::toEntity)
//                .map(playerRepository::save)
//                .map(playerMapper::toDto)
//                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_CREATED));
    }

    @Override
    public PlayerReadDto update(UUID id, PlayerCreateEditDto playerCreateEditDto) {
        var player = playerRepository.findById(id);
        Player updatedPlayer;
        if (player.isPresent()) {
            updatedPlayer = playerMapper.toEntity(playerCreateEditDto);
            updatedPlayer.setProfile(player.get().getProfile());
            playerRepository.saveAndFlush(updatedPlayer);
        } else {
            throw new PlayerException(ErrorMessage.NOT_UPDATED);
        }
        return playerMapper.toDto(updatedPlayer);

//        if (player.isPresent()) {
//            return Optional.of(playerCreateEditDto)
//                    .map(playerMapper::toEntity)
//                    .map(playerRepository::saveAndFlush)
//                    .map(playerMapper::toDto)
//                    .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_UPDATED));
//        } else {
//            throw new PlayerException(ErrorMessage.NOT_FOUND);
//        }
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
