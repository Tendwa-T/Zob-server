package com.tendwa.zobbackend.Ima.entities;

import com.tendwa.zobbackend.generic.enums.CommunicationChannel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "incident_notifications", schema = "zobV1")
public class IncidentNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notification_id")
    private UUID notificationId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "incident_id", nullable = false)
    private Incident incident;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CommunicationChannel channel;

    @Size(max = 255)
    @NotNull
    @Column(name = "target", nullable = false)
    private String target;

    @NotNull
    @Column(name = "sent_at", nullable = false)
    private Instant sentAt;

}