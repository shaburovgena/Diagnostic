-- Файл миграции БД с помощью flyway
-- имя должно начинаться с V далее цифра и 2 нижних подчеркивания (опционально описание в имени без пробелов)
-- в теле скрипт скрипт на HQL для выполнения действий с базой на этапе запуска приложения
insert into usr (id, username, password, active)
  values (1, 'admin', '1', 1);

insert into user_role (user_id, roles)
values (1, 'USER') ,(1, 'ADMIN');