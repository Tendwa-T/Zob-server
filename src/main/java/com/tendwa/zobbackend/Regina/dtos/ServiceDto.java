package com.tendwa.zobbackend.Regina.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.Regina.entities.AppService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link AppService}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDto implements Serializable {
    UUID serviceId;
    @NotNull
    @Size(max = 255)
    String name;
    @NotNull
    @Size(max = 512)
    String baseUrl;
    @NotNull
    @Size(max = 512)
    String authToken;
    @NotNull
    String status;
    @NotNull
    Instant createdAt;
    @NotNull
    Instant updatedAt;
    Instant lastSeen;
}