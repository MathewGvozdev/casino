package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RewardRepository extends JpaRepository<Reward, UUID> {

    Optional<Reward> findById(UUID id);
}
