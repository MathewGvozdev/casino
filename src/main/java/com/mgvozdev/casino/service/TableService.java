package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Table;

import java.util.Optional;
import java.util.UUID;

public interface TableService {

    Optional<Table> findById(UUID id);

    Table create(Table table);

    boolean delete(UUID id);
}
