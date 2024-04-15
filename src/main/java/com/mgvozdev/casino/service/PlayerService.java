package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public Optional<Player> findById(UUID id) {
        return playerRepository.findById(id);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Transactional
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
