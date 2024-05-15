package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.annotation.AddChipsForPlayer;
import com.mgvozdev.casino.annotation.DeleteChipsForPlayer;
import com.mgvozdev.casino.annotation.UpdateChipSetForPlayer;
import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.service.PlayerChipSetService;
import com.mgvozdev.casino.validation.ChipsChecker;
import com.mgvozdev.casino.validation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequiredArgsConstructor
public class ChipController {

    private final PlayerChipSetService playerChipSetService;

    //TODO 15.05.2024:
//    private final TableChipSetService tableChipSetService;

    @AddChipsForPlayer(path = "/players/{id}/chips")
    public Set<ChipSetDto> addChipsForPlayer(@PathVariable("id") UUID id,
                                             @ChipsChecker @RequestBody Set<ChipSetDto> chips) {
        return playerChipSetService.create(id, chips);
    }

    @UpdateChipSetForPlayer(path = "/players/{id}/chips")
    public Set<ChipSetDto> updateChipSetForPlayer(@PathVariable("id") UUID id,
                                           @Validated @RequestBody ChipSetDto chipSetDto) {
        playerChipSetService.update(id, chipSetDto);
        return playerChipSetService.findPlayerChips(id);
    }

    @DeleteChipsForPlayer(path = "/players/{id}/chips")
    public ResponseEntity<?> deleteAllPlayerChips(@UuidChecker @PathVariable("id") UUID id) {
        return playerChipSetService.deleteAll(id)
                ? noContent().build()
                : notFound().build();
    }
}
