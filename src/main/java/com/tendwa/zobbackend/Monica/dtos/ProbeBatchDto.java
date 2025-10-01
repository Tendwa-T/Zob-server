package com.tendwa.zobbackend.Monica.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.tendwa.zobbackend.Monica.entities.ProbeBatch}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProbeBatchDto implements Serializable {
    Integer id;
    @NotNull
    UUID batchId;
    @NotNull
    Integer totalConfigurations;
    Integer totalRun;
    Integer totalSuccess;
    Integer totalFailed;
    @NotNull
    Instant createdAt;
    Instant startedAt;
    Instant completedAt;
}