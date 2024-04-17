package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.entity.TableSession;
import com.mgvozdev.casino.repository.TableSessionRepository;
import com.mgvozdev.casino.service.TableSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableSessionServiceImpl implements TableSessionService {

    private final TableSessionRepository tableSessionRepository;

    public Optional<TableSession> findById(UUID id) {
        return tableSessionRepository.findById(id);
    }

    @Transactional
    public TableSession create(TableSession tableSession) {
        return tableSessionRepository.save(tableSession);
    }

    @Transactional
    public boolean delete(UUID id) {
        return tableSessionRepository.findById(id)
                .map(entity -> {
                    tableSessionRepository.delete(entity);
                    tableSessionRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
