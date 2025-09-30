package com.tendwa.zobbackend.incident.services;

import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import com.tendwa.zobbackend.incident.dtos.crudDtos.create.CreateIncidentDto;
import com.tendwa.zobbackend.incident.entities.Incident;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IncidentService {
    //* Create incident
    UUID createIncident(CreateIncidentDto incidentDto);

    //* Update incident Status
    boolean updateIncidentStatus(UUID incident_id, IncidentStatus incidentStatus, String message, IncidentUpdatedBy updatedBy);

    //! Internal service
    //* Auto Resolve Incident
    void autoResolveIncident(UUID service_id, UUID endpoint_id);

    //* Active incidents
    List<Incident> getAllActiveIncidents(UUID service_id);

    //! Duplicate Service
    //* Get Incidents By Service
    List<Incident> getAllIncidents(UUID service_id, Pageable pageable);

    //* Get all incidents
    List<Incident> getIncidentHistory(UUID service_id, Pageable pageable);

    //* Resolve incident
    boolean resolveIncident(UUID incident_id, String message, IncidentUpdatedBy updatedBy);

    //* Close incident
    boolean closeIncident(UUID incident_id, String message, IncidentUpdatedBy updatedBy);
}
