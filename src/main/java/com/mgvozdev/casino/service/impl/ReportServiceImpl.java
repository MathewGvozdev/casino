package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.entity.Report;
import com.mgvozdev.casino.repository.ReportRepository;
import com.mgvozdev.casino.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public Optional<Report> findById(UUID id) {
        return reportRepository.findById(id);
    }

    @Transactional
    public Report create(Report report) {
        return reportRepository.save(report);
    }

    @Transactional
    public boolean delete(UUID id) {
        return reportRepository.findById(id)
                .map(entity -> {
                    reportRepository.delete(entity);
                    reportRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
