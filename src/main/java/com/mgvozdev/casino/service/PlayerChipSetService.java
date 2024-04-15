package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.PlayerChipSet;
import com.mgvozdev.casino.repository.PlayerChipSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerChipSetService {

    private final PlayerChipSetRepository playerChipSetRepository;

    public Optional<PlayerChipSet> findById(UUID id) {
        return playerChipSetRepository.findById(id);
    }

    @Transactional
    public PlayerChipSet create(PlayerChipSet playerChipSet) {
        return playerChipSetRepository.save(playerChipSet);
    }

    @Transactional
    public boolean delete(UUID id) {
        return playerChipSetRepository.findById(id)
                .map(entity -> {
                    playerChipSetRepository.delete(entity);
                    playerChipSetRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
