package com.mgvozdev.casino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgvozdev.casino.annotation.IntegrationTest;
import com.mgvozdev.casino.dto.ChipSetDto;
import com.mgvozdev.casino.entity.enums.Chip;
import com.mgvozdev.casino.service.PlayerService;
import com.mgvozdev.casino.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
public class ChipControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerService playerService;

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
    public void deleteAllPlayerChips_negative_notFoundStatus() throws Exception {
        var notExistingPlayerId = "11111111-aaaa-bbbb-cccc-000000000000";

        mockMvc.perform(delete("/players/{id}/chips", notExistingPlayerId))
                .andExpect(status().isNotFound());
    }
}
