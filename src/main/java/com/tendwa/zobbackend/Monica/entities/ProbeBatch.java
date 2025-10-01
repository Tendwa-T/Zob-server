package com.tendwa.zobbackend.Monica.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "probe_batches", schema = "zobV1")
public class ProbeBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 16)
    @NotNull
    @Column(name = "batch_id")
    private UUID batchId;

    @NotNull
    @Column(name = "total_configurations", nullable = false)
    private Integer totalConfigurations;

    @ColumnDefault("0")
    @Column(name = "total_run")
    private Integer totalRun;

    @ColumnDefault("0")
    @Column(name = "total_success")
    private Integer totalSuccess;

    @ColumnDefault("0")
    @Column(name = "total_failed")
    private Integer totalFailed;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

}