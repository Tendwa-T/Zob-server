package com.tendwa.zobbackend.Monica.entities;

import com.tendwa.zobbackend.Regina.entities.AppService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "canary_checks", schema = "zobV1")
public class CanaryCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "canary_check_id")
    private UUID canaryCheckId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "service_id", nullable = false)
    private AppService service;

    @Size(max = 500)
    @NotNull
    @Column(name = "endpoint_url")
    private String endpointUrl;

    @ColumnDefault("200")
    @Column(name = "expected_status")
    private Integer expectedStatus;

    @ColumnDefault("3000")
    @Column(name = "timeout_ms")
    private Integer timeoutMs;

    @ColumnDefault("(now())")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("(now())")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "canaryCheck")
    private Set<CanaryResult> canaryResults = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

}