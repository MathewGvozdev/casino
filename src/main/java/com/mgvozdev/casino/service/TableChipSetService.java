package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.TableChipSet;

import java.util.Optional;
import java.util.UUID;

public interface TableChipSetService {

    Optional<TableChipSet> findById(UUID id);

    TableChipSet create(TableChipSet tableChipSet);

    boolean delete(UUID id);
}
