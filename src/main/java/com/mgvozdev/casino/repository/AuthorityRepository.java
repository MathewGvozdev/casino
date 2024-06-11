package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

    Optional<Authority> findById(UUID id);
}
