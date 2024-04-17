package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Dealer;

import java.util.Optional;
import java.util.UUID;

public interface DealerService {

    Optional<Dealer> findById(UUID id);

    Dealer create(Dealer dealer);

    boolean delete(UUID id);
}
