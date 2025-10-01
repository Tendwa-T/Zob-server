package com.tendwa.zobbackend.Monica.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.tendwa.zobbackend.Monica.entities.CanaryResult}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class CanaryResultDto implements Serializable {
    UUID canaryResultId;
    UUID canaryCheckCanaryCheckId;
    @NotNull
    AppServiceStatus status;
    Integer responseTime;
    String message;
    @NotNull
    Instant checkedAt;
}