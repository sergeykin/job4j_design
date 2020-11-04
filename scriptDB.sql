create database users;

create table users(
    UserID serial primary key ,
    FIO    varchar(255),
    BirthDay date,
    Heigh double precision
);

insert into users( FIO, BirthDay, Heigh) values ('TestUser', '20000102',153.12);

select * from users;

update users
  set fio = 'xxx'
where userid = 1;

delete from public.users where userid = 2;
