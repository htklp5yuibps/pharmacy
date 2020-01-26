insert into `user` (`username`, `password`, `status`, `role`)
values ('admin01@mail.ru', '{bcrypt}$2a$10$/7S0XNQGqkVPEvjfBjOozumuR0TatDaDfO8Iu4RvnQRFm6Hm5XK9i', 1, 'admin'),
('admin02@mail.ru', '{bcrypt}$2a$10$/7S0XNQGqkVPEvjfBjOozumuR0TatDaDfO8Iu4RvnQRFm6Hm5XK9i', 0, 'admin'),
('user01@mail.ru', '{bcrypt}$2a$10$xHEq/FealMfCuI/VM3JWBeIX9/WtBBIzc4hcCEO0I7BoRQpERnF9.', 1, 'user'),
('user02@mail.ru', '{bcrypt}$2a$10$xHEq/FealMfCuI/VM3JWBeIX9/WtBBIzc4hcCEO0I7BoRQpERnF9.', 0, 'user'),
('employee01@mail.ru', '{bcrypt}$2a$10$o3MK5zfb/1NCixTSoAjRA.mjXQGIEN.NFZkLEqxCr9Qep5klvH5Z.', 1, 'employee'),
('employee02@mail.ru', '{bcrypt}$2a$10$o3MK5zfb/1NCixTSoAjRA.mjXQGIEN.NFZkLEqxCr9Qep5klvH5Z.', 0, 'employee');

insert into `userinfo` (`user_id`, `name`, `surname`, `patronymic`)
values (1, 'Владислав', 'Солдатенков', 'Андреевич'),
(2, 'Никита', 'Руденко', 'Александрович'),
(3, 'Егор', 'Скурат', 'Батькович'),
(4, 'Владислав', 'Королев', 'Блацкович'),
(5, 'Роман', 'Смирнов', 'Андреевич'),
(6, 'Татьяна', 'Цодикова', 'Владимировна');

insert into `product` (`name`, `info`, `cost`, `type`)
value ('Продукт 01', 'Some info...', 9.99, 'pills'),
('Продукт 02', 'Some info...', 9.99, 'pills'),
('Продукт 03', 'Some info...', 9.99, 'pills'),
('Продукт 04', 'Some info...', 9.99, 'salve'),
('Продукт 05', 'Some info...', 9.99, 'salve'),
('Продукт 06', 'Some info...', 9.99, 'salve');

insert into `order` (`user_id`, `status`)
values (1, 'new'),
(1, 'closed'),
(1, 'ready'),
(2, 'new'),
(2, 'closed'),
(2, 'ready'),
(3, 'new'),
(3, 'closed'),
(3, 'ready'),
(4, 'new'),
(4, 'closef'),
(4, 'ready'),
(5, 'new'),
(5, 'closed'),
(5, 'ready'),
(6, 'new'),
(6, 'closed'),
(6, 'ready');

insert into `orderdetail` (`order_id`, `product_id`, `count`)
values (1, 1, 5),
(2, 2, 5),
(3, 3, 5),
(4, 4, 5),
(5, 5, 5),
(6, 1, 5),
(7, 1, 5),
(8, 2, 5),
(9, 3, 5),
(10, 4, 5),
(11, 5, 5),
(12, 1, 5),
(13, 1, 5),
(14, 2, 5),
(15, 3, 5),
(16, 4, 5),
(17, 5, 5),
(18, 1, 5);