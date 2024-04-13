package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.entity.Player;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import com.mgvozdev.casino.service.PlayerService;
import com.mgvozdev.casino.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final ProfileService profileService;

    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") UUID id) {
        return playerService.findById(id)
                .orElseThrow(() -> new PlayerException(ErrorMessage.NOT_FOUND));
    }

    @GetMapping
    public List<Player> findAll() {
        var players = playerService.findAll();
        if (players.isEmpty()) {
            throw new PlayerException(ErrorMessage.NOT_FOUND);
        }
        return players;
    }

    @GetMapping("/create/{profileId}/{openedAt}/{buyIn}/{closedAt}/{avgBet}")
    @ResponseStatus(HttpStatus.CREATED)
    public Player create(@PathVariable("profileId") UUID profileId,
                         @PathVariable("openedAt") LocalDateTime openedAt,
                         @PathVariable("buyIn") BigDecimal buyIn,
                         @PathVariable("closedAt") LocalDateTime closedAt,
                         @PathVariable("avgBet") Integer avgBet) {
        var player = new Player();
        var profile = profileService.findById(profileId);
        if (profile.isPresent()) {
            player.setProfile(profile.get());
        } else {
            throw new PlayerException(ErrorMessage.NOT_FOUND);
        }
        player.setOpenedAt(openedAt);
        player.setBuyIn(buyIn);
        player.setClosedAt(closedAt);
        player.setAvgBet(avgBet);
        return playerService.create(player);
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") UUID id) {
        return playerService.delete(id);
    }
}
