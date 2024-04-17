package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Report;

import java.util.Optional;
import java.util.UUID;

public interface ReportService {

    Optional<Report> findById(UUID id);

    Report create(Report report);

    boolean delete(UUID id);
}
