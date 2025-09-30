package com.tendwa.zobbackend.discovery.repositories;

import com.tendwa.zobbackend.discovery.entities.AppService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<AppService, UUID> {
    AppService findAllByNameAndBaseUrl(String name, String baseUrl);
}