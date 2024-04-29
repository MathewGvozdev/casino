package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.annotation.*;
import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @FindPlayerById
    public PlayerReadDto findById(@PathVariable("id") UUID id) {
        return playerService.findById(id);
    }

    @FindAllPlayers
    public List<PlayerReadDto> findAll() {
        return playerService.findAll();
    }

    @RequestMapping(value = "/{openedAtStart}/{openedAtEnd}",
            method = RequestMethod.GET)
    public List<PlayerReadDto> findByOpenedAtBetween(@PathVariable LocalDateTime openedAtStart,
                                                     @PathVariable LocalDateTime openedAtEnd) {
        return playerService.findByOpenedAtBetween(openedAtStart, openedAtEnd);
    }

    @RequestMapping(value = "/sessions/{profileId}",
            method = RequestMethod.GET)
    public List<PlayerReadDto> findByProfileId(@PathVariable UUID profileId) {
        return playerService.findByProfileId(profileId);
    }

    @CreatePlayer
    public PlayerReadDto create(@RequestBody PlayerCreateDto playerCreateDto) {
        return playerService.create(playerCreateDto);
    }

    @UpdatePlayer
    public PlayerReadDto update(@PathVariable("id") UUID id,
                                @RequestBody PlayerEditDto playerEditDto) {
        return playerService.update(id, playerEditDto);
    }

    @DeletePlayer
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        return playerService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
