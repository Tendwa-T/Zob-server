package com.tendwa.zobbackend.incident.dtos.crudDtos.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.generic.enums.IncidentSeverity;
import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import com.tendwa.zobbackend.incident.entities.Incident;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link Incident}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIncidentDto implements Serializable {
    UUID incidentId;
    UUID serviceServiceId;
    UUID endpointEndpointId;

    String title;
    String description;
    String stackTrace;

    IncidentStatus status;

    IncidentSeverity severity;

    Instant detectedAt;
    Instant resolvedAt;
    IncidentUpdatedBy createdBy;
}