-- Create Database to your MySql Server

create database java2akad;
use java2akad;
create table if not exists users(
    user_id int(11) not null auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    created_at datetime not null default current_timestamp,
    modified_at datetime not null default current_timestamp,
);