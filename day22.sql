create database day21;
use day21;

//图书分类表
create table category(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);
insert into category values('34324','javaEE','前景好');
insert into category values('43828','android','前景非常好');

//图书表
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

//新增加用户的账户密码表
create table customer(
	id varchar(100) primary key,
	username varchar(100) not null unique,
	password varchar(100) not null,
	phone varchar(100) not null,
	address varchar(100) not null,
	email varchar(100) not null,
	actived int,
	code varchar(100)
);

alter table customer add role int ;//1是管理者

select * from customer;
update customer set role=0;
update customer set role=1 where username='ggg';

//插入一些默认的用户,密码使用Md5Utils去加密获得插入
insert into customer values('11222','ccc','ICy5YqxZB1uWSwcVLSNLcA==','13829961049','广东省湛江市霞山区人民大道2号农垦中心医院','faith_yee@126.com',1,3332221135222123,1);