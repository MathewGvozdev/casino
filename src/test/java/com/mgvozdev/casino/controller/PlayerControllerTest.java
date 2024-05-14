package com.mgvozdev.casino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgvozdev.casino.dto.PlayerReadDto;
import com.mgvozdev.casino.util.ExpectedResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    void findAll() throws Exception {
        var urlTemplateForAllPlayers = "/players";
        var urlTemplateForPlayersWithParams = "/players?openedFrom=2024-03-29T00:00:00&openedTill=2024-03-30T00:00:00";

        testForAllPlayers(urlTemplateForAllPlayers);
        testForPlayersWithRequestParams(urlTemplateForPlayersWithParams);
    }

    private void testForAllPlayers(String urlTemplateForAllPlayers) throws Exception {
        var expectedList = ExpectedResult.getListOfPlayerReadDtos();
        var json = objectMapper.writeValueAsString(expectedList);
        var mvcResult = mockMvc.perform(get(urlTemplateForAllPlayers)
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

    private void testForPlayersWithRequestParams(String urlTemplateForPlayersWithParams) throws Exception {
        var expectedList = ExpectedResult.getListOfPlayerReadDtos().subList(0, 2);
        var json = objectMapper.writeValueAsString(expectedList);
        var mvcResult = mockMvc.perform(get(urlTemplateForPlayersWithParams)
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
    void findById() throws Exception {
        var playerReadDto = ExpectedResult.getPlayerReadDto();
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";

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
    public void createPlayerPositiveTest() throws Exception {
        var playerCreateDto = ExpectedResult.getPlayerCreateDto();

        var json = objectMapper.writeValueAsString(playerCreateDto);
        var mvcResult = mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, PlayerReadDto.class);

        assertEquals(201, mvcResult.getResponse().getStatus());
        assertEquals(playerCreateDto.documentNumber(), actualResult.documentNumber());
        assertEquals(playerCreateDto.buyIn(), actualResult.buyIn());
    }

    @Test
    public void updatePlayerPositiveTest() throws Exception {
        var playerEditDto = ExpectedResult.getPlayerEditDto();
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";

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
    public void deletePlayerPositiveTest() throws Exception {
        var uuid = "d9ba4962-aa83-40b1-a027-794d5531e586";
        var notExistingUuid = "11111111-aaaa-bbbb-cccc-000000000000";

        mockMvc.perform(delete("/players/{id}", uuid))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/players/{id}", notExistingUuid))
                .andExpect(status().isNotFound());
    }
}