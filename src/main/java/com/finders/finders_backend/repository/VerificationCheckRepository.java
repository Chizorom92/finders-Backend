package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.VerificationCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VerificationCheckRepository extends JpaRepository<VerificationCheck, Long> {

    // Looks up the verification record for a given property id -
    // this is used when computing risk_level for that property.
    Optional<VerificationCheck> findByPropertyId(Long propertyId);
}