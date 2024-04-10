package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import com.mgvozdev.casino.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") UUID id) {
        return playerService.findById(id)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_FOUND));
    }
}
