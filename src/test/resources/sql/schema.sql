create table if not exists user (
    id              int unsigned    auto_increment primary key,
    email           varchar(200)    not null unique ,
    name            varchar(200)    not null,
    nickname        varchar(200)    not null,
    password        varchar(200)    not null,
    access_token    varchar(256)    null,
    join_date       timestamp       not null default CURRENT_TIMESTAMP,
    modified_at     timestamp       not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) charset = utf8mb4, collate = utf8mb4_general_ci;