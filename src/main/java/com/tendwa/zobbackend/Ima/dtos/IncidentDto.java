package com.tendwa.zobbackend.Ima.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.generic.enums.IncidentSeverity;
import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import com.tendwa.zobbackend.Ima.entities.Incident;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link Incident}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentDto implements Serializable {
    UUID incidentId;
    @NotNull
    @Size(max = 255)
    String title;
    String description;
    String stackTrace;
    @NotNull
    IncidentStatus status;
    @NotNull
    IncidentSeverity severity;
    @NotNull
    Instant detectedAt;
    Instant resolvedAt;
    @NotNull
    IncidentUpdatedBy createdBy;
}