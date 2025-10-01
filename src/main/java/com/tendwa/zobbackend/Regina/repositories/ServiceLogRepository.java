package com.tendwa.zobbackend.Regina.repositories;

import com.tendwa.zobbackend.Regina.entities.ServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceLogRepository extends JpaRepository<ServiceLog, UUID> {
}