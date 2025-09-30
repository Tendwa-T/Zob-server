package com.tendwa.zobbackend.incident.repositories;

import com.tendwa.zobbackend.incident.entities.Incident;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {
    List<Incident> findByService_ServiceIdAndEndpoint_EndpointId(UUID serviceServiceId, UUID endpointEndpointId);

    @Query("SELECT i FROM Incident i where i.status='OPEN' OR i.status='INVESTIGATING' AND i.service.serviceId=:service_id")
    List<Incident> getActiveIncidents(@Param("service_id") UUID service_id);

    List<Incident> findAllByService_ServiceId(UUID serviceServiceId, Pageable pageable);
}