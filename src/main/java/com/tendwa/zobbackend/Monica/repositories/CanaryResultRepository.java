package com.tendwa.zobbackend.Monica.repositories;

import com.tendwa.zobbackend.Monica.entities.CanaryResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CanaryResultRepository extends JpaRepository<CanaryResult, UUID> {
}