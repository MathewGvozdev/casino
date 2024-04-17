package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.entity.Dealer;
import com.mgvozdev.casino.repository.DealerRepository;
import com.mgvozdev.casino.service.DealerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;

    public Optional<Dealer> findById(UUID id) {
        return dealerRepository.findById(id);
    }

    @Transactional
    public Dealer create(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Transactional
    public boolean delete(UUID id) {
        return dealerRepository.findById(id)
                .map(entity -> {
                    dealerRepository.delete(entity);
                    dealerRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
