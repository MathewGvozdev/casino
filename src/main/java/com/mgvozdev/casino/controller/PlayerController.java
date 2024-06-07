package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.annotation.*;
import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.service.PlayerChipSetService;
import com.mgvozdev.casino.service.PlayerService;
import com.mgvozdev.casino.validation.ChipsChecker;
import com.mgvozdev.casino.validation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerChipSetService playerChipSetService;

    @FindPlayerById(path = "/{id}")
    public PlayerReadDto findById(@UuidChecker @PathVariable("id") UUID id) {
        return playerService.findById(id);
    }

    @FindPlayers
    public List<PlayerReadDto> findAll(@RequestParam(required = false) LocalDateTime openedFrom,
                                       @RequestParam(required = false) LocalDateTime openedTill) {
        if (openedFrom != null && openedTill != null) {
            return playerService.findByOpenedAtBetween(openedFrom, openedTill);
        } else {
            return playerService.findAll();
        }
    }

    @CreatePlayer
    public PlayerReadDto create(@Validated @RequestBody PlayerCreateDto playerCreateDto) {
        return playerService.create(playerCreateDto);
    }

    @UpdatePlayer(path = "/{id}")
    public PlayerReadDto update(@UuidChecker @PathVariable("id") UUID id,
                                @Validated @RequestBody PlayerEditDto playerEditDto) {
        return playerService.update(id, playerEditDto);
    }

    @DeletePlayer(path = "/{id}")
    public ResponseEntity<?> delete(@UuidChecker @PathVariable("id") UUID id) {
        return playerService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

    @AddChipsForPlayer(path = "/{id}/chips")
    public Set<ChipSetDto> addChipsForPlayer(@PathVariable("id") UUID id,
                                             @ChipsChecker @RequestBody Set<ChipSetDto> chips) {
        return playerChipSetService.create(id, chips);
    }

    @UpdateChipSetForPlayer(path = "/{id}/chips")
    public Set<ChipSetDto> updateChipSetForPlayer(@PathVariable("id") UUID id,
                                                  @Validated @RequestBody ChipSetDto chipSetDto) {
        playerChipSetService.update(id, chipSetDto);
        return playerChipSetService.findPlayerChips(id);
    }

    @DeleteChipsForPlayer(path = "/{id}/chips")
    public ResponseEntity<?> deleteAllPlayerChips(@UuidChecker @PathVariable("id") UUID id) {
        return playerChipSetService.deleteAll(id)
                ? noContent().build()
                : notFound().build();
    }
}
