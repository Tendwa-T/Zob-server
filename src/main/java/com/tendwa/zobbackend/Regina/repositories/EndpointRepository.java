package com.tendwa.zobbackend.Regina.repositories;

import com.tendwa.zobbackend.Regina.entities.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {
    List<Endpoint> findAllByAppService_ServiceId(UUID appServiceServiceId);
}