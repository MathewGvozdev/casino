package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {

    Optional<UserInfo> findById(UUID id);

    Optional<UserInfo> findByUserId(UUID userId);
}
