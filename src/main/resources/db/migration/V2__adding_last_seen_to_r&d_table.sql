alter table services
    add last_seen TIMESTAMP default (current_time()) null;

