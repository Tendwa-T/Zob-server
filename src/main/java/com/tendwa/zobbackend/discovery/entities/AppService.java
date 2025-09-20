package com.tendwa.zobbackend.discovery.entities;

import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
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
@Table(name = "services", schema = "zobV1")
public class AppService {
    @Id
    @Size(max = 16)
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "service_id", nullable = false, length = 16)
    private UUID serviceId;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 512)
    @NotNull
    @Column(name = "base_url", nullable = false, length = 512)
    private String baseUrl;

    @Size(max = 512)
    @NotNull
    @Column(name = "auth_token", nullable = false, length = 512)
    private String authToken;

    @NotNull
    @ColumnDefault("'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppServiceStatus status;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("(now())")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "appService")
    private Set<Endpoint> endpoints = new LinkedHashSet<>();

    @OneToMany(mappedBy = "appService")
    private Set<ServiceLog> serviceLogs = new LinkedHashSet<>();

    @ColumnDefault("(curtime())")
    @Column(name = "last_seen")
    private Instant lastSeen;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        lastSeen = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
        lastSeen = Instant.now();
    }

}