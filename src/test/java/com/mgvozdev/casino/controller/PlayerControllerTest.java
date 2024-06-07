package com.mgvozdev.casino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgvozdev.casino.annotation.IntegrationTest;
import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.entity.enums.Chip;
import com.mgvozdev.casino.service.PlayerService;
import com.mgvozdev.casino.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerService playerService;



    @Test
    void findAll_positive_returnListOfPlayers() throws Exception {
        var expectedList = playerService.findAll();
        var json = objectMapper.writeValueAsString(expectedList);

        var mvcResult = mockMvc.perform(get("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse,
                new TypeReference<List<PlayerReadDto>>() {
                });

        assertEquals(expectedList, actualResult);
    }

    @Test
    void findAllByParams_positive_returnListOfPlayers() throws Exception {
        var url = "/players?openedFrom=2024-03-29T00:00:00&openedTill=2024-03-30T00:00:00";
        var expectedList = playerService.findAll().subList(0, 2);
        var json = objectMapper.writeValueAsString(expectedList);

        var mvcResult = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse,
                new TypeReference<List<PlayerReadDto>>() {
                });

        assertEquals(expectedList, actualResult);
    }

    @Test
    void findById_positive_returnPlayerDto() throws Exception {
        var uuid = UUID.fromString("d9ba4962-aa83-40b1-a027-794d5531e586");
        var playerReadDto = playerService.findById(uuid);
        var json = objectMapper.writeValueAsString(playerReadDto);

        var mvcResult = mockMvc.perform(get("/players/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, PlayerReadDto.class);

        assertEquals(playerReadDto, actualResult);
    }

    @Test
    void findById_negativeWrongUuid_throwsPlayerException() throws Exception {
        var notExistingUuid = UUID.fromString("11111111-aaaa-bbbb-cccc-000000000000");

        mockMvc.perform(get("/players/{id}", notExistingUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create_positive_returnNewPlayer() throws Exception {
        var playerCreateDto = TestUtils.getPlayerCreateDto();
        var json = objectMapper.writeValueAsString(playerCreateDto);

        var mvcResult = mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, PlayerReadDto.class);

        assertEquals(playerCreateDto.documentNumber(), actualResult.documentNumber());
        assertEquals(playerCreateDto.buyIn(), actualResult.buyIn());
    }

    @Test
    public void create_negativeNoSuchDocument_throwsPlayerException() throws Exception {
        var notExistingDocument = "00000zxc";
        var playerCreateDto = new PlayerCreateDto(notExistingDocument, new BigDecimal(100));
        var json = objectMapper.writeValueAsString(playerCreateDto);

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update_positive_returnUpdatedPlayer() throws Exception {
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";
        var playerEditDto = TestUtils.getPlayerEditDto();
        var json = objectMapper.writeValueAsString(playerEditDto);

        var mvcResult = mockMvc.perform(put("/players/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, PlayerReadDto.class);

        assertEquals(playerEditDto.buyIn(), actualResult.buyIn());
        assertEquals(playerEditDto.closedAt(), actualResult.closedAt());
        assertEquals(playerEditDto.avgBet(), actualResult.avgBet());
    }

    @Test
    public void update_negativeUuidNotFound_throwsPlayerException() throws Exception {
        var notExistingUuid = UUID.fromString("11111111-aaaa-bbbb-cccc-000000000000");
        var playerEditDto = TestUtils.getPlayerEditDto();
        var json = objectMapper.writeValueAsString(playerEditDto);

        mockMvc.perform(put("/players/{id}", notExistingUuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update_negativeWrongValuesGiven_throwsPlayerException() throws Exception {
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";
        var dtoWithWrongBuyIn = new PlayerEditDto(new BigDecimal(-500), LocalDateTime.now(), 50);
        var dtoWithWrongAvgBet = new PlayerEditDto(new BigDecimal(500), LocalDateTime.now(), -20);
        var wrongBuyInJson = objectMapper.writeValueAsString(dtoWithWrongBuyIn);
        var nullAvgBetJson = objectMapper.writeValueAsString(dtoWithWrongAvgBet);

        mockMvc.perform(put("/players/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wrongBuyInJson))
                .andExpect(status().isBadRequest());
        mockMvc.perform(put("/players/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nullAvgBetJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delete_positive_noContentStatus() throws Exception {
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";

        mockMvc.perform(delete("/players/{id}", uuid))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_negativeNotExistingId_notFoundStatus() throws Exception {
        var notExistingUuid = "11111111-aaaa-bbbb-cccc-000000000000";

        mockMvc.perform(delete("/players/{id}", notExistingUuid))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addChipsForPlayer_positive_returnSetOfChips() throws Exception {
        var playerId = UUID.fromString("a9f6c8cf-fec4-4ffc-99d6-13e6bc1e9b5f");
        var chips = TestUtils.getSetOfChips();
        var json = objectMapper.writeValueAsString(chips);

        var mvcResult = mockMvc.perform(post("/players/{id}/chips", playerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse,
                new TypeReference<Set<ChipSetDto>>() {
                });

        assertEquals(chips, actualResult);
    }

    @Test
    public void addChipsForPlayer_negativeNoSuchPlayer_throwsPlayerException() throws Exception {
        var notExistingPlayerId = UUID.fromString("11111111-aaaa-bbbb-cccc-000000000000");
        var chips = TestUtils.getSetOfChips();
        var json = objectMapper.writeValueAsString(chips);

        mockMvc.perform(post("/players/{id}/chips", notExistingPlayerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addChipsForPlayer_negativeWrongValuesGiven_throwsPlayerException() throws Exception {
        var playerId = UUID.fromString("a9f6c8cf-fec4-4ffc-99d6-13e6bc1e9b5f");
        var chips = TestUtils.getSetOfChipsForNegative();
        var json = objectMapper.writeValueAsString(chips);

        mockMvc.perform(post("/players/{id}/chips", playerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateChipSetForPlayer_positive_returnPlayerChips() throws Exception {
        var playerId = UUID.fromString("dfafbb82-414b-4dc5-872e-f9dc63b1ee42");
        var chipSetDto = TestUtils.getChipSetDto();
        var json = objectMapper.writeValueAsString(chipSetDto);

        var mvcResult = mockMvc.perform(put("/players/{id}/chips", playerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse,
                new TypeReference<Set<ChipSetDto>>() {
                });
        var updatedChips = playerService.findById(playerId).chips();

        assertEquals(updatedChips, actualResult);
    }

    @Test
    public void updateChipSetForPlayer_negativeNoSuchPlayer_throwsChipException() throws Exception {
        var notExistingPlayerId = UUID.fromString("11111111-aaaa-bbbb-cccc-000000000000");
        var chipSetDto = TestUtils.getChipSetDto();
        var json = objectMapper.writeValueAsString(chipSetDto);

        mockMvc.perform(put("/players/{id}/chips", notExistingPlayerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateChipSetForPlayer_negativeWrongValuesGiven_throwsPlayerException() throws Exception {
        var playerId = UUID.fromString("dfafbb82-414b-4dc5-872e-f9dc63b1ee42");
        var wrongChipSetDto = new ChipSetDto(Chip.RED, null, new BigDecimal(-50));
        var json = objectMapper.writeValueAsString(wrongChipSetDto);

        mockMvc.perform(put("/players/{id}/chips", playerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAllPlayerChips_positive_noContentStatus() throws Exception {
        var playerId = "dfafbb82-414b-4dc5-872e-f9dc63b1ee42";

        mockMvc.perform(delete("/players/{id}/chips", playerId))
                .andExpect(status().isNoContent());
    }

    //TODO 15.05.2024: jacoco doesn't see this test
    @Test
    public void deleteAllPlayerChips_negativeNotExistingId_notFoundStatus() throws Exception {
        var notExistingPlayerId = "11111111-aaaa-bbbb-cccc-000000000000";

        mockMvc.perform(delete("/players/{id}/chips", notExistingPlayerId))
                .andExpect(status().isNotFound());
    }
}