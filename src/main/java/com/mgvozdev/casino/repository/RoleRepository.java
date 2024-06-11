package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findById(UUID id);

    Optional<Role> findByTitle(String roleName);
}
