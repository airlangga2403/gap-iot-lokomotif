package com.iot.springbootcreatereport.repository;

import com.iot.springbootcreatereport.dto.LocomotifSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocomotifSummaryRepository extends JpaRepository<LocomotifSummary, UUID> {
    Optional<LocomotifSummary> findFirstByOrderByLastModifiedTimeDesc();
}
