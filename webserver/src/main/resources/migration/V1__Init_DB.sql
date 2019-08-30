-- Файл миграции БД с помощью flyway, должен располагаться в /resources/db/migration
-- имя должно начинаться с V далее цифра и 2 нижних подчеркивания (опционально описание в имени без пробелов)
-- в теле скрипт на HQL для выполнения действий с базой на этапе запуска приложения

create table groups (
  id bigint not null,
  attribute varchar(255),
  filename varchar(255),
  group_name varchar(255),
  group_tag varchar(255),
  user_id bigint,
primary key (id))

create table message(
  id bigint not null,
  filename varchar(255),
  tag varchar(255),
  text varchar(2048),
  user_id bigint,
primary key (id))

create table metric (
  id bigint not null,
  ip_address varchar(255),
  port int not null,
  selected bit not null,
  title varchar(255),
  value varchar(255),
  group_id bigint,
primary key (id))

create table user_role (
  user_id bigint not null,
  roles varchar(255))

create table usr (
  id bigint not null,
  activation_code varchar(255),
  active bit not null,
  email varchar(255),
  password varchar(255),
  username varchar(255),
primary key (id))


alter table groups
  add constraint groups_user_fk foreign key (user_id) references usr
alter table message
  add constraint message_user_fk foreign key (user_id) references usr
alter table metric
  add constraint metric_user_fk foreign key (group_id) references groups
alter table user_role
  add constraint user_role_user_fk foreign key (user_id) references usr

