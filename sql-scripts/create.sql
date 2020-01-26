drop table if exists `userimage`;
drop table if exists `productimage`;
drop table if exists `userinfo`;
drop table if exists `image`;
drop table if exists `orderdetail`;
drop table if exists `order`;
drop table if exists `product`;
drop table if exists `user`;

create table `user` (
	`id` bigint not null auto_increment,
	`username` varchar(50) not null,
	`password` varchar(68) not null,
	`status` tinyint not null default 1,
	`role` varchar(10) not null default 'user',
	constraint `PK_user` primary key (`id`),
	constraint `UNQ_user_username` unique key (`username`)
);

create table `image` (
	`id` bigint not null auto_increment,
	`image` longblob not null,
	constraint `PK_image` primary key (`id`)
);

create table `userinfo` (
	`user_id` bigint not null,
	`name` varchar(50) not null,
	`surname` varchar(50) not null,
	`patronymic` varchar(50) not null,
	`image_id` bigint,
	constraint `PK_user_id` primary key (`user_id`),
	constraint `FK_userinfo_user` foreign key (`user_id`) references `user`(`id`),
	constraint `FK_userinfo_image` foreign key (`image_id`) references `image`(`id`)
);

create table `product` (
	`id` bigint not null auto_increment,
	`name` varchar(50) not null,
	`info` varchar(500) not null,
	`status` varchar(15) not null default 'available',
	`cost` double not null,
	`type` varchar(15) not null,
	constraint `PK_product` primary key (`id`),
	constraint `UNQ_product_name` unique key (`name`)
);

create table `productimage` (
	`product_id` bigint not null,
	`image_id` bigint not null,
	constraint `PK_productimage` primary key (`image_id`, `product_id`),
	constraint `FK_productimage_product` foreign key (`product_id`) references `product`(`id`),
	constraint `FK_productimage)image` foreign key (`image_id`) references `image`(`id`)
);

create table `order` (
	`id` bigint not null auto_increment,
	`user_id` bigint not null,
	`order_date` datetime not null default now(),
	`end_date` datetime,
	`status` varchar(10) not null default 'new',
	constraint `PK_order` primary key (`id`),
	constraint `FK_order_user` foreign key (`user_id`) references `user`(`id`)
);

create table `orderdetail` (
	`order_id` bigint not null,
	`product_id` bigint not null,
	`count` int not null,
	constraint `PK_orderdetail` primary key (`order_id`, `product_id`),
	constraint `FK_orderdetail_product` foreign key (`product_id`) references `product`(`id`)
);

create table `userimage` (
	`user_id` bigint not null,
	`image_id` bigint not null,
	constraint `PK_userimage` primary key (`user_id`, `image_id`),
	constraint `FK_userimage_user` foreign key (`user_id`) references `user`(`id`),
	constraint `FK_userimage_image` foreign key (`image_id`) references `image`(`id`)
);