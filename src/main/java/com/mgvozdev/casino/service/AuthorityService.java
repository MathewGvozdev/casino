package com.mgvozdev.casino.service;

import com.mgvozdev.casino.entity.Authority;
import com.mgvozdev.casino.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public Optional<Authority> findById(UUID id) {
        return authorityRepository.findById(id);
    }

    @Transactional
    public Authority create(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Transactional
    public boolean delete(UUID id) {
        return authorityRepository.findById(id)
                .map(entity -> {
                    authorityRepository.delete(entity);
                    authorityRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
