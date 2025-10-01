package com.tendwa.zobbackend.Monica.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.tendwa.zobbackend.Monica.entities.CanaryCheck}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class CanaryCheckDto implements Serializable {
    UUID canaryCheckId;
    UUID serviceServiceId;
    String serviceName;
    @NotNull
    @Size(max = 500)
    String endpointUrl;
    Integer expectedStatus;
    Integer timeoutMs;
    Instant createdAt;
    Instant updatedAt;
}