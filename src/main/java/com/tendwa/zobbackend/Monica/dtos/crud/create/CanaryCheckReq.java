package com.tendwa.zobbackend.Monica.dtos.crud.create;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;

@Data
public class CanaryCheckReq {
    private String serviceId;
    private String endpointUrl;
    private Integer expectedStatus = 200;

    @Nullable
    private Integer timeoutMs;

    @Nullable
    private Instant createdAt;

    @Nullable
    private Instant updatedAt;
}
