package com.mgvozdev.casino.service;

import com.mgvozdev.casino.dto.ProfileCreateEditDto;
import com.mgvozdev.casino.dto.ProfileReadDto;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProfileService {

    ProfileReadDto findById(UUID id);

    List<ProfileReadDto> findAll();

    List<ProfileReadDto> findBy(String documentNumber);

    List<ProfileReadDto> findBy(MembershipType membershipType);

    List<ProfileReadDto> findBy(ProfileStatus profileStatus);

    List<ProfileReadDto> findByDepositGreaterThan(BigDecimal deposit);

    ProfileReadDto create(ProfileCreateEditDto profileCreateEditDto);

    ProfileReadDto update(UUID id, ProfileCreateEditDto profileCreateEditDto);

    boolean delete(UUID id);
}
