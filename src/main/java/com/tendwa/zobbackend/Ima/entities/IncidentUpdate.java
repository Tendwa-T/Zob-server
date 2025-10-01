package com.tendwa.zobbackend.Ima.entities;

import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incident_updates", schema = "zobV1")
public class IncidentUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "update_id")
    private UUID updateId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "incident_id", nullable = false)
    private Incident incident;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IncidentStatus status;

    @Lob
    @Column(name = "message")
    private String message;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "updated_by", nullable = false)
    private IncidentUpdatedBy updatedBy;

    @Size(max = 16)
    @Column(name = "updated_by_id", length = 16)
    private String updatedById;

    @PrePersist
    public void prePersist() {
        this.updatedAt = Instant.now();
    }
}