CREATE SCHEMA test_me_db;

USE test_me_db;

create table user
(
  id       int auto_increment
    primary key,
  login    varchar(20)                           not null,
  password varchar(20)                           not null,
  name     varchar(45)                           not null,
  surname  varchar(45)                           null,
  u_group  varchar(10)                           not null,
  role     enum ('USER', 'ADMIN') default 'USER' null,
  constraint id_UNIQUE
  unique (id)
);

create table task
(
  id        int auto_increment
    primary key,
  title     varchar(100) not null,
  page_name varchar(45)  not null,
  level     int          not null,
  constraint id_UNIQUE
  unique (id),
  constraint title_UNIQUE
  unique (title),
  constraint page_name_UNIQUE
  unique (page_name)
);

create table complete
(
  user_id      int                    not null,
  task_id      int                    not null,
  time         time                   null,
  use_tip      enum ('TRUE', 'FALSE') null,
  use_paid_tip enum ('TRUE', 'FALSE') null,
  primary key (user_id, task_id)
);

create table free_tip
(
  task_id     int  null,
  description text null,
  constraint free_tip_task_id_uindex
  unique (task_id),
  constraint free_tip_task_id_fk
  foreign key (task_id) references task (id)
    on update cascade
    on delete cascade
);

create table tip
(
  task_id     int            not null
    primary key,
  description varchar(10000) not null,
  constraint Tip_task_id_uindex
  unique (task_id)
);

