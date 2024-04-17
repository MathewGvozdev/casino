package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Reward;

import java.util.Optional;
import java.util.UUID;

public interface RewardService {

    Optional<Reward> findById(UUID id);

    Reward create(Reward reward);

    boolean delete(UUID id);
}
