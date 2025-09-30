create table incidents
(
    incident_id binary(16)                                           default (uuid_to_bin(uuid())) not null
        primary key,
    service_id  binary(16)                                                                         not null,
    endpoint_id binary(16)                                                                         not null,
    title       varchar(255)                                                                       not null,
    description text                                                                               null,
    stack_trace text                                                                               null,
    status      ENUM ('OPEN', 'INVESTIGATING', 'RESOLVED', 'CLOSED') default 'OPEN'                not null,
    severity    ENUM ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')                                         not null,
    detected_at timestamp                                            default (current_timestamp()) not null,
    resolved_at timestamp                                                                          null,
    created_by  ENUM ('M_SYS', 'MANUAL')                             default 'M_SYS'               not null,
    constraint incidents_endpoints_endpoint_id_fk
        foreign key (endpoint_id) references endpoints (endpoint_id),
    constraint incidents_services_service_id_fk
        foreign key (service_id) references services (service_id)
);


-- check on this for corrections
create table incident_updates
(
    update_id     binary(16) default (uuid_to_bin(uuid()))             not null
        primary key,
    incident_id   binary(16)                                           not null,
    status        ENUM ('OPEN', 'INVESTIGATING', 'RESOLVED', 'CLOSED') not null,
    message       text                                                 null,
    updated_at    timestamp  default (current_timestamp())             not null,
    updated_by    ENUM ('M_SYS', 'MANUAL')                             not null,
    updated_by_id binary(16)                                           null,
    constraint incident_updates_incidents_incident_id_fk
        foreign key (incident_id) references incidents (incident_id)
);


create table incident_notifications
(
    notification_id binary(16) default (uuid_to_bin(uuid()))     not null
        primary key,
    incident_id     binary(16)                                   not null,
    channel         ENUM ('EMAIL', 'SMS', 'MS_TEAMS', 'WEBHOOK') not null,
    target          VARCHAR(255)                                 not null,
    sent_at         timestamp  default (current_timestamp())     not null,
    constraint incident_notifications_incidents_incident_id_fk
        foreign key (incident_id) references incidents (incident_id)
);




