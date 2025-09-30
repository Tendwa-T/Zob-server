create table probe_batches
(
    id                   int auto_increment
        primary key,
    batch_id             binary(16) default (uuid_to_bin(uuid())) not null,
    total_configurations int                                      not null,
    total_run            int        default 0                     null,
    total_success        int        default 0                     null,
    total_failed         int        default 0                     null,
    created_at           timestamp  default (CURRENT_TIMESTAMP()) not null,
    started_at           timestamp                                null,
    completed_at         timestamp                                null,
    constraint probe_batches_pk_2
        unique (batch_id)
);

create table canary_checks
(
    canary_check_id binary(16) default (UUID_TO_BIN(UUID())) not null
        primary key,
    service_id      binary(16)                               not null,
    endpoint_url    varchar(500)                             not null,
    expected_status int        default 200                   null,
    timeout_ms      int        default 3000                  null,
    created_at      timestamp  default (current_timestamp()) null,
    updated_at      timestamp  default (current_timestamp()) null,
    constraint canary_checks_services_service_id_fk
        foreign key (service_id) references services (service_id)
            on delete cascade
);

create table canary_results
(
    canary_result_id binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    canary_check_id  binary(16)                               not null,
    status           ENUM ('UP', 'DOWN', 'DEGRADED')          not null,
    response_time    int                                      null,
    message          TEXT                                     null,
    checked_at       timestamp  default (CURRENT_TIMESTAMP()) not null,
    constraint canary_results_canary_checks_canary_check_id_fk
        foreign key (canary_check_id) references canary_checks (canary_check_id)
            on delete cascade
);


