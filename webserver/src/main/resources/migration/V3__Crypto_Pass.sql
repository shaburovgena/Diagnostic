-- Шифруем пароли уже внесенные в базу
--Postgres
-- create extension if no exists pgcrypto;
-- update usr set password = crypt (password, salt('bf', 8));

--MySQL
-- update usr set password = if(username != 'admin', MD5(password), password);﻿