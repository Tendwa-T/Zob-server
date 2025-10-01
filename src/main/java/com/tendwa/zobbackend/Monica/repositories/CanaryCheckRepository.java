package com.tendwa.zobbackend.Monica.repositories;

import com.tendwa.zobbackend.Monica.entities.CanaryCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CanaryCheckRepository extends JpaRepository<CanaryCheck, UUID> {
}