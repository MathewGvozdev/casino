package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Table;
import com.mgvozdev.casino.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public Optional<Table> findById(UUID id) {
        return tableRepository.findById(id);
    }

    @Transactional
    public Table create(Table table) {
        return tableRepository.save(table);
    }

    @Transactional
    public boolean delete(UUID id) {
        return tableRepository.findById(id)
                .map(entity -> {
                    tableRepository.delete(entity);
                    tableRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
