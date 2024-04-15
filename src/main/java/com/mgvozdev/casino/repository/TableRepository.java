package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<Table, UUID> {

    Optional<Table> findById(UUID id);
}
