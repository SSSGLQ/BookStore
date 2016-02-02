create database day21;
use day21;
create table category(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);
insert into category values('34324','javaEE','前景好');
insert into category values('43828','android','前景非常好');

create table book(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	author varchar(100),
	price float(8,2),
	imageName varchar(100),
	description varchar(255),
	categoryid varchar(100),
	constraint categoryid_fk foreign key(categoryid) references category(id)
);
insert into book values('11111','android应用开发手册','王友军',100,'1.jpg','非常不错，一定要看','43828');