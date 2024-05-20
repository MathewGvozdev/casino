package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.dto.ProfileCreateEditDto;
import com.mgvozdev.casino.dto.ProfileReadDto;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.ProfileException;
import com.mgvozdev.casino.mapper.ProfileMapper;
import com.mgvozdev.casino.repository.ProfileRepository;
import com.mgvozdev.casino.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileReadDto findById(UUID id) {
        return profileRepository.findById(id)
                .map(profileMapper::toDto)
                .orElseThrow(() -> new ProfileException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public List<ProfileReadDto> findAll() {
        return profileRepository.findAll().stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Override
    public List<ProfileReadDto> findBy(String documentNumber) {
        return profileRepository.findBy(documentNumber)
                .map(profileMapper::toDto)
                .map(dto -> {
                    List<ProfileReadDto> list = new ArrayList<>();
                    list.add(dto);
                    return list;
                })
                .orElseThrow(() -> new ProfileException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public List<ProfileReadDto> findBy(MembershipType membershipType) {
        return profileRepository.findBy(membershipType).stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Override
    public List<ProfileReadDto> findBy(ProfileStatus profileStatus) {
        return profileRepository.findBy(profileStatus).stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Override
    public List<ProfileReadDto> findByDepositGreaterThan(BigDecimal deposit) {
        return profileRepository.findByTotalDepositGreaterThan(deposit).stream()
                .map(profileMapper::toDto)
                .toList();
    }

    @Override
    public ProfileReadDto create(ProfileCreateEditDto profileCreateEditDto) {
        return Optional.of(profileCreateEditDto)
                .map(profileMapper::toEntity)
                .map(profileRepository::save)
                .map(profileMapper::toDto)
                .orElseThrow(() -> new ProfileException(ErrorMessage.NOT_CREATED));
    }

    @Override
    public ProfileReadDto update(UUID id, ProfileCreateEditDto profileCreateEditDto) {
        var profileFromDb = profileRepository.findById(id);
        if (profileFromDb.isPresent()) {
            return profileFromDb
                    .map(entity -> profileMapper.toEntity(profileCreateEditDto, entity))
                    .map(profileRepository::saveAndFlush)
                    .map(profileMapper::toDto)
                    .orElseThrow(() -> new ProfileException(ErrorMessage.NOT_UPDATED));
        } else {
            throw new ProfileException(ErrorMessage.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(UUID id) {
        return profileRepository.findById(id)
                .map(entity -> {
                    profileRepository.delete(entity);
                    profileRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
