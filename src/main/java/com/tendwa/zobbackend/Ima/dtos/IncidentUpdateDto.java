package com.tendwa.zobbackend.Ima.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import com.tendwa.zobbackend.Ima.entities.IncidentUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link IncidentUpdate}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentUpdateDto implements Serializable {
    UUID updateId;
    @NotNull
    IncidentStatus status;
    String message;
    @NotNull
    Instant updatedAt;
    @NotNull
    IncidentUpdatedBy updatedBy;
    @Size(max = 16)
    String updatedById;
}