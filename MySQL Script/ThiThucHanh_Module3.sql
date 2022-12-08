drop database thithuchanh_module3;
create database thithuchanh_module3;
use thithuchanh_module3;
create table employee(
id 			bigint auto_increment primary key,
name 		varchar(50),
email 		varchar(50),
address 	varchar(50),
phoneNumber	varchar(50),
salary		double,
departmentId int,
foreign key (departmentId) references department(id)
);

create table department(
id 			bigint auto_increment primary key,
name 		varchar(50)
);
