package com.mgvozdev.casino.repository;

import com.mgvozdev.casino.entity.Profile;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findById(UUID id);

    @Query("select p from Profile p " +
           "where p.documentNumber = :documentNumber")
    Optional<Profile> findBy(String documentNumber);

    @Query("select p from Profile p " +
           "where p.membershipType = :membershipType")
    List<Profile> findBy(MembershipType membershipType);

    @Query("select p from Profile p " +
           "where p.status = :profileStatus")
    List<Profile> findBy(ProfileStatus profileStatus);

    List<Profile> findByTotalDepositGreaterThan(BigDecimal deposit);
}
