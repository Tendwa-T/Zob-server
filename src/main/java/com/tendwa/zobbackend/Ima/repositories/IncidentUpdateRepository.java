package com.tendwa.zobbackend.Ima.repositories;

import com.tendwa.zobbackend.Ima.entities.IncidentUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncidentUpdateRepository extends JpaRepository<IncidentUpdate, UUID> {
}