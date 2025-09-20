package com.tendwa.zobbackend.discovery.entities;

import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import com.tendwa.zobbackend.generic.enums.EndpointType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "endpoints", schema = "zobV1")
public class Endpoint {
    @Id
    @Size(max = 16)
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "endpoint_id", nullable = false, length = 16)
    private UUID endpointId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "service_id", nullable = false)
    private AppService appService;

    @Size(max = 500)
    @NotNull
    @Column(name = "path", nullable = false, length = 500)
    private String path;

    @NotNull
    @Lob
    @Column(name = "method", nullable = false)
    private String method;

    @NotNull
    @ColumnDefault("'NORMAL'")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EndpointType type;

    @NotNull
    @ColumnDefault("'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppServiceStatus status;

}