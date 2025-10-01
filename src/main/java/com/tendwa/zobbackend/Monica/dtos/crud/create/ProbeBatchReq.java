package com.tendwa.zobbackend.Monica.dtos.crud.create;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;

@Data
public class ProbeBatchReq {
    private Integer totalConfigurations;
    private Integer totalRun;
    private Integer totalSuccess;
    private Integer totalFailed;

    @Nullable
    private Instant createdAt;

    private Instant startedAt;
    private Instant completedAt;

}
