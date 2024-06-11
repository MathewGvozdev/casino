package com.mgvozdev.casino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgvozdev.casino.annotation.IntegrationTest;
import com.mgvozdev.casino.dto.UserReadDto;
import com.mgvozdev.casino.service.UserService;
import com.mgvozdev.casino.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final UUID EXISTING_USER_ID = UUID.fromString("616deeeb-b47f-4550-95f7-cb31dabd14ea");
    private static final UUID NON_EXISTING_USER_ID = UUID.fromString("616deeeb-b47f-4550-95f7-cb31dabd0000");

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Test
    void findAll_positive_returnListOfUsers() throws Exception {
        var expectedList = userService.findAll();
        var json = objectMapper.writeValueAsString(expectedList);

        var mvcResult = mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse,
                new TypeReference<List<UserReadDto>>() {
                });

        Assertions.assertEquals(expectedList, actualResult);
    }

    @Test
    void findById_positive_returnUserDto() throws Exception {
        var userReadDto = userService.findById(EXISTING_USER_ID);
        var json = objectMapper.writeValueAsString(userReadDto);

        var mvcResult = mockMvc.perform(get("/users/{id}", EXISTING_USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, UserReadDto.class);

        assertEquals(userReadDto, actualResult);
    }

    @Test
    void findById_negativeWrongUuid_throwsUserException() throws Exception {
        mockMvc.perform(get("/users/{id}", NON_EXISTING_USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create_positive_returnNewUser() throws Exception {
        var userCreateDto = TestUtils.getUserCreateDto();
        var json = objectMapper.writeValueAsString(userCreateDto);

        var mvcResult = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, UserReadDto.class);

        assertEquals(userCreateDto.firstName(), actualResult.firstName());
        assertEquals(userCreateDto.lastName(), actualResult.lastName());
        assertEquals(userCreateDto.username(), actualResult.username());
        assertEquals(userCreateDto.shift(), actualResult.shift());
    }

    @Test
    public void updateUser_positive_returnUpdatedUser() throws Exception {
        var userEditDto = TestUtils.getUserEditDto();
        var json = objectMapper.writeValueAsString(userEditDto);

        var mvcResult = mockMvc.perform(put("/users/{id}", EXISTING_USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, UserReadDto.class);

        assertEquals(userEditDto.username(), actualResult.username());
    }

    @Test
    public void updateInfo_positive_returnUpdatedUser() throws Exception {
        var userInfoEditDto = TestUtils.getUserInfoEditDto();
        var json = objectMapper.writeValueAsString(userInfoEditDto);

        var mvcResult = mockMvc.perform(put("/users/{id}/info", EXISTING_USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, UserReadDto.class);

        assertEquals(userInfoEditDto.firstName(), actualResult.firstName());
        assertEquals(userInfoEditDto.lastName(), actualResult.lastName());
        assertEquals(userInfoEditDto.shift(), actualResult.shift());
    }

    @Test
    public void update_negativeUuidNotFound_throwsUserException() throws Exception {
        var userEditDto = TestUtils.getUserEditDto();
        var json = objectMapper.writeValueAsString(userEditDto);

        mockMvc.perform(put("/users/{id}", NON_EXISTING_USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
}
