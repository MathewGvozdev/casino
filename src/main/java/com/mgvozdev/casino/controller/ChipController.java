package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.service.PlayerChipSetService;
import com.mgvozdev.casino.validation.UUIDChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequiredArgsConstructor
public class ChipController {

    private final PlayerChipSetService playerChipSetService;
//    private final TableChipSetService tableChipSetService;

    @PostMapping("/players/{id}/chips")
    public Set<ChipSetDto> create(@PathVariable("id") UUID id,
                                  @RequestBody Set<ChipSetDto> chips) {
        return playerChipSetService.create(id, chips);
    }

    @PutMapping("players/{id}/chips")
    public Set<ChipSetDto> update(@PathVariable("id") UUID id,
                                  @RequestBody ChipSetDto chipSetDto) {
        playerChipSetService.update(id, chipSetDto);
        return playerChipSetService.findPlayerChips(id);
    }

    @DeleteMapping("players/{id}/chips")
    public ResponseEntity<?> delete(@UUIDChecker @PathVariable("id") UUID id) {
        return playerChipSetService.deleteAll(id)
                ? noContent().build()
                : notFound().build();
    }
}
