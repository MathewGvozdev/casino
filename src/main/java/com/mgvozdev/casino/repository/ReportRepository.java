package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    Optional<Report> findById(UUID id);

    List<Report> findByUserId(UUID userId);

    List<Report> findByDate(LocalDate date);
}
