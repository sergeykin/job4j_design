create table "type"(
                       id serial primary key ,
                       name varchar(255)
) ;

create table product(
                        id serial primary key ,
                        name varchar(255),
                        type_id int references type(id),
                        expired_date date,
                        price int

);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('ЯЙЦО');

insert into product(name, type_id, expired_date, price) values ('СЫР1', 1, '20201106', 100);
insert into product(name, type_id, expired_date, price) values ('СЫР2', 1, '20201206', 100);
insert into product(name, type_id, expired_date, price) values ('СЫР3', 1, '20201206', 100);

insert into product(name, type_id, expired_date, price) values ('МОЛОКО1', 2, '20201106', 100);
insert into product(name, type_id, expired_date, price) values ('МОЛОКО1 мороженное', 2, '20201106', 100);
insert into product(name, type_id, expired_date, price) values ('МОЛОКО2 мороженное2', 2, '20201106', 200);

insert into product(name, type_id, expired_date, price) values ('ЯЙЦО1', 3, '20201206', 300);
--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name
from type t
         inner join product p on t.id = p.type_id
where t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select p.name
from product p
where p.name like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select p.name
from product p
where p.expired_date >= (date_trunc('month', CURRENT_DATE)  + interval '1 month');

--4. Написать запрос, который выводит самый дорогой продукт.
select p.name
from product p
where p.price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select max(t.name), count(*)
from product p
         inner join type t on p.type_id = t.id
group by p.type_id;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name
from type t
         inner join product p on t.id = p.type_id
where t.name in ('СЫР', 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select max(t.name)
from product p
         inner join type t on p.type_id = t.id
group by p.type_id
having count(*)<10;
--8. Вывести все продукты и их тип.
select t.name as type
     ,p.name as product
from product p
         inner join type t on p.type_id = t.id;