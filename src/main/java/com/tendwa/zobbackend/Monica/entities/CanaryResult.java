package com.tendwa.zobbackend.Monica.entities;

import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "canary_results", schema = "zobV1")
public class CanaryResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "canary_result_id")
    private UUID canaryResultId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "canary_check_id", nullable = false)
    private CanaryCheck canaryCheck;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppServiceStatus status;

    @Column(name = "response_time")
    private Integer responseTime;

    @Lob
    @Column(name = "message")
    private String message;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "checked_at", nullable = false)
    private Instant checkedAt;

}