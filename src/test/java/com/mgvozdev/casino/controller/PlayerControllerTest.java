package com.mgvozdev.casino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgvozdev.casino.dto.PlayerCreateDto;
import com.mgvozdev.casino.dto.PlayerEditDto;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.service.PlayerService;
import com.mgvozdev.casino.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:/db/test-schema.sql")
@Sql("classpath:/db/test-data.sql")
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
        var url = "/players";
        var expectedList = playerService.findAll();
        var json = objectMapper.writeValueAsString(expectedList);

        var mvcResult = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
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
        var expectedList = playerService.findAll().subList(0,2);
        var json = objectMapper.writeValueAsString(expectedList);

        var mvcResult = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
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
                .andExpect(status().is2xxSuccessful())
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void create_positive_ReturnNewPlayer() throws Exception {
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void update_positive_returnUpdatedPlayer() throws Exception {
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";
        var playerEditDto = TestUtils.getPlayerEditDto();
        var json = objectMapper.writeValueAsString(playerEditDto);

        var mvcResult = mockMvc.perform(put("/players/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
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
        var wrongBuyInDto = new PlayerEditDto(new BigDecimal(-500), LocalDateTime.now(), 50);
        var wrongAvgBetDto = new PlayerEditDto(new BigDecimal(500), LocalDateTime.now(), -20);
        var wrongBuyInJson = objectMapper.writeValueAsString(wrongBuyInDto);
        var nullAvgBetJson = objectMapper.writeValueAsString(wrongAvgBetDto);

        mockMvc.perform(put("/players/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wrongBuyInJson))
                .andExpect(status().is4xxClientError());
        mockMvc.perform(put("/players/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nullAvgBetJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void delete_positive_noContentStatus() throws Exception {
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";

        mockMvc.perform(delete("/players/{id}", uuid))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_negative_notFoundStatus() throws Exception {
        var notExistingUuid = "11111111-aaaa-bbbb-cccc-000000000000";

        mockMvc.perform(delete("/players/{id}", notExistingUuid))
                .andExpect(status().isNotFound());
    }
}