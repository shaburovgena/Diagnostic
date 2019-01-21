-- Файл миграции БД с помощью flyway, должен располагаться в /resources/db/migration
-- имя должно начинаться с V далее цифра и 2 нижних подчеркивания (опционально описание в имени без пробелов)
-- в теле скрипт скрипт на HQL для выполнения действий с базой на этапе запуска приложения

create sequence hibernate_sequence
  start with 1
  increment by 1;

create table message (
  id       bigint        not null,
  filename varchar(255),
  tag      varchar(255),
  text     varchar(2048) not null,
  user_id  bigint,
  primary key (id)
);

create table user_role (
  user_id bigint not null,
  roles   varchar(255)
);

create table usr (
  id              bigint       not null,
  activation_code varchar(255),
  active          bit          not null,
  email           varchar(255),
  password        varchar(255) not null,
  username        varchar(255) not null,
  primary key (id)
);

alter table message
  add constraint message_user_fk foreign key (user_id) references usr;
alter table user_role
  add constraint user_role_user_fk foreign key (user_id) references usr;