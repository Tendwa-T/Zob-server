package com.tendwa.zobbackend.incident.entities;

import com.tendwa.zobbackend.discovery.entities.AppService;
import com.tendwa.zobbackend.discovery.entities.Endpoint;
import com.tendwa.zobbackend.generic.enums.IncidentSeverity;
import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "incidents", schema = "zobV1")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "incident_id", nullable = false)
    private UUID incidentId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private AppService service;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "endpoint_id", nullable = false)
    private Endpoint endpoint;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "stack_trace")
    private String stackTrace;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IncidentStatus status;

    @NotNull
    @Column(name = "severity", nullable = false)
    @Enumerated(EnumType.STRING)
    private IncidentSeverity severity;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "detected_at", nullable = false)
    private Instant detectedAt;

    @Column(name = "resolved_at")
    private Instant resolvedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "created_by", nullable = false)
    private IncidentUpdatedBy createdBy;

    @OneToMany(mappedBy = "incident")
    private Set<IncidentNotification> incidentNotifications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "incident")
    private Set<IncidentUpdate> incidentUpdates = new LinkedHashSet<>();


    public boolean isOpen() {
        return status == IncidentStatus.OPEN || status == IncidentStatus.INVESTIGATING;
    }

    public boolean isClosed() {
        return status == IncidentStatus.CLOSED;
    }
    public boolean isResolved() {
        return status == IncidentStatus.RESOLVED;
    }

}