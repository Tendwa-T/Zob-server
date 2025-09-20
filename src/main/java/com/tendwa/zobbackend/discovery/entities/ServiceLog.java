package com.tendwa.zobbackend.discovery.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "service_logs", schema = "zobV1")
public class ServiceLog {
    @Id
    @Size(max = 16)
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "log_id", nullable = false, length = 16)
    private UUID logId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "service_id", nullable = false)
    private AppService appService;

    @Lob
    @Column(name = "message")
    private String message;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

}