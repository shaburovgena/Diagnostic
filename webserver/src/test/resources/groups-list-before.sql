delete from groups;

insert into groups(id, text, tag, user_id) values
(1, 'first', 'one', 1),
(2, 'second', 'more', 1),
(3, 'third', 'new', 1),
(4, 'fourth', 'new', 2);

alter sequence hibernate_sequence restart with 10;
