package com.tendwa.zobbackend.discovery.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.discovery.entities.ServiceLog;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link ServiceLog}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceLogDto implements Serializable {
    UUID logId;
    String message;
    @NotNull
    Instant createdAt;
}