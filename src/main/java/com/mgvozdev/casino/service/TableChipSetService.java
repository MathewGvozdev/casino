package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.TableChipSet;
import com.mgvozdev.casino.repository.TableChipSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableChipSetService {

    private final TableChipSetRepository tableChipSetRepository;

    public Optional<TableChipSet> findById(UUID id) {
        return tableChipSetRepository.findById(id);
    }

    @Transactional
    public TableChipSet create(TableChipSet tableChipSet) {
        return tableChipSetRepository.save(tableChipSet);
    }

    @Transactional
    public boolean delete(UUID id) {
        return tableChipSetRepository.findById(id)
                .map(entity -> {
                    tableChipSetRepository.delete(entity);
                    tableChipSetRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
