package com.tendwa.zobbackend.discovery.repositories;

import com.tendwa.zobbackend.discovery.entities.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {
}