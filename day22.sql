create database day21;
use day21;

//ͼ������
create table category(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);
insert into category values('34324','javaEE','ǰ����');
insert into category values('43828','android','ǰ���ǳ���');

//ͼ���
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
insert into book values('11111','androidӦ�ÿ����ֲ�','���Ѿ�',100,'1.jpg','�ǳ�����һ��Ҫ��','43828');

//�������û����˻������
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

alter table customer add role int ;//1�ǹ�����

select * from customer;
update customer set role=0;
update customer set role=1 where username='ggg';

//����һЩĬ�ϵ��û�,����ʹ��Md5Utilsȥ���ܻ�ò���
insert into customer values('11222','ccc','ICy5YqxZB1uWSwcVLSNLcA==','13829961049','�㶫ʡտ����ϼɽ��������2��ũ������ҽԺ','faith_yee@126.com',1,3332221135222123,1);