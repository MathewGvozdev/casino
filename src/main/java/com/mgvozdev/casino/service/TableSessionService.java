package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.TableSession;

import java.util.Optional;
import java.util.UUID;

public interface TableSessionService {

    Optional<TableSession> findById(UUID id);

    TableSession create(TableSession tableSession);

    boolean delete(UUID id);
}
