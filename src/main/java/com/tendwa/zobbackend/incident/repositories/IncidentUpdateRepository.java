package com.tendwa.zobbackend.incident.repositories;

import com.tendwa.zobbackend.incident.entities.IncidentUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface IncidentUpdateRepository extends JpaRepository<IncidentUpdate, UUID> {
}