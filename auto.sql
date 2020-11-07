/*
Создать структур данных в базе.
Таблицы.
   Кузов. Двигатель, Коробка передач.
Создать структуру Машина. Машина не может существовать без данных из п.1.
Заполнить таблицы через insert.
                            Создать SQL запросы:

                            1. Вывести список всех машин и все привязанные к ним детали.

                            2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
 */

create table carbody(
                        id serial primary key ,
                        name varchar(255)
) ;

create table engine(
                       id serial primary key ,
                       name varchar(255)
);

create table transmission(
                             id serial primary key ,
                             name varchar(255)
);

create table car(
                    id serial primary key ,
                    carbody_id int references carbody(id),
                    engine_id int references engine(id),
                    transission_id int references transmission(id),
                    name varchar(255)
);

insert into carbody(name) values ('carbody1');
insert into carbody(name) values ('carbody2');
insert into carbody(name) values ('carbody3');

insert into engine(name) values ('engine1');
insert into engine(name) values ('engine2');
insert into engine(name) values ('engine3');

insert into transmission(name) values ('transmission1');
insert into transmission(name) values ('transmission2');
insert into transmission(name) values ('transmission3');

insert into car(carbody_id, engine_id, transission_id, name) VALUES (1,1,1,'car1');
insert into car(carbody_id, engine_id, transission_id, name) VALUES (1,2,2,'car2');

select c.name as car, b.name as carbody, e.name as engine, t.name as transmission
from car c
         inner join carbody b on c.carbody_id = b.id
         inner join engine e on c.engine_id = e.id
         inner join transmission t on c.transission_id = t.id;

select b.name as detail
from carbody b where not exists(select 1 from car c where c.carbody_id = b.id)
union
select e.name as detail
from engine e where not exists(select 1 from car c where c.engine_id = e.id)
union
select t.name as detail
from transmission t where not exists(select 1 from car c where c.transission_id = t.id);
