package com.tendwa.zobbackend.Monica.dtos.crud.create;

import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class CanaryResultReq {
    private UUID canaryCheckId;
    private AppServiceStatus status;
    private Integer responseTime;
    private String message;
    private Instant checkedAt;
}
