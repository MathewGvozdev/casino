package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public Optional<Player> findById(UUID id) {
        return playerRepository.findById(id);
    }
}
