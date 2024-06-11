package com.mgvozdev.casino.controller;

import com.mgvozdev.casino.annotation.*;
import com.mgvozdev.casino.dto.ProfileCreateEditDto;
import com.mgvozdev.casino.dto.ProfileReadDto;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;
import com.mgvozdev.casino.service.ProfileService;
import com.mgvozdev.casino.validation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @FindProfileById(path = "/{id}")
    public ProfileReadDto findById(@UuidChecker @PathVariable("id") UUID id) {
        return profileService.findById(id);
    }

    @FindProfiles
    public List<ProfileReadDto> findAll(@RequestParam(required = false) String documentNumber,
                                        @RequestParam(required = false) MembershipType membershipType,
                                        @RequestParam(required = false) ProfileStatus status,
                                        @RequestParam(required = false) BigDecimal deposit) {
        if (documentNumber != null) {
            return profileService.findBy(documentNumber);
        } else if (membershipType != null) {
            return profileService.findBy(membershipType);
        } else if (status != null) {
            return profileService.findBy(status);
        } else if (deposit != null) {
            return profileService.findByDepositGreaterThan(deposit);
        } else {
            return profileService.findAll();
        }
    }

    @CreateProfile
    public ProfileReadDto create(@Validated @RequestBody ProfileCreateEditDto profileCreateEditDto) {
        return profileService.create(profileCreateEditDto);
    }

    @UpdateProfile(path = "/{id}")
    public ProfileReadDto update(@UuidChecker @PathVariable("id") UUID id,
                                 @Validated @RequestBody ProfileCreateEditDto profileCreateEditDto) {
        return profileService.update(id, profileCreateEditDto);
    }

    @DeleteProfile(path = "/{id}")
    public ResponseEntity<?> delete(@UuidChecker @PathVariable("id") UUID id) {
        return profileService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
