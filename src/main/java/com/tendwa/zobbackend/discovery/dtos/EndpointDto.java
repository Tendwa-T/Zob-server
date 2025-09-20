package com.tendwa.zobbackend.discovery.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.tendwa.zobbackend.discovery.entities.Endpoint}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class EndpointDto implements Serializable {
    UUID endpointId;
    @NotNull
    @Size(max = 500)
    String path;
    @NotNull
    String method;
    @NotNull
    String type;
    @NotNull
    String status;
}