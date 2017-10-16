drop schema if exists mvc;

create schema mvc;

use mvc;

create table user(
	id int auto_increment,
    username varchar(25),
    password varchar(25),
    email varchar(30),
    primary key(id)
);

insert into user(username, password, email) values ('admin', '123456', 'admin@njxzc.edu.cn');