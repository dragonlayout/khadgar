drop table if exists `user_test`;
create table `user_test`
(
    `id`           bigint unsigned  not null auto_increment comment '用户 id',
    `username`     varchar(64)      not null comment '用户账号',
    -- enum
    `user_type`    smallint                  default '0' comment '用户类型(0系统用户)',
    -- enum
    `sex`          tinyint unsigned          default '0' comment '用户性别(0男 1女 2未知)',
    `password`     varchar(128)     not null default '' comment '密码',
    -- enum
    `status`       tinyint unsigned not null default '0' comment '账号状态(0正常 1停用 2冻结)',
    -- 通用字段
    `creator_id`   bigint                    default null comment '更新者 id',
    `create_time`  bigint unsigned  not null comment '创建时间',
    `updater_id`   bigint                    default null comment '更新者 id',
    `update_time`  bigint unsigned  not null comment '更新时间',
    -- boolean
    `is_deleted`   tinyint unsigned not null default '0' comment '删除标记(时间戳)',
    -- primary key
    primary key pk_user_test (`id`),
    -- unique key
    unique key uk_user_test (`username`, `is_deleted`)
) ENGINE = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_general_ci comment '用户测试表';