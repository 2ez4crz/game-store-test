insert into tb_user VALUES(0, 0, '1996-12-12', 'asd@gmail.com', 'Admin', '$2a$10$63sOERuHhV6t5OQIhnAv1.3oI1uKbAHL2/.mcejBE3kZPLq6fXIJq', '2018-08-29', 'Adm0n', null, 'http://localhost:8080/images/user/default.jpg');

insert into tb_role VALUES(1, 'ROLE_NORMAL_USER');
insert into tb_role VALUES(2, 'ROLE_PUBLISHER');
insert into tb_role VALUES(3, 'ROLE_ADMIN');

insert into user_roles VALUES(0,3);
