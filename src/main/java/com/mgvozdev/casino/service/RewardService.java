package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Reward;
import com.mgvozdev.casino.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;

    public Optional<Reward> findById(UUID id) {
        return rewardRepository.findById(id);
    }

    @Transactional
    public Reward create(Reward reward) {
        return rewardRepository.save(reward);
    }

    @Transactional
    public boolean delete(UUID id) {
        return rewardRepository.findById(id)
                .map(entity -> {
                    rewardRepository.delete(entity);
                    rewardRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
