create table services
(
    service_id binary(16)                     default (uuid_to_bin(uuid())) not null
        primary key,
    name       varchar(255)                                                 not null,
    base_url   varchar(512)                                                 not null,
    auth_token varchar(512)                                                 not null,
    status     ENUM ('UP', 'DOWN', 'UNKNOWN') default 'UNKNOWN'             not null,
    created_at TIMESTAMP                      default (CURRENT_TIMESTAMP()) not null,
    updated_at TIMESTAMP                      default (CURRENT_TIMESTAMP()) not null
);

create table endpoints
(
    endpoint_id binary(16)                     default (uuid_to_bin(uuid())) not null
        primary key,
    service_id  binary(16)                                                   not null,
    path        varchar(500)                                                 not null,
    method      ENUM ('GET', 'POST', 'PUT', 'DELETE', 'PATCH')                 not null,
    type        ENUM ('CANARY', 'NORMAL')      default 'NORMAL'              not null,
    status      ENUM ('UP', 'DOWN', 'UNKNOWN') default 'UNKNOWN'             not null,
    constraint endpoints_services_service_id_fk
        foreign key (service_id) references services (service_id)
            on delete cascade
);

create table service_logs
(
    log_id     binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    service_id binary(16)                               not null,
    message    TEXT                                     null,
    created_at timestamp  default (CURRENT_TIMESTAMP()) not null,
    constraint service_logs_services_service_id_fk
        foreign key (service_id) references services (service_id)
            on delete cascade
);

