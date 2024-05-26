create table if not exists `board` (
    id                int unsigned auto_increment primary key,
    uid               int unsigned                                      not null,
    title             varchar(256)                                      not null,
    body              TEXT                                              not null,
    create_date       timestamp default CURRENT_TIMESTAMP not null,
    modified_at       timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
) comment '게시글' charset = 'utf8mb4' collate = 'utf8mb4_general_ci';