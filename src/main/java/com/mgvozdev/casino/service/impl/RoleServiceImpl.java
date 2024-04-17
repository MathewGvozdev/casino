package com.mgvozdev.casino.service.impl;

import com.mgvozdev.casino.entity.Role;
import com.mgvozdev.casino.repository.RoleRepository;
import com.mgvozdev.casino.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Transactional
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public boolean delete(UUID id) {
        return roleRepository.findById(id)
                .map(entity -> {
                    roleRepository.delete(entity);
                    roleRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
