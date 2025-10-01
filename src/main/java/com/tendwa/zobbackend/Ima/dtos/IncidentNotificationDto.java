package com.tendwa.zobbackend.Ima.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.generic.enums.CommunicationChannel;
import com.tendwa.zobbackend.Ima.entities.IncidentNotification;
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
public class IncidentNotificationDto implements Serializable {
    UUID notificationId;
    @NotNull
    CommunicationChannel channel;
    @NotNull
    @Size(max = 255)
    String target;
    @NotNull
    Instant sentAt;
}