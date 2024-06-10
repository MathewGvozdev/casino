package com.mgvozdev.casino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgvozdev.casino.annotation.IntegrationTest;
import com.mgvozdev.casino.dto.ProfileReadDto;
import com.mgvozdev.casino.service.ProfileService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProfileService profileService;

    @Test
    void findAll_positive_returnListOfProfiles() throws Exception {
        var expectedList = profileService.findAll();
        var json = objectMapper.writeValueAsString(expectedList);

        var mvcResult = mockMvc.perform(get("/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse,
                new TypeReference<List<ProfileReadDto>>() {
                });

        Assertions.assertEquals(expectedList, actualResult);
    }

    @Test
    void findById_positive_returnProfileDto() throws Exception {
        var uuid = UUID.fromString("a6a43441-ac01-4de7-911e-94d7c6cb6fbd");
        var profileReadDto = profileService.findById(uuid);
        var json = objectMapper.writeValueAsString(profileReadDto);

        var mvcResult = mockMvc.perform(get("/profiles/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, ProfileReadDto.class);

        assertEquals(profileReadDto, actualResult);
    }

    @Test
    void findById_negativeWrongUuid_throwsProfileException() throws Exception {
        var notExistingUuid = UUID.fromString("11111111-aaaa-bbbb-cccc-000000000000");

        mockMvc.perform(get("/profiles/{id}", notExistingUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create_positive_returnNewProfile() throws Exception {
        var profileCreateDto = TestUtils.getProfileCreateEditDto();
        var json = objectMapper.writeValueAsString(profileCreateDto);

        var mvcResult = mockMvc.perform(post("/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, ProfileReadDto.class);

        assertEquals(profileCreateDto.getDocumentNumber(), actualResult.documentNumber());
        assertEquals(profileCreateDto.getAddress(), actualResult.address());
        assertEquals(profileCreateDto.getCountry(), actualResult.country());
        assertEquals(profileCreateDto.getDateOfBirth(), actualResult.dateOfBirth());
        assertEquals(profileCreateDto.getFirstName(), actualResult.firstName());
        assertEquals(profileCreateDto.getLastName(), actualResult.lastName());
        assertEquals(profileCreateDto.getIssueDate(), actualResult.issueDate());
        assertEquals(profileCreateDto.getExpirationDate(), actualResult.expirationDate());
        assertEquals(profileCreateDto.getPhoneNumber(), actualResult.phoneNumber());
    }

    @Test
    public void update_positive_returnUpdatedProfile() throws Exception {
        var uuid = "a6a43441-ac01-4de7-911e-94d7c6cb6fbd";
        var profileEditDto = TestUtils.getProfileCreateEditDto();
        var json = objectMapper.writeValueAsString(profileEditDto);

        var mvcResult = mockMvc.perform(put("/profiles/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = mvcResult.getResponse().getContentAsString();
        var actualResult = objectMapper.readValue(jsonResponse, ProfileReadDto.class);

        assertEquals(profileEditDto.getDocumentNumber(), actualResult.documentNumber());
        assertEquals(profileEditDto.getAddress(), actualResult.address());
        assertEquals(profileEditDto.getCountry(), actualResult.country());
        assertEquals(profileEditDto.getDateOfBirth(), actualResult.dateOfBirth());
        assertEquals(profileEditDto.getFirstName(), actualResult.firstName());
        assertEquals(profileEditDto.getLastName(), actualResult.lastName());
        assertEquals(profileEditDto.getIssueDate(), actualResult.issueDate());
        assertEquals(profileEditDto.getExpirationDate(), actualResult.expirationDate());
        assertEquals(profileEditDto.getPhoneNumber(), actualResult.phoneNumber());
    }

    @Test
    public void update_negativeUuidNotFound_throwsPlayerException() throws Exception {
        var notExistingUuid = UUID.fromString("11111111-aaaa-bbbb-cccc-000000000000");
        var profileEditDto = TestUtils.getProfileCreateEditDto();
        var json = objectMapper.writeValueAsString(profileEditDto);

        mockMvc.perform(put("/profiles/{id}", notExistingUuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_positive_noContentStatus() throws Exception {
        var uuid = "a6a43441-ac01-4de7-911e-94d7c6cb6fbd";

        mockMvc.perform(delete("/profiles/{id}", uuid))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_negativeNotExistingId_notFoundStatus() throws Exception {
        var notExistingUuid = "11111111-aaaa-bbbb-cccc-000000000000";

        mockMvc.perform(delete("/profiles/{id}", notExistingUuid))
                .andExpect(status().isNotFound());
    }
}
