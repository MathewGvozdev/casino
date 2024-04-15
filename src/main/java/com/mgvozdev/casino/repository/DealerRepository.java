package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Dealer;
import com.mgvozdev.casino.entity.enums.DealerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, UUID> {

    Optional<Dealer> findById(UUID id);

    Optional<Dealer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Dealer> findByStatus(DealerStatus status);
}
