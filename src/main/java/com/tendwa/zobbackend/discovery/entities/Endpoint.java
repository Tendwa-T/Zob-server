package com.tendwa.zobbackend.discovery.entities;

import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import com.tendwa.zobbackend.generic.enums.EndpointType;
import com.tendwa.zobbackend.generic.enums.HttpMethods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endpoints", schema = "zobV1")
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "endpoint_id")
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
    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private HttpMethods method;

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