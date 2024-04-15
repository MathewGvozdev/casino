package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.TableSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableSessionRepository extends JpaRepository<TableSession, UUID> {

    Optional<TableSession> findById(UUID id);

    List<TableSession> findByTableId(UUID tableId);

    List<TableSession> findByClosedAtIsNull();
}
