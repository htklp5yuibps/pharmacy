package no.comp.finalproject.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

@Configuration
public class DataBaseInitConfigurationContext {

	@Bean
	public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource)
			throws IOException {
		JdbcTemplate template = new JdbcTemplate(dataSource);

		template.execute("drop table if exists `userimage`");
		template.execute("drop table if exists `productimage`;");
		template.execute("drop table if exists `userinfo`; ");
		template.execute("drop table if exists `image`; ");
		template.execute("drop table if exists `orderdetail`; ");
		template.execute("drop table if exists `order`; ");
		template.execute("drop table if exists `product`; ");
		template.execute("drop table if exists `user`;");

		template.execute(
				"create table `user` (" + "	`id` bigint not null auto_increment," + "    `username` varchar(50) not null,"
						+ "    `password` varchar(68) not null," + "    `status` tinyint not null default 1,"
						+ "    `role` varchar(10) not null default 'user'," + "    constraint `PK_user` primary key (`id`),"
						+ "    constraint `UNQ_user_username` unique key (`username`)" + ");");

		template.execute("create table `image` (" + "	`id` bigint not null auto_increment,"
				+ "    `image` longblob not null," + "	constraint `PK_image` primary key (`id`)" + ");");

		template.execute("create table `userinfo` (" + "	`user_id` bigint not null," + "    `name` varchar(50) not null,"
				+ "    `surname` varchar(50) not null," + "    `patronymic` varchar(50) not null," + "    `image_id` bigint,"
				+ "    constraint `PK_user_id` primary key (`user_id`),"
				+ "    constraint `FK_userinfo_user` foreign key (`user_id`) references `user`(`id`),"
				+ "    constraint `FK_userinfo_image` foreign key (`image_id`) references `image`(`id`)" + ");");

		template.execute("create table `product` (" + "	`id` bigint not null auto_increment,"
				+ "    `name` varchar(50) not null," + "    `info` varchar(500) not null,"
				+ "    `status` varchar(15) not null default 'available'," + "    `cost` double not null,"
				+ "    `type` varchar(15) not null," + "    constraint `PK_product` primary key (`id`),"
				+ "    constraint `UNQ_product_name` unique key (`name`)" + ");");

		template.execute("create table `productimage` (" + "	`product_id` bigint not null,"
				+ "	`image_id` bigint not null," + "	constraint `PK_productimage` primary key (`image_id`, `product_id`),"
				+ "    constraint `FK_productimage_product` foreign key (`product_id`) references `product`(`id`),"
				+ "    constraint `FK_productimage)image` foreign key (`image_id`) references `image`(`id`)" + ");");

		template
				.execute("create table `order` (" + "	`id` bigint not null auto_increment," + "    `user_id` bigint not null,"
						+ "    `order_date` datetime not null default now()," + "    `end_date` datetime,"
						+ "    `status` varchar(10) not null default 'new'," + "    constraint `PK_order` primary key (`id`),"
						+ "    constraint `FK_order_user` foreign key (`user_id`) references `user`(`id`)" + ");");

		template
				.execute("create table `orderdetail` (" + "	`order_id` bigint not null," + "    `product_id` bigint not null,"
						+ "    `count` int not null," + "    constraint `PK_orderdetail` primary key (`order_id`, `product_id`),"
						+ "    constraint `FK_orderdetail_product` foreign key (`product_id`) references `product`(`id`)" + ");");

		template.execute("create table `userimage` (" + "	`user_id` bigint not null," + "    `image_id` bigint not null,"
				+ "    constraint `PK_userimage` primary key (`user_id`, `image_id`),"
				+ "    constraint `FK_userimage_user` foreign key (`user_id`) references `user`(`id`),"
				+ "    constraint `FK_userimage_image` foreign key (`image_id`) references `image`(`id`)" + ");");

		template.execute("insert into `user` (`username`, `password`, `status`, `role`)"
				+ "values ('admin01@mail.ru', '{bcrypt}$2a$10$/7S0XNQGqkVPEvjfBjOozumuR0TatDaDfO8Iu4RvnQRFm6Hm5XK9i', 1, 'admin'),"
				+ "('admin02@mail.ru', '{bcrypt}$2a$10$/7S0XNQGqkVPEvjfBjOozumuR0TatDaDfO8Iu4RvnQRFm6Hm5XK9i', 0, 'admin'),"
				+ "('user01@mail.ru', '{bcrypt}$2a$10$xHEq/FealMfCuI/VM3JWBeIX9/WtBBIzc4hcCEO0I7BoRQpERnF9.', 1, 'user'),"
				+ "('user02@mail.ru', '{bcrypt}$2a$10$xHEq/FealMfCuI/VM3JWBeIX9/WtBBIzc4hcCEO0I7BoRQpERnF9.', 0, 'user'),"
				+ "('employee01@mail.ru', '{bcrypt}$2a$10$o3MK5zfb/1NCixTSoAjRA.mjXQGIEN.NFZkLEqxCr9Qep5klvH5Z.', 1, 'employee'),"
				+ "('employee02@mail.ru', '{bcrypt}$2a$10$o3MK5zfb/1NCixTSoAjRA.mjXQGIEN.NFZkLEqxCr9Qep5klvH5Z.', 0, 'employee');");

		template.execute("insert into `userinfo` (`user_id`, `name`, `surname`, `patronymic`)"
				+ "values (1, 'Владислав', 'Солдатенков', 'Андреевич')," + "(2, 'Никита', 'Руденко', 'Александрович'),"
				+ "(3, 'Егор', 'Скурат', 'Батькович')," + "(4, 'Владислав', 'Королев', 'Блацкович'),"
				+ "(5, 'Роман', 'Смирнов', 'Андреевич')," + "(6, 'Татьяна', 'Цодикова', 'Владимировна');");

		template.execute("insert into `product` (`name`, `info`, `cost`, `type`)"
				+ "value ('Продукт 01', 'Some info...', 9.99, 'pills')," + "('Продукт 02', 'Some info...', 9.99, 'pills'),"
				+ "('Продукт 03', 'Some info...', 9.99, 'pills')," + "('Продукт 04', 'Some info...', 9.99, 'salve'),"
				+ "('Продукт 05', 'Some info...', 9.99, 'salve')," + "('Продукт 06', 'Some info...', 9.99, 'salve');");

		template.execute("insert into `order` (`user_id`, `status`)" + "values (1, 'new')," + "(1, 'closed'),"
				+ "(1, 'ready')," + "(2, 'new')," + "(2, 'closed')," + "(2, 'ready')," + "(3, 'new')," + "(3, 'closed'),"
				+ "(3, 'ready')," + "(4, 'new')," + "(4, 'closed')," + "(4, 'ready')," + "(5, 'new')," + "(5, 'closed'),"
				+ "(5, 'ready')," + "(6, 'new')," + "(6, 'closed')," + "(6, 'ready');");
		
		template.execute("update `order` set `end_date` = now() where `status` = 'closed'");

		template.execute("insert into `orderdetail` (`order_id`, `product_id`, `count`)" + "values (1, 1, 5),"
				+ "(2, 2, 5)," + "(3, 3, 5)," + "(4, 4, 5)," + "(5, 5, 5)," + "(6, 1, 5)," + "(7, 1, 5)," + "(8, 2, 5),"
				+ "(9, 3, 5)," + "(10, 4, 5)," + "(11, 5, 5)," + "(12, 1, 5)," + "(13, 1, 5)," + "(14, 2, 5)," + "(15, 3, 5),"
				+ "(16, 4, 5)," + "(17, 5, 5)," + "(18, 1, 5);");
		
		for (int i = 1; i < 13; i++) {
			String number = "";
			if (i < 10) {
				number += "0" + i;
			} else {
				number += i;
			}
			
			Resource resource = new ClassPathResource("product_images/" + number + ".jpg");
			InputStream is = resource.getInputStream();
			int length = resource.getInputStream().readAllBytes().length;
			if (resource.exists()) {
				LobHandler lobHandler = new DefaultLobHandler();
				template.execute("insert into `image` (`image`) values (?)", 
						new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
					@Override
					protected void setValues(PreparedStatement ps, LobCreator lobCreator)
							throws SQLException, DataAccessException {
						lobCreator.setBlobAsBinaryStream(ps, 1, is, length);
					}
				});
			}
		}
		
		template.execute("insert into `productimage` (`product_id`, `image_id`)"
				+ " value (1, 1),"
				+ " (1, 2),"
				+ " (2, 3),"
				+ " (2, 4),"
				+ " (3, 5),"
				+ " (3, 6),"
				+ " (4, 7),"
				+ " (4, 8),"
				+ " (5, 9),"
				+ " (5, 10),"
				+ " (6, 11),"
				+ " (6, 12);");
		
		return template;
	}
	
}
