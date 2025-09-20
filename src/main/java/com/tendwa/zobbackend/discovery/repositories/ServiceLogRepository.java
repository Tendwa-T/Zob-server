package com.tendwa.zobbackend.discovery.repositories;

import com.tendwa.zobbackend.discovery.entities.ServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceLogRepository extends JpaRepository<ServiceLog, UUID> {
}