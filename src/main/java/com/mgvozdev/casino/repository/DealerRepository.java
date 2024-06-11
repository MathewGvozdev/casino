package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, UUID> {

    Optional<Dealer> findById(UUID id);
}
