package com.tendwa.zobbackend.Regina.repositories;

import com.tendwa.zobbackend.Regina.entities.AppService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<AppService, UUID> {
    AppService findAllByNameAndBaseUrl(String name, String baseUrl);
}