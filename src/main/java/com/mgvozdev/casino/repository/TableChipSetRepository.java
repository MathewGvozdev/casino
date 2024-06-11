package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.TableChipSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableChipSetRepository extends JpaRepository<TableChipSet, UUID> {

    Optional<TableChipSet> findById(UUID id);
}
