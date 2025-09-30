package com.tendwa.zobbackend.incident.dtos.crudDtos.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.generic.enums.CommunicationChannel;
import com.tendwa.zobbackend.incident.entities.IncidentNotification;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link IncidentNotification}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIncidentNotificationDto implements Serializable {
    UUID notificationId;
    UUID incidentIncidentId;
    @NotNull
    CommunicationChannel channel;
    @NotNull
    @Size(max = 255)
    String target;
    @NotNull
    Instant sentAt;
}