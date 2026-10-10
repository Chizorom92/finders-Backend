package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.AgentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AgentProfileRepository extends JpaRepository<AgentProfile, Long> {

    // makes it possible to look up an agent's profile starting from their User id
    Optional<AgentProfile> findByUserId(Long userId);
}