package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.dto.PlayerCreateEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{id}")
    public PlayerReadDto findById(@PathVariable("id") UUID id) {
        return playerService.findById(id);
    }

    @GetMapping
    public List<PlayerReadDto> findAll() {
        return playerService.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerReadDto create(@RequestBody PlayerCreateEditDto playerCreateEditDto) {
        return playerService.create(playerCreateEditDto);
    }

    @PutMapping("/update/{id}")
    public PlayerReadDto update(@PathVariable("id") UUID id,
                                @RequestBody PlayerCreateEditDto playerCreateEditDto) {
        return playerService.update(id, playerCreateEditDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        return playerService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
